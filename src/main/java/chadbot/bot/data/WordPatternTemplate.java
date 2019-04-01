package chadbot.bot.data;

import chadbot.bot.data.word.Word;

public class WordPatternTemplate {

    private Word[] pattern;
    private String template;

    /**
     * Creates a Response Template based on the pattern and the template
     * @param pattern The sentence after it has been tokenized that the bot should recognize to produce a template
     * @param template The template that the bot should produce based on the pattern
     */
    public WordPatternTemplate(Word[] pattern, String template) {
        this.pattern = pattern;
        this.template = template;
    }

    /**
     * Gets the sentence broken up in words that the bot should recognize to produce a template
     * @return A Word[] that is ordered from the first word in the sentence to the last word
     */
    public Word[] getPattern() {
        return pattern;
    }

    /**
     * Gets the template that the bot should produce based on the pattern from this Response Template
     */
    public String getTemplate() {
        return template;
    }
}
