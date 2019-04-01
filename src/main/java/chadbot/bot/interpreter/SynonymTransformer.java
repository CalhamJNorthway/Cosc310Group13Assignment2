package chadbot.bot.interpreter;

import chadbot.bot.data.word.SyntacticCategory;
import chadbot.bot.data.word.Word;
import edu.mit.jwi.RAMDictionary;
import edu.mit.jwi.data.ILoadPolicy;
import edu.mit.jwi.item.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SynonymTransformer implements WordTransformer {

    private RAMDictionary dictionary;

    public SynonymTransformer(String wordNetDirectory) {
        URL url = SynonymTransformer.class.getResource(wordNetDirectory);
        try {
            dictionary =  new RAMDictionary(url, ILoadPolicy.BACKGROUND_LOAD);
            dictionary.open();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Exception ex = new IOException("The specified WordNet Directory \"" + wordNetDirectory + "\" was not found");
            ex.setStackTrace(e.getStackTrace());
            ex.printStackTrace();
        }
    }

    @Override
    public Word transform(Word word, String[] context, int indexInContext) {
        if(dictionary == null || word.getPartOfSpeechTag() == null) {
            return new Word(word);
        }

        SyntacticCategory cat = SyntacticCategory.valueOf(word.getPartOfSpeechTag());
        if(cat == null) {
            return new Word(word);
        }

        waitForDictionaryToLoad();

        Word w = new Word(word);

        IIndexWord idxWord = dictionary.getIndexWord(w.getWord(), cat.getWordNetPOS());
        if(idxWord == null) {
            return w;
        }
        IWordID wordID = idxWord.getWordIDs().get(0);
        IWord wNetWord = dictionary.getWord(wordID);
        String[] synonyms = convertSynsetToStringArray(w, wNetWord.getSynset());

        w.setSynonyms(synonyms);

        return w;
    }

    private String[] convertSynsetToStringArray(Word word, ISynset synset) {
        List<IWord> words = synset.getWords();
        ArrayList<String> strings = new ArrayList<>();
        for (IWord iWord : words) {
            if(!iWord.getLemma().equals(word.getWord())){
                strings.add(iWord.getLemma());
            }
        }
        return strings.toArray(new String[0]);
    }

    private void waitForDictionaryToLoad() {
        while (!dictionary.isLoaded()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
