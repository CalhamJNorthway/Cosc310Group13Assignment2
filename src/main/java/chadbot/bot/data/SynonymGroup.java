package chadbot.bot.data;

import java.util.Arrays;

public class SynonymGroup {
    private String[] synonymArray;

    public SynonymGroup(String[] synonymArray) {
        this.synonymArray = synonymArray;
    }

    public String[] getSynonymArray() {
        return this.synonymArray;
    }

    public String getSynonymFromIndex(int index) {
        return this.synonymArray[index];
    }

    public void addSynonym(String synonym) {
        String[] copy = Arrays.copyOf(this.synonymArray, this.synonymArray.length+1);
        int newIndex = copy.length - 1;
        copy[newIndex] = synonym;
        this.synonymArray = copy;
    }

}
