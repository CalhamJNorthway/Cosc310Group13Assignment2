package chadbot.bot.dictionarytree;

import chadbot.bot.data.ResponseTemplate;

import java.util.List;

public class DictionaryTree {

    private static final String NO_RESPONSE = "";

    private TreeNode root;

    /**
     * Creates a DictionaryTree based on the array of response templates
     */
    public DictionaryTree(ResponseTemplate[] responseTemplates) {
        root = new TreeNode();
        createTree(responseTemplates);
    }

    /**
     * Creates a DictionaryTree based on the List of response templates
     */
    public DictionaryTree(List<ResponseTemplate> responseTemplates) {
        this(responseTemplates.toArray(new ResponseTemplate[0]));
    }

    //TODO
    public String search(String[] prompt) {
        return NO_RESPONSE;
    }

    //Creates the Dictionary Tree
    private void createTree(ResponseTemplate[] responseTemplates) {
        for (ResponseTemplate template : responseTemplates) {
            if(template != null) {
                insertResponse(template);
            }
        }
    }

    //Inserts the ResponseTemplate into the tree
    private void insertResponse(ResponseTemplate responseTemplate) {
        String[] prompt = responseTemplate.getPrompt();

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
                newNode = new TreeNode(prompt[i], responseTemplate.getResponse());
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
