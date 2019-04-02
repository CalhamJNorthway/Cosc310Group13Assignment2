package chadbot.bot.data.word;

import java.util.Arrays;
import java.util.Objects;

public class Word {
    private String word;
    private String[] synonyms;
    private PartOfSpeechTag posTag;

    public Word(Word word) {
        this(word.getWord(), word.getSynonyms(), word.getPartOfSpeechTag());
    }

    public Word(String word) {
        this(word, new String[0], null);
    }

    public Word(String word, String[] synonyms) {
        this(word, synonyms, null);
    }

    public Word(String word, PartOfSpeechTag tag) {
        this(word, new String[0], tag);
    }

    public Word(String word, String[] synonyms, PartOfSpeechTag tag) {
        this.word = word;
        this.synonyms = synonyms;
        this.posTag = tag;
    }

    public void setSynonyms(String[] synonyms) {
        this.synonyms = synonyms;
    }

    public void setPosTag(PartOfSpeechTag posTag) {
        this.posTag = posTag;
    }

    public String getWord() {
        return word;
    }

    public String[] getSynonyms() {
        return synonyms;
    }

    public PartOfSpeechTag getPartOfSpeechTag() {
        return posTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return word.equals(word1.word);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word);
    }

    @Override
    public String toString() {
        return "Word{" +
                "\tword = '" + word + '\'' +
                "\n\t\tsynonyms = " + Arrays.toString(synonyms) +
                "\n\t\tposTag = " + posTag +
                '}';
    }

    public int getObjectCode() {
        return super.hashCode();
    }
}
