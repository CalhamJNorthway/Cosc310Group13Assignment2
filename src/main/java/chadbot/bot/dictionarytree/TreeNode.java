package chadbot.bot.dictionarytree;

import chadbot.bot.data.word.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeNode {

    private boolean isRoot;
    private Word word;
    private List<String> responses;
    private TreeNode parent;
    private HashMap<Word, TreeNode> children;

    public TreeNode() {
        this.isRoot = true;
        children = new HashMap<>();
        responses = new ArrayList<>();
    }

    public TreeNode(Word word) {
        this();
        this.isRoot = false;
        this.word = word;
    }

    public TreeNode(Word word, String response) {
        this(word);
        responses.add(response);
    }

    /**
     * Inserts a TreeNode as a Child to this TreeNode
     * @param child The TreeNode to add as a child
     * @return The TreeNode that was added or if there is a node that contains the same word, it will return the node
     */
    public TreeNode insert(TreeNode child){
        if(!children.containsKey(child.getWord())) {
            children.put(child.getWord(), child);
            child.parent = this;
        } else {
            TreeNode old = getChild(child.getWord());
            old.responses.addAll(child.getResponses());
            child = old;
        }
        return child;
    }

    /**
     * Gets the word associated with this node
     */
    public Word getWord() {
        return word;
    }

    /**
     * Gets the first response in the response list
     */
    public String getResponse() {
        return !responses.isEmpty() ? responses.get(0) : null;
    }

    /**
     * Gets the response in the list based on the index
     */
    public String getResponse(int index) {
        return responses.size() > index ? responses.get(index) : null;
    }

    /**
     * Gets the list of responses, may be empty
     */
    public List<String> getResponses() {
        return responses;
    }

    public boolean isRoot() {
        return isRoot;
    }

    /**
     * Check to see if this node has a response and is a leaf node
     * @return true if the node is a leaf node
     */
    public boolean isLeaf() {
        return !responses.isEmpty();
    }

    /**
     * Gets a Child name that is associated with the parameter
     * @param word the associated word
     * @return the child treenode, null if no child is found
     */
    protected TreeNode getChild(Word word) {
        return children.get(word);
    }

    protected HashMap<Word, TreeNode> getChildren() {
        return children;
    }

    protected int getChildCount() {
        return children.size();
    }

    protected TreeNode getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "word='" + word + '\'' +
                ", response='" + responses + '\'' +
                "\n\t children=" + children +
                '}';
    }
}
