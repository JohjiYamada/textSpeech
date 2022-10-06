package com.jojo.textspeech.controllers;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.protobuf.ByteString;
import com.jojo.textspeech.TextToSpeechUtil;

@Controller
public class TextSpeechController {
	
	private String text = "進撃の巨人のセリフ 「心臓を捧げよ」  どういう意味ですか？  また、どのような状況で言ったのですか？  読んだことがないのでよくわからないのですが  よくいろんな場所でこの言葉が使われるので  教えてください  アルミンに関係があるらしいのですが…";
	
	@RequestMapping("/")
	private Object index() throws Exception {
		
		ByteString audioContents = TextToSpeechUtil.synthesizeText(text);
		try (OutputStream out = new FileOutputStream(TextToSpeechUtil.PATH+"output.mp3")) {
	      out.write(audioContents.toByteArray());
//	      System.out.println("Audio content written to file \"output.mp3\"");
	    }
		return "/index.html";
	}

//	public static void main(String[] args) throws Exception {
//		TextSpeechController t = new TextSpeechController();
//		t.index();
//	}
	@GetMapping(value = "/file")
	public @ResponseBody byte[] getImage() throws Exception {
		ByteString audioContents = TextToSpeechUtil.synthesizeText(text);
		return audioContents.toByteArray();
	}
}


