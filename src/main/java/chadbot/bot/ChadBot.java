package chadbot.bot;

import chadbot.Main;
import chadbot.bot.data.*;
import chadbot.bot.data.word.PartOfSpeechTag;
import chadbot.bot.data.word.Word;
import chadbot.bot.dictionarytree.DictionaryTree;
import chadbot.bot.interpreter.Interpreter;
import chadbot.bot.interpreter.PartOfSpeechTransformer;
import chadbot.bot.interpreter.SynonymTransformer;
import chadbot.bot.synonyms.SynonymMap;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;

public class ChadBot {

    private SynonymMap synonyms;
    private DictionaryTree dictionaryTree;
    private String defaultResponse;
    private Interpreter interpreter;

    public ChadBot(InputStream AIMLFile) {
        interpreter = new Interpreter();
        PartOfSpeechTransformer posTransformer = new PartOfSpeechTransformer("en-pos-maxent.bin");
        SynonymTransformer synonymTransformer = new SynonymTransformer("dictPrinceton");

        AIMLParser parser = new AIMLParser(AIMLFile);
        defaultResponse = parser.getDefaultResponse();

        interpreter.addWordTransformer(posTransformer);

        PatternTemplate[] patternTemplates = parser.getPatternTemplate();
        WordPatternTemplate[] interpretedPatternTemplate = interpretPatternTemplate(patternTemplates);
        dictionaryTree = new DictionaryTree(interpretedPatternTemplate);

        interpreter.addWordTransformer(synonymTransformer);

    }

    @Deprecated
    protected ChadBot(SynonymMap synonyms, DictionaryTree dictionaryTree, String defaultResponse) {
        this.synonyms = synonyms;
        this.dictionaryTree = dictionaryTree;
        this.defaultResponse = defaultResponse;
    }

    public String getResponse(String input) {
        Word[] interpretedText = interpreter.interpretSentence(input);
        printInterpretedTextDebug(interpretedText);

        String response = dictionaryTree.search(interpretedText);
        return getFinalResponse(response);
    }

    private WordPatternTemplate[] interpretPatternTemplate(PatternTemplate[] templates) {
        WordPatternTemplate[] interpretedPatternTemplate = new WordPatternTemplate[templates.length];
        for (int i = 0; i < templates.length; i++) {
            Word[] words = interpreter.interpretSentence(templates[i].getPattern());
            interpretedPatternTemplate[i] = new WordPatternTemplate(words, templates[i].getTemplate());
        }

        return interpretedPatternTemplate;
    }

    private void printInterpretedTextDebug(Word[] words) {
        if(Main.DEBUG) {
            System.out.println("[Word{\t");
            for (Word word : words) {
                System.out.println("\t"+word);
            }
        }
    }

    /**
     * Simplifies text by replacing synonyms with words that the bot will understand
     * @param textToSimplify the text that is to be simplified
     * @return the text after the synonyms have been replaced
     */
    @Deprecated
    private String[] simplifySynonyms(String[] textToSimplify) {
        String[] simplifiedText = Arrays.copyOf(textToSimplify, textToSimplify.length);
        for (int i = 0; i < textToSimplify.length; i++) {
            String word = textToSimplify[i];
            String[] synonymArray = synonyms.getSynonyms(word).getSynonymArray();
            if(synonymArray.length != 0 ) {
                simplifiedText[i] = synonymArray[0];
            }
        }

        return simplifiedText;
    }

    /**
     * Gets the response that the bot will return back to the user. If the response is empty it will send back the default
     * response to the user.
     * @param response The response to send to the user
     * @return The response to send to the user, if the response is empty it will return the defaultResponse
     */
    private String getFinalResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            return defaultResponse;
        }

        return response;
    }
}
