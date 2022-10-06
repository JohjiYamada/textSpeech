package com.jojo.textspeech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TextSpeechController {
	
	@RequestMapping("/")
	private String index() {
		return "/index.html";
	}
}
