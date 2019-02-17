package chadbot.bot.AIMLParser;

import chadbot.bot.data.AIMLParser;
import chadbot.bot.data.PatternTemplate;
import chadbot.bot.synonyms.SynonymGroup;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class AIMLParserTest {

    URL url = this.getClass().getResource("TestAIML.xml");
    File file = new File(url.getFile());
    AIMLParser testParser = new AIMLParser(file);

    @Test
    void testCreateParser() {
        assertTrue(testParser != null);
    }

    @Test
    void testParseSynonymGroupArr() {
        String[] synonyms = {"stressful", "upsetting", "nerve-racking"};
        SynonymGroup testGroup = new SynonymGroup(synonyms);
        SynonymGroup responseGroup = testParser.getSynonymGroupArr()[0];
        assertArrayEquals(testGroup.getSynonymArray(), responseGroup.getSynonymArray());
    }

    @Test
    void testParsePatternTemplateArr() {
        String[] pattern = {"Hello"};
        String response = "Hi! I'm Chadbot.";
        PatternTemplate testPattern= new PatternTemplate(pattern, response);
        PatternTemplate responsePattern = testParser.getPatternTemplateArr()[0];
        assertArrayEquals(testPattern.getPattern(), responsePattern.getPattern());
    }

    @Test
    void testDefaultResponse() {
        String response = "This is my default!";
        assertEquals(response,testParser.getDefaultResponse());
    }
}