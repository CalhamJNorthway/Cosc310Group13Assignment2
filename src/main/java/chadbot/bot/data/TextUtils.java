package chadbot.bot.data;

public class TextUtils {

    public static boolean isEmpty(String text) {
        if(text == null) {
            return true;
        }
        text = text.trim();
        return text.isEmpty();
    }
}
