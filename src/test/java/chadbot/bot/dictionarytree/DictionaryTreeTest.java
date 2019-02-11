package chadbot.bot.dictionarytree;

import chadbot.bot.data.ResponseTemplate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTreeTest {

    private String[] prompt1 = {"This", "is", "a", "test", "only", "a", "test"};
    private String[] prompt2 = {"What", "is", "your", "favorite", "song"};
    private String[] prompt3 = {"What", "is", "your", "favorite", "food"};
    private ResponseTemplate template1;
    private ResponseTemplate template2;
    private ResponseTemplate template3;
    private String response1 = "This is just a response";
    private String response2 = "My favorite song is I still haven't found what I'm looking for by U2";
    private String response3 = "I'm a Bot, I don't eat food";

    @BeforeEach
    void setUp() {
        template1 = new ResponseTemplate(prompt1, response1);
        template2 = new ResponseTemplate(prompt2, response2);
        template3 = new ResponseTemplate(prompt3, response3);
    }

    @AfterEach
    void tearDown() {
        template1 = null;
        template2 = null;
        template3 = null;
    }

    //Test the List Constructor for DictionaryTree
    @Test
    void testListConstructor() {
        List<ResponseTemplate> responseTemplates = new ArrayList<>();
        responseTemplates.add(template1);
        responseTemplates.add(template2);
        responseTemplates.add(template3);

        DictionaryTree  dictionaryTree= new DictionaryTree(responseTemplates);

        TreeNode root = dictionaryTree.getRoot();

        TreeNode node = root;
        for (String word : prompt1) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response1, node.getResponse());
            }
        }

        node = root;
        for (String word : prompt2) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response2, node.getResponse());
            }
        }

        node = root;
        for (String word : prompt3) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response3, node.getResponse());
            }
        }
    }

    //Tests the String Array Constructor for Dictionary Tree. Checks to see if each node is added
    @Test
    void testStringArrayConstructor() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        TreeNode root = dictionaryTree.getRoot();

        assertTrue(root.isRoot());
        assertFalse(root.isLeaf());

        TreeNode node = root;
        for (String word : prompt1) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response1, node.getResponse());
            }
        }

        node = root;
        for (String word : prompt2) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response2, node.getResponse());
            }
        }

        node = root;
        for (String word : prompt3) {
            node = node.getChild(word);
            assertNotNull(node);
            assertEquals(word, node.getWord());
            assertEquals(node.getResponse() != null, node.isLeaf());
            if(node.isLeaf()) {
                assertEquals(response3, node.getResponse());
            }
        }
    }

    //Tests if that no duplicate prompts are added top the tree
    @Test
    void testDuplicatesInserts() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template1, template3});

        testExistenceOfRootChildren(dictionaryTree);
    }

    //Tests if there is a null Response Template inserted into the tree. No exception should be thrown
    @Test
    void testNullResponseTemplateInsert() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, null, template3});

        testExistenceOfRootChildren(dictionaryTree);
    }

    //Tests if the Prompt in the Response Template that is inserted is null and doesn't get inserted into the tree. No
    //Exception should be thrown
    @Test
    void testNullPromptInResponseTemplateInsert() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1,
                new ResponseTemplate(null, null), template3});

        testExistenceOfRootChildren(dictionaryTree);
    }

    //Tests if no Response Template is inserted into the tree
    @Test
    void testEmptyResponseTemplatesInserts() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[0]);

        TreeNode root = dictionaryTree.getRoot();

        assertEquals(0, root.getChildCount());
    }

    //Tests if the prompt is empty in the Response template that is inserted. No exception should be thrown
    @Test
    void testEmptyPromptInResponseTemplateInsert() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1,
                new ResponseTemplate(new String[0], null), template3});

        testExistenceOfRootChildren(dictionaryTree);
    }

    //Test if all responses used in the creation of the tree can be found in the tree when searched with the
    //correct prompt
    @Test
    void searchResponseFound() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String actualResponse = dictionaryTree.search(template1.getPrompt());
        assertEquals(response1, actualResponse);

        actualResponse = dictionaryTree.search(template2.getPrompt());
        assertEquals(response2, actualResponse);

        actualResponse = dictionaryTree.search(template3.getPrompt());
        assertEquals(response3, actualResponse);
    }

    //Test if no response is found. Should return DictionaryTree.NO_RESPONSE
    @Test
    void searchResponseNoFound() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String[] prompt = {"Will", "you", "be", "my", "only", "friend"};
        String actualResponse = dictionaryTree.search(prompt);
        assertEquals(DictionaryTree.NO_RESPONSE, actualResponse);
    }

    //Test if no response is found with a prompt that is longer than the height of the tree.
    //Should return DictionaryTree.NO_RESPONSE
    @Test
    void searchPromptExtremelyLong() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String[] prompt = {"Will", "you", "be", "my", "only", "friend", "Will", "you", "be", "my", "only", "friend"};
        String actualResponse = dictionaryTree.search(prompt);
        assertEquals(DictionaryTree.NO_RESPONSE, actualResponse);
    }

    //Test if no response is found with a prompt that is shorter than an actual prompt that can result in a found
    //response. Should return DictionaryTree.NO_RESPONSE
    @Test
    void searchPromptShortOfLeafNode() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String[] prompt = {"This", "is", "a", "test"};
        String actualResponse = dictionaryTree.search(prompt);
        assertEquals(DictionaryTree.NO_RESPONSE, actualResponse);
    }

    //Test if the prompt is null. Should return DictionaryTree.NO_RESPONSE
    @Test
    void searchNullPrompt() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String actualResponse = dictionaryTree.search(null);
        assertEquals(DictionaryTree.NO_RESPONSE, actualResponse);
    }

    //Test if the prompt is empty. Should return DictionaryTree.NO_RESPONSE
    @Test
    void searchEmptyPrompt() {
        DictionaryTree dictionaryTree = new DictionaryTree(new ResponseTemplate[] {template1, template2, template3});

        String[] prompt = {};
        String actualResponse = dictionaryTree.search(prompt);
        assertEquals(DictionaryTree.NO_RESPONSE, actualResponse);
    }

    private void testExistenceOfRootChildren(DictionaryTree dictionaryTree) {
        TreeNode root = dictionaryTree.getRoot();

        assertEquals(2, root.getChildCount());
        assertNotNull(root.getChildren());
        assertNotNull(root.getChild("This"));
        assertNotNull(root.getChild("What"));
    }

}