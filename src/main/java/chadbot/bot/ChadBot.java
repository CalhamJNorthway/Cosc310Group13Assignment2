package chadbot.bot;

import chadbot.bot.data.Tokenizer;
import chadbot.bot.dictionarytree.DictionaryTree;
import chadbot.bot.synonyms.SynonymGroup;
import chadbot.bot.synonyms.SynonymMap;

import java.util.Arrays;

public class ChadBot {

    private SynonymMap synonyms;
    private DictionaryTree dictionaryTree;
    private String defaultResponse;

    public ChadBot(String AIMLFileLocation) {

    }

    public String getResponse(String input) {
        String[] tokenizedText = Tokenizer.parseInput(input.toLowerCase());
        String[] simplifiedText = simplifySynonyms(tokenizedText);
        String response = dictionaryTree.search(simplifiedText);
        return response;
    }

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
}
