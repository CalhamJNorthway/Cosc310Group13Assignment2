package chadbot.bot.synonyms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SynonymMapTest {
    private SynonymMap synonymMap;

    @BeforeEach
    void setUp() {
        String[] group1 = {"stressful", "upsetting", "nerve-racking"};
        String[] group2 = {"great", "good", "lovely", "wonderful"};
        String[] group3 = {"hi", "hey"};

        synonymMap = new SynonymMap(new SynonymGroup[]{new SynonymGroup(group1),new SynonymGroup(group2),new SynonymGroup(group3)});
    }

    @AfterEach
    void tearDown() {
        synonymMap = null;
    }

    //Test for all existing synonyms in the created map
    @Test
    void getExistingSynonyms() {
        SynonymGroup group = synonymMap.getSynonyms("stressful");
        assertEquals("stressful", group.getSynonymFromIndex(0));
        assertEquals("upsetting", group.getSynonymFromIndex(1));
        assertEquals("nerve-racking", group.getSynonymFromIndex(2));

        group = synonymMap.getSynonyms("upsetting");
        assertEquals("stressful", group.getSynonymFromIndex(0));
        assertEquals("upsetting", group.getSynonymFromIndex(1));
        assertEquals("nerve-racking", group.getSynonymFromIndex(2));

        group = synonymMap.getSynonyms("nerve-racking");
        assertEquals("stressful", group.getSynonymFromIndex(0));
        assertEquals("upsetting", group.getSynonymFromIndex(1));
        assertEquals("nerve-racking", group.getSynonymFromIndex(2));

        group = synonymMap.getSynonyms("great");
        assertEquals("great", group.getSynonymFromIndex(0));
        assertEquals("good", group.getSynonymFromIndex(1));
        assertEquals("lovely", group.getSynonymFromIndex(2));
        assertEquals("wonderful", group.getSynonymFromIndex(3));

        group = synonymMap.getSynonyms("good");
        assertEquals("great", group.getSynonymFromIndex(0));
        assertEquals("good", group.getSynonymFromIndex(1));
        assertEquals("lovely", group.getSynonymFromIndex(2));
        assertEquals("wonderful", group.getSynonymFromIndex(3));

        group = synonymMap.getSynonyms("lovely");
        assertEquals("great", group.getSynonymFromIndex(0));
        assertEquals("good", group.getSynonymFromIndex(1));
        assertEquals("lovely", group.getSynonymFromIndex(2));
        assertEquals("wonderful", group.getSynonymFromIndex(3));

        group = synonymMap.getSynonyms("wonderful");
        assertEquals("great", group.getSynonymFromIndex(0));
        assertEquals("good", group.getSynonymFromIndex(1));
        assertEquals("lovely", group.getSynonymFromIndex(2));
        assertEquals("wonderful", group.getSynonymFromIndex(3));

        group = synonymMap.getSynonyms("hi");
        assertEquals("hi", group.getSynonymFromIndex(0));
        assertEquals("hey", group.getSynonymFromIndex(1));

        group = synonymMap.getSynonyms("hey");
        assertEquals("hi", group.getSynonymFromIndex(0));
        assertEquals("hey", group.getSynonymFromIndex(1));
    }

    //Test for return of an empty SynonymGroup with an empty String array in the SynonymGroup
    @Test
    void getNonExistingSynonymsInMap() {
        SynonymGroup group = synonymMap.getSynonyms("happy");
        assertArrayEquals(new String[0], group.getSynonymArray());
    }
}