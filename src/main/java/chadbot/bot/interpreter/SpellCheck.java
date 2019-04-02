package chadbot.bot.interpreter;

import chadbot.bot.data.word.Word;
import opennlp.tools.stemmer.PorterStemmer;


public class SpellCheck implements WordTransformer {

    @Override
    public Word transform(Word word, String[] context, int indexInContext) {
        PorterStemmer pStem = new PorterStemmer();
        pStem.stem(word.getWord());
        Word w = new Word(pStem.toString(), word.getSynonyms(), word.getPartOfSpeechTag());
        return w;
    }
}
