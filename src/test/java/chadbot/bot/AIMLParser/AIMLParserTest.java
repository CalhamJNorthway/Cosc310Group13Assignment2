package chadbot.bot.AIMLParser;

import chadbot.Main;
import chadbot.bot.data.AIMLParser;
import chadbot.bot.data.Tokenizer;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class AIMLParserTest {

    URL url = Main.class.getResource("ChatBotResponses.xml");
    File file = new File(url.getFile());
    AIMLParser testParser = new AIMLParser(file);

    @Test
    void testInputParserOnSpace() {
        String testString = "The Ting Go Ding";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }



}