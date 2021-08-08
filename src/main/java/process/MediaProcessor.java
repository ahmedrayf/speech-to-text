package process;

import java.io.File;

import ws.schild.jave.Encoder;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

public class MediaProcessor {

    //Where the converted video will be saved to process transcription on
    private String targetFilePath = "C:\\Users\\ADMIN\\Videos\\Speech To Text\\audio.wav";
    public String getTargetFilePath() {return targetFilePath;}

    /**
     * the path of media that will be converted and trim
     * @param sourceFilePath
     * start of trim
     * @param start
     * end of trim
     * @param end
     */
    public void ConvertVideoToAudio(String sourceFilePath , double start , double end){
        end-=start;

        try {
            File source = new File(sourceFilePath);
            File target = new File(targetFilePath);

            //Audio Attributes
            AudioAttributes audio = new AudioAttributes();

            //Encoding attributes
            EncodingAttributes attrs = new EncodingAttributes();
            attrs.setOffset((float)start);
            attrs.setDuration((float)end);
            attrs.setOutputFormat("wav");
            attrs.setAudioAttributes(audio);

            //Encode
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(source), target, attrs);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
