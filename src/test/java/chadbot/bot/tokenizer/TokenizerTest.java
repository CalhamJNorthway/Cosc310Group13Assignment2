package chadbot.bot.tokenizer;

import org.junit.jupiter.api.Test;
import chadbot.bot.data.Tokenizer;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    Tokenizer T = new Tokenizer();

    //Test Parse on " "
    @Test
    void testInputParserOnSpace() {
        String testString = "The Ting Go Ding";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on Null
    @Test
    void testInputParserOnNull() {
        String testString = null;
        String[] expectedResult = {};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on Empty String
    @Test
    void testInputParserOnEmptyString() {
        String testString = "";
        String[] expectedResult = {};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on !
    @Test
    void testInputParserOnExMark() {
        String testString = "The! Ting! Go! Ding!";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on ?
    @Test
    void testInputParserOnQMark() {
        String testString = "The? Ting? Go? Ding?";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on ,
    @Test
    void testInputParserOnComma() {
        String testString = "The, Ting, Go, Ding,";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on .
    @Test
    void testInputParserOnPrd() {
        String testString = "The. Ting. Go. Ding.";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

    //Test Parse on ;
    @Test
    void testInputParserOnSmCln() {
        String testString = "The; Ting; Go; Ding;";
        String[] expectedResult = {"The", "Ting", "Go", "Ding"};
        assertArrayEquals(expectedResult, Tokenizer.tokenize(testString));
    }

}