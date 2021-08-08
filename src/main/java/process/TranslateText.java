package process;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateText {
    Translate translate = TranslateOptions.getDefaultInstance().getService();

    public TranslateText(){};

    /**
     * Add audio transcription
     * @param audioTextTranscription
     * Detect the language used
     * Translate Audio Text Transcription By determine the source language and target language
     */
    public TranslateText(String audioTextTranscription,String targetLanguageCode){

        Detection detection = translate.detect(audioTextTranscription);

        Translation translation = translate.translate(audioTextTranscription,
                Translate.TranslateOption.sourceLanguage(detection.getLanguage()),
                Translate.TranslateOption.targetLanguage(targetLanguageCode),
                Translate.TranslateOption.model("nmt"));
        System.out.printf("TranslatedText:\nText: %s\n", translation.getTranslatedText());
    }
}
