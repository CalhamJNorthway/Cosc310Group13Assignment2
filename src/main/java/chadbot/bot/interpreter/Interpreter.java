package chadbot.bot.interpreter;

import chadbot.bot.data.Tokenizer;
import chadbot.bot.data.word.Word;

import java.util.ArrayList;
import java.util.List;

public class Interpreter {
    List<WordTransformer> wordTransformerList;

    public Interpreter() {
        wordTransformerList = new ArrayList<>();
    }

    public boolean addWordTransformer(WordTransformer transformer) {
        return wordTransformerList.add(transformer);
    }

    public boolean removeWordTransformer(WordTransformer transformer) {
       return wordTransformerList.remove(transformer);
    }

    public Word[] interpretSentence(String sentence) {
        sentence = sentence.toLowerCase();
        String[] context = Tokenizer.tokenizeWithPunctuation(sentence);
        Word[] words = createWords(sentence);

        int lastIndex = 0;
        for (int i = 0; i < words.length; i++) {
            Word word = words[i];
            int indexInContext = getIndexInContext(word, context, lastIndex);
            word = transformWord(word, context, indexInContext);

            words[i] = word;
            lastIndex = indexInContext;
        }

        return words;
    }

    protected int getIndexInContext(Word word, String[] context, int lastIndex) {
        for (int i = lastIndex; i < context.length; i++) {
            if(word.getWord().equals(context[i])){
                return i;
            }
        }

        return lastIndex;
    }

    private Word transformWord(Word word, String[] context, int indexInContext) {
        for (WordTransformer transformer : wordTransformerList) {
            word = transformer.transform(word, context, indexInContext);
        }
        return word;
    }

    protected Word[] createWords(String sentence) {
        String[] tokenizedText = Tokenizer.tokenizeWithoutPunctuation(sentence);
        Word[] words = new Word[tokenizedText.length];
        for (int i = 0; i < tokenizedText.length; i++) {
            words[i] = new Word(tokenizedText[i]);
        }

        return words;
    }
}
