package com.automationexercices.media;

import com.automation.remarks.video.RecorderFactory;
import com.automation.remarks.video.recorder.IVideoRecorder;
import com.automation.remarks.video.recorder.VideoRecorder;
import com.automationexercices.utils.dataReader.PropertyReader;
import com.automationexercices.utils.logs.LogsManager;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;
import ws.schild.jave.encode.VideoAttributes;

import java.io.File;

public class ScreenRecordManager {
    public final static String RECORDINGS_PATH = "test-output/recordings/";
    private static final ThreadLocal<IVideoRecorder> recorder = new ThreadLocal<>();

    /**
     * Starts screen recording.
     */
    public static void startRecording() {
        if (PropertyReader.getProperty("recordTests").equalsIgnoreCase("true")) {
            try {
                // Ensure the recordings directory exists
                File recordingsDir = new File(RECORDINGS_PATH);
                if (!recordingsDir.exists()) {
                    recordingsDir.mkdirs();
                }
                // Configure the recorder to use the custom directory and file name
                if (PropertyReader.getProperty("executionType").equalsIgnoreCase("local")) {
                    recorder.set(RecorderFactory.getRecorder(VideoRecorder.conf().recorderType()));
                    // Start recording
                    recorder.get().start();
                    LogsManager.info("Recording Started");

                }


            } catch (Exception e) {
                LogsManager.error("Failed to start recording: " + e.getMessage());
            }

        }
    }
    /**
     * Stops screen recording and returns the video as an InputStream.
     */
    public static void stopRecording(String testMethodName) {
        try {
            if (recorder.get() != null) {
                // Stop the recorder and get the video file
                String videoFilePath = String.valueOf(recorder.get().stopAndSave(testMethodName));
                File videoFile = new File(videoFilePath);

                // Log the file path for debugging
                LogsManager.info("Video file saved at: " + videoFile.getAbsolutePath());


                // Convert the video to .mp4 format
                File mp4File = encodeRecording(videoFile);
                LogsManager.info("Recording Stopped and Converted to MP4: " + mp4File.getName());
            }
        } catch (Exception e) {
            LogsManager.error("Failed to stop recording: " + e.getMessage());
        } finally {
            recorder.remove();
        }
    }

    /**
     * Converts a video file to .mp4 format.
     *
     * @param sourceFile The input video file.
     * @return The converted .mp4 file.
     */
    private static File encodeRecording(File sourceFile) {
        File targetFile = new File(sourceFile.getParent(), sourceFile.getName().replace(".avi", ".mp4"));
        try {

            AudioAttributes audio = new AudioAttributes();
            audio.setCodec("aac"); // AAC audio codec

            VideoAttributes video = new VideoAttributes();
            video.setCodec("libx264"); // H.264 video codec

            EncodingAttributes encodingAttributes = new EncodingAttributes();
            encodingAttributes.setOutputFormat("mp4"); // Output format
            encodingAttributes.setAudioAttributes(audio);
            encodingAttributes.setVideoAttributes(video);

            // Encode the video
            Encoder encoder = new Encoder();
            encoder.encode(new MultimediaObject(sourceFile), targetFile, encodingAttributes);

            // Delete the original .avi file after conversion
            if (targetFile.exists()) {
                sourceFile.delete();
                LogsManager.info("Deleted original AVI file: " + sourceFile.getName());
            }
        } catch (EncoderException e) {
            LogsManager.error("Failed to convert video to MP4: " + e.getMessage());
        }
        return targetFile;
    }
}
