package com.jojo.textspeech;

import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.AudioEncoding;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

public class TextToSpeechUtil {
	
	public static final String PATH = "src/main/resources/static/mp3/";
	/**
	 * Demonstrates using the Text to Speech client to synthesize text or ssml.
	 *
	 * @param text the raw text to be synthesized. (e.g., "Hello there!")
	 * @throws Exception on TextToSpeechClient Errors.
	 */
	public static ByteString synthesizeText(String text) throws Exception {
	  // Instantiates a client
	  try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
	    // Set the text input to be synthesized
	    SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

	    // Build the voice request
	    VoiceSelectionParams voice =
	        VoiceSelectionParams.newBuilder()
	            .setLanguageCode("ja-JP") // languageCode = "en_us"
	            .setSsmlGender(SsmlVoiceGender.MALE) // ssmlVoiceGender = SsmlVoiceGender.FEMALE
	            .build();

	    // Select the type of audio file you want returned
	    AudioConfig audioConfig =
	        AudioConfig.newBuilder()
	            .setAudioEncoding(AudioEncoding.MP3) // MP3 audio.
	            .build();

	    // Perform the text-to-speech request
	    SynthesizeSpeechResponse response =
	        textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

	    // Get the audio contents from the response
	    ByteString audioContents = response.getAudioContent();
	    return audioContents;
//	    // Write the response to the output file.
//	    try (OutputStream out = new FileOutputStream("output.mp3")) {
//	      out.write(audioContents.toByteArray());
//	      System.out.println("Audio content written to file \"output.mp3\"");
//	      return audioContents;
//	    }
	  }
	}
}
