package chadbot.bot.interpreter;

import chadbot.bot.data.word.PartOfSpeechTag;
import chadbot.bot.data.word.Word;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;

import java.io.IOException;
import java.io.InputStream;

public class PartOfSpeechTransformer implements WordTransformer {

    private POSTaggerME posTaggerME;

    public PartOfSpeechTransformer(String modelName) {
        try (InputStream modelIn = PartOfSpeechTransformer.class.getResourceAsStream(modelName)){
            POSModel model = new POSModel(modelIn);
            posTaggerME = new POSTaggerME(model);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Exception ex = new IOException("The specified Part Of Speech Model file \"" + modelName + "\" was not found");
            ex.setStackTrace(e.getStackTrace());
            ex.printStackTrace();
        }
    }

    @Override
    public Word transform(Word word, String[] context, int indexInContext) {
        if(posTaggerME == null) {
            return new Word(word);
        }

        Word w = new Word(word);
        String[] tags = posTaggerME.tag(context);
        w.setPosTag(PartOfSpeechTag.valueOf(tags[indexInContext]));

        return w;
    }

}
