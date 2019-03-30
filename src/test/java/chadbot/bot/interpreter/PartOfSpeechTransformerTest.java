package chadbot.bot.interpreter;

import chadbot.bot.data.Tokenizer;
import chadbot.bot.data.word.PartOfSpeechTag;
import chadbot.bot.data.word.SyntacticCategory;
import chadbot.bot.data.word.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartOfSpeechTransformerTest {
    private PartOfSpeechTransformer transformer;
    private String sentence;
    private Word[] expected;

    @BeforeEach
    void setUp() {
        transformer = new PartOfSpeechTransformer("en-pos-maxent.bin");
        sentence = "My dog really likes to go for long walks in the morning before anything else.";
         expected = new Word[]{
                 new Word("My", PartOfSpeechTag.PRP$),
                 new Word("dog", PartOfSpeechTag.NN),
                 new Word("really", PartOfSpeechTag.RB),
                 new Word("likes", PartOfSpeechTag.VBZ),
                 new Word("to", PartOfSpeechTag.TO),
                 new Word("go", PartOfSpeechTag.VB),
                 new Word("for", PartOfSpeechTag.IN),
                 new Word("long", PartOfSpeechTag.JJ),
                 new Word("walks", PartOfSpeechTag.NNS),
                 new Word("in", PartOfSpeechTag.IN),
                 new Word("the", PartOfSpeechTag.DT),
                 new Word("morning", PartOfSpeechTag.NN),
                 new Word("before", PartOfSpeechTag.IN),
                 new Word("anything", PartOfSpeechTag.NN),
                 new Word("else", PartOfSpeechTag.RB)};
    }

    @Test
    void transformShort() {
        String[] context = Tokenizer.tokenizeWithPunctuation(sentence);
        Word wordOld = new Word("long");
        Word wordNew = transformer.transform(wordOld, context, 7);

        assertEquals(wordOld.getWord(), wordNew.getWord());
        assertNotNull(wordNew.getPartOfSpeechTag());
        assertEquals(PartOfSpeechTag.JJ, wordNew.getPartOfSpeechTag());
        assertEquals(SyntacticCategory.ADJECTIVE, SyntacticCategory.valueOf(wordNew.getPartOfSpeechTag()));
    }

    @Test
    void transformSentence() {
        String[] context = Tokenizer.tokenizeWithPunctuation(sentence);

        for (int i = 0; i < expected.length; i++) {
            Word word = transformer.transform(expected[i], context, i);

            assertEquals(expected[i].getWord(), word.getWord());
            assertNotNull(word.getPartOfSpeechTag());
            assertEquals(expected[i].getPartOfSpeechTag(), word.getPartOfSpeechTag());
        }
    }

    @Test
    void partOfSpeechTaggerModelNotFound() {
        transformer = new PartOfSpeechTransformer("invalidname.bin");

        String[] context = Tokenizer.tokenizeWithPunctuation(sentence);
        Word wordOld = new Word("long");
        Word wordNew = transformer.transform(wordOld, context, 7);

        assertEquals(wordOld.getWord(), wordNew.getWord());
        assertNotEquals(wordOld.getObjectCode(), wordNew.getObjectCode());
    }
}