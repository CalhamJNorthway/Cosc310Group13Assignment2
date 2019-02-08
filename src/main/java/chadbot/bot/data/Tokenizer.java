package chadbot.bot.data;

public class Tokenizer {

    public Tokenizer(){}


    public static String[] parseInput (String input) {
        if(input != null && input != "" && input != " ") {
            return input.split("\\W+");
        }
        return new String[0];
    }


}