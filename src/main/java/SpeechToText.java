import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.collect.Lists;
import process.AudioTranscription;
import process.TranslateText;

import java.io.FileInputStream;

public class SpeechToText{
    public static void main(String[] args) throws Exception {

        // [START auth_cloud_explicit]
        //Add path of Service Account
        GoogleCredentials credentials =
                GoogleCredentials.fromStream(new FileInputStream("F:\\Environment\\api-project-204667169246-bab65b95321a.json"))
                        .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));

        /**
         * Add the path of media you want extract transcript with it's language
         * and determine the duration with the start and the end of media you want to transcript
         */
        AudioTranscription audioTranscription = new AudioTranscription();
        String transcription = audioTranscription
                .transcript("F:\\VideoProcess\\motivational speech.mp3","en",5.0,10.0);
        System.out.println("Transcription:\n" + transcription);

        /**
         * Add audioTextTranscription you want to translate with target language
         */
        TranslateText translateText = new TranslateText(transcription,"ar");


    }
}
