package com.green.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@GetMapping("/articles/WriteForm")
	public String writeForm() {
		return "articles/write";
	}
	
	// 405 error = method="POST" 인데 @GetMapping
	// @GetMapping("/articles/Write")
	@PostMapping("/articles/Write")
	public String write(ArticleDto articleDto ) {
		// 넘어온 데이터 확인
		System.out.println(articleDto.toString() );  // 교재 : ArticleForm
		
		// db 에 저장 (h2 article table)
		// 1. Dto -> Entity로 보냄 // Entity = 그 자체로 db의 Table 역할
		Article article = articleDto.toEntity();
		
		// 2. Repository(interface)를 사용하여 Entity를 영구적으로 저장
		Article saved = articleRepository.save(article);
		
		return "redirect:/articles/List";
	}
	
}
