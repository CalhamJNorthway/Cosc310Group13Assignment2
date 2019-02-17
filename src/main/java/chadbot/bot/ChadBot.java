package chadbot.bot;

import chadbot.bot.data.AIMLParser;
import chadbot.bot.data.TextUtils;
import chadbot.bot.data.Tokenizer;
import chadbot.bot.dictionarytree.DictionaryTree;
import chadbot.bot.synonyms.SynonymMap;

import java.io.File;
import java.util.Arrays;

public class ChadBot {

    private SynonymMap synonyms;
    private DictionaryTree dictionaryTree;
    private String defaultResponse;

    public ChadBot(File AIMLFile) {
        AIMLParser parser = new AIMLParser(AIMLFile);
        defaultResponse = parser.getDefaultResponse();
        synonyms = new SynonymMap(parser.getSynonymGroups());
        dictionaryTree = new DictionaryTree(parser.getPatternTemplate());

    }

    protected ChadBot(SynonymMap synonyms, DictionaryTree dictionaryTree, String defaultResponse) {
        this.synonyms = synonyms;
        this.dictionaryTree = dictionaryTree;
        this.defaultResponse = defaultResponse;
    }

    public String getResponse(String input) {
        String[] tokenizedText = Tokenizer.tokenize(input.toLowerCase());
        String[] simplifiedText = simplifySynonyms(tokenizedText);
        String response = dictionaryTree.search(simplifiedText);
        return getFinalResponse(response);
    }

    /**
     * Simplifies text by replacing synonyms with words that the bot will understand
     * @param textToSimplify the text that is to be simplified
     * @return the text after the synonyms have been replaced
     */
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
