package chadbot.bot.data;

public class ResponseTemplate {

    private String[] prompt;
    private String response;

    /**
     * Creates a Response Template based on the prompt and the response
     * @param prompt The sentence after it has been tokenized that the bot should recognize to produce a response
     * @param response The response that the bot should produce based on the prompt
     */
    public ResponseTemplate(String[] prompt, String response) {
        this.prompt = prompt;
        this.response = response;
    }

    /**
     * Gets the sentence broken up in words that the bot should recognize to produce a response
     * @return A String[] that is ordered from the first word in the sentence to the last word
     */
    public String[] getPrompt() {
        return prompt;
    }

    /**
     * Gets the response that the bot should produce based on the prompt from this Response Template
     */
    public String getResponse() {
        return response;
    }
}
