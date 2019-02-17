package chadbot.bot.SynonymGroup;

import chadbot.bot.data.SynonymGroup;
import chadbot.bot.data.Tokenizer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SynonymGroupTest {

    String[] SynonymArray = {"Happy", "Sad", "Mad", "Bad"};
    SynonymGroup testGroup = new SynonymGroup(SynonymArray);

    //Test Create SynonymGroup
    @Test
    void testCreateSynonymGroup() {
        assertTrue(testGroup != null);
    }

    //Test get Synonym Array
    @Test
    void testGetSynonymArray() {
        assertEquals(SynonymArray, testGroup.getSynonymArray());
    }

    //Test get Synonym Array
    @Test
    void testGetSynonymFromIndex() {
        assertEquals(SynonymArray[1], testGroup.getSynonymFromIndex(1));
    }

    //Test get Synonym Array
    @Test
    void testAddSynonym() {
        testGroup.addSynonym("Jolly");
        assertTrue(testGroup.getSynonymArray().length > SynonymArray.length);
    }
}