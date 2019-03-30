package chadbot.bot.dictionarytree;

import chadbot.bot.data.PatternTemplate;
import chadbot.bot.data.WordPatternTemplate;
import chadbot.bot.data.word.Word;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DictionaryTree {

    public static final String NO_RESPONSE = "";

    private TreeNode root;

    /**
     * Creates a DictionaryTree based on the array of response templates
     */
    public DictionaryTree(WordPatternTemplate[] patternTemplates) {
        root = new TreeNode();
        createTree(patternTemplates);
    }

    /**
     * Creates a DictionaryTree based on the List of response templates
     */
    public DictionaryTree(List<WordPatternTemplate> patternTemplates) {
        this(patternTemplates.toArray(new WordPatternTemplate[0]));
    }

    /**
     * Searches the DictionaryTree for the given prompt to find the leaf node associated with the prompt which contains a
     * response.
     * @param prompt the prompt that is used to search the DictionaryTree
     * @return The response associated with a leaf node if found. Otherwise returns {@link DictionaryTree#NO_RESPONSE}
     */
    public String search(Word[] prompt) {
        if(prompt == null) {
            return NO_RESPONSE;
        }
        String response = NO_RESPONSE;
        TreeNode node = root;

        for (int i = 0; i < prompt.length; i++) {
            Word word = prompt[i];
            TreeNode nodeTemp = node.getChild(word);

            if(nodeTemp == null) {
                for (String synonym : word.getSynonyms()) {
                    Word synonymWord = new Word(synonym, word.getPartOfSpeechTag());
                    nodeTemp = node.getChild(synonymWord);
                    if(nodeTemp != null) {
                        break;
                    }
                }
            }

            node = nodeTemp;

            //Check if there is a new node, breaks out of the loop if there is no new node
            if(node == null) {
                break;
            }

            //Checks to see if the loop is at the end of the prompt and the last found node is a leaf node.
            //Sets the response as the first response in the node
            if(i == prompt.length - 1 && node.isLeaf()) {
                response = node.getResponse();
            }
        }

        return response;
    }

    //Creates the Dictionary Tree
    private void createTree(WordPatternTemplate[] patternTemplates) {
        for (WordPatternTemplate template : patternTemplates) {
            if(template != null) {
                insertResponse(template);
            }
        }
    }

    //Inserts the ResponseTemplate into the tree
    private void insertResponse(WordPatternTemplate patternTemplate) {
        Word[] prompt = patternTemplate.getPattern();

        //Handles if the prompt is null
        if(prompt == null) {
            return;
        }

        TreeNode parent = root;
        for (int i = 0; i < prompt.length; i++) {

            //Create new node
            TreeNode newNode;
            if (i != prompt.length - 1) {
                newNode = new TreeNode(prompt[i]);
            } else {
                newNode = new TreeNode(prompt[i], patternTemplate.getTemplate());
            }

            parent = parent.insert(newNode);
        }
    }

    //Gets the root for the Tree, used for testing
    protected TreeNode getRoot() {
        return root;
    }

    @Override
    public String toString() {
        return "DictionaryTree{" +
                "root=" + root +
                '}';
    }
}
