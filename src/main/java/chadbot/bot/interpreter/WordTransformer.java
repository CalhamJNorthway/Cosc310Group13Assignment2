package chadbot.bot.interpreter;

import chadbot.bot.data.word.Word;

import java.io.Closeable;

public interface WordTransformer{
    Word transform(Word word, String[] context, int indexInContext);
}
