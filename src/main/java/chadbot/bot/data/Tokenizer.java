package chadbot.bot.data;

import java.util.StringTokenizer;

public class Tokenizer {

    public Tokenizer(){}


    public static String[] tokenizeWithoutPunctuation(String input) {
        if(!TextUtils.isEmpty(input)) {
            return input.split("\\W+");
        }
        return new String[0];
    }

    public static String[] tokenizeWithPunctuation(String input) {
        if(!TextUtils.isEmpty(input)) {
            input = input.replaceAll("\\p{Punct}", " $0 ");
            return input.split("\\s+");
        }
        return new String[0];
    }


}