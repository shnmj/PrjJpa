package com.green.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	
	@GetMapping("/hi")
	public String hi() {
		return "greetings";   // greentings.msutache
		// 화면 출력할 template
		// resources/template/greetings.mustache
	}
	
	@GetMapping("/hi2")
	public String hi2(Model model) {
		model.addAttribute("username", "SHIN");
		return "greetings2"; // greetings2.mustache
	}
	
	@GetMapping("/hi3")
	public String hi3(Model model) {
		model.addAttribute("username", "KIM");
		return "greetings3"; // greetings3.mustache
	}
	
	@GetMapping("/hi4")
	public String hi4(Model model) {
		model.addAttribute("username", "LEE");
		return "greetings4"; // greetings4.mustache
	}
	
}
