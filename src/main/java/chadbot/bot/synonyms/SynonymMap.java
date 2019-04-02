package chadbot.bot.synonyms;

import java.util.HashMap;

@Deprecated
public class SynonymMap {
    private HashMap<String, SynonymGroup> table;

    private static final SynonymGroup EMPTY = new SynonymGroup(new String[0]);

    public SynonymMap(SynonymGroup[] synonymGroups) {
        table = new HashMap<>();
        populateTable(synonymGroups);
    }

    /**
     * Get the SynonymGroup that the word belongs to
     * @param word the word to get the synonym group
     * @return the SynonymGroup or an empty SynonymGroup if no SynonymGroup is found
     */
    public SynonymGroup getSynonyms(String word) {
        return table.getOrDefault(word.toLowerCase(), EMPTY);
    }

    private void populateTable(SynonymGroup[] synonymGroups) {
        for (SynonymGroup synonymGroup : synonymGroups) {
            populateTable(synonymGroup);
        }
    }

    private void populateTable(SynonymGroup synonymGroup) {
        for (String word : synonymGroup.getSynonymArray()) {
            table.put(word, synonymGroup);
        }
    }
}
