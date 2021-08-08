package process;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AudioTranscription {
    public   AudioTranscription(){}

    private String transcription;

    /**
     *Add path of media you want to get transcription
     * @param filePath
     * Add language code of media's language such as 'English' -->> 'en'
     * @param languageCode
     * The start of transcript with seconds and milli seconds like '5.400'
     * @param start
     * The end of transcript with seconds and milli seconds like '10.0'
     * @param end
     * @return
     */
    public String  transcript(String filePath ,String languageCode, double start , double end){

        MediaProcessor mediaProcessor = new MediaProcessor();
        mediaProcessor.ConvertVideoToAudio(filePath,start,end);

        try (SpeechClient speechClient = SpeechClient.create()) {

            // When enabled, trascription results may include punctuation
            // (available for select languages).
            boolean enableAutomaticPunctuation = true;

            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEnableAutomaticPunctuation(enableAutomaticPunctuation)
                            .setLanguageCode(languageCode)
                            .setAudioChannelCount(2)
                            .build();
            Path path = Paths.get(mediaProcessor.getTargetFilePath());
            byte[] data = Files.readAllBytes(path);
            ByteString content = ByteString.copyFrom(data);
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(content).build();
            RecognizeRequest request =
                    RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
            RecognizeResponse response = speechClient.recognize(request);
            for (SpeechRecognitionResult result : response.getResultsList()) {
                // First alternative is the most probable result
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcription =  alternative.getTranscript();
            }
        } catch (Exception exception) {
            System.err.println("Failed to create the client due to: " + exception);
        }
        return transcription;
    }



}
