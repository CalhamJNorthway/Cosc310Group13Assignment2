package chadbot.bot.interpreter;

import chadbot.bot.data.Tokenizer;
import chadbot.bot.data.word.PartOfSpeechTag;
import chadbot.bot.data.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {
    private Interpreter interpreter = new Interpreter();
    private String sentence;
    private Word[] expected;
    private int[] indexInContextArray;
    private WordTransformer synTransformer = new SynonymTransformer("dictPrinceton");
    private WordTransformer posTransformer = new PartOfSpeechTransformer("en-pos-maxent.bin");

    @BeforeEach
    void setUp() {
        sentence = "My dog really likes to go for long walks, specially in the morning before anything else.";

        expected = new Word[]{
                new Word("my", PartOfSpeechTag.PRP$),
                new Word("dog", new String[]{"domestic_dog", "Canis_familiaris"}, PartOfSpeechTag.NN),
                new Word("really", new String[]{"truly", "genuinely"}, PartOfSpeechTag.RB),
                new Word("likes", PartOfSpeechTag.VBZ),
                new Word("to", PartOfSpeechTag.TO),
                new Word("go", new String[]{"travel", "move", "locomote"}, PartOfSpeechTag.VB),
                new Word("for", PartOfSpeechTag.IN),
                new Word("long", PartOfSpeechTag.JJ),
                new Word("walks", PartOfSpeechTag.NNS),
                new Word("specially", new String[]{"especially"}, PartOfSpeechTag.RB),
                new Word("in", PartOfSpeechTag.IN),
                new Word("the", PartOfSpeechTag.DT),
                new Word("morning", new String[]{"morn", "morning_time", "forenoon"}, PartOfSpeechTag.NN),
                new Word("before", PartOfSpeechTag.IN),
                new Word("anything", PartOfSpeechTag.NN),
                new Word("else", PartOfSpeechTag.RB)
        };

        indexInContextArray = new int[] {
                0,
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8,
                10,
                11,
                12,
                13,
                14,
                15,
                16
        };
    }

    @Test
    void interpretSentence() {
        interpreter.addWordTransformer(posTransformer);
        interpreter.addWordTransformer(synTransformer);

        Word[] interpretedSentence = interpreter.interpretSentence(sentence);

        for (int i = 0; i < interpretedSentence.length; i++) {
            Word w = interpretedSentence[i];
            assertEquals(expected[i], w);
            assertArrayEquals(expected[i].getSynonyms(), w.getSynonyms());
        }
    }

    @Test
    void getIndexInContext() {
        String[] context = Tokenizer.tokenizeWithPunctuation(sentence);
        int lastIndex = 0;
        for (int i = 0; i < expected.length; i++) {
            Word word = expected[i];
            int indexInContext = interpreter.getIndexInContext(word, context, lastIndex);
            assertEquals(indexInContextArray[i], indexInContext);

            lastIndex = indexInContext;
        }
    }

    @Test
    void createWords() {
        Word[] actual = interpreter.createWords(sentence.toLowerCase());

        assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i].getWord(), actual[i].getWord());
        }
    }
}