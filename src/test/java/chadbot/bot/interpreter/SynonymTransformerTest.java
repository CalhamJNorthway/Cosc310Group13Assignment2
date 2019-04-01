package chadbot.bot.interpreter;

import chadbot.bot.data.word.PartOfSpeechTag;
import chadbot.bot.data.word.Word;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SynonymTransformerTest {

    private SynonymTransformer transformer = new SynonymTransformer("dictPrinceton");

    @Test
    void transformDog() {
        Word word = new Word("dog", PartOfSpeechTag.NN);
        word = transformer.transform(word, null, 0);
        String[] expected = {"domestic_dog", "Canis_familiaris"};

        assertArrayEquals(expected, word.getSynonyms());
    }

    @Test
    void transformNullPartOfSpeechTag() {
        Word wordOld = new Word("dog", null, null);
        Word wordNew = transformer.transform(wordOld, null, 0);
        assertEquals(wordOld, wordNew);
    }

    @Test
    void transformWithNonConvertiblePOSTag() {
        Word wordOld = new Word("My", null, PartOfSpeechTag.PRP$);
        Word wordNew = transformer.transform(wordOld, null, 0);
        assertEquals(wordOld, wordNew);
    }
}