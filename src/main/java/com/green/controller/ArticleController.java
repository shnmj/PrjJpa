package com.green.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.green.dto.ArticleDto;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	// data 입력
	@GetMapping("/articles/WriteForm") // 교재 : articles = new
	public String writeForm() { 
		return "articles/write"; //  articles/write .mustache
	}
	
	// data 저장
	// 405 error = method="POST" 인데 @GetMapping
	// @GetMapping("/articles/Write")
	// payload에 Form data (입력한 데이터가 담겨서 전송 -> title : aaa )
	@PostMapping("/articles/Write")
	public String write(ArticleDto articleDto ) {
		// 넘어온 데이터 확인
		System.out.println("결과 : " + articleDto.toString() );  
		// 교재 : ArticleForm  Dto를 toString으로 출력
		
		// db 에 저장 (h2 article table)
		// 1. Dto -> Entity로 보냄 // Entity = 그 자체로 db의 Table 역할
		Article article = articleDto.toEntity();  // article.mapper같은 개념
		
		// 2. Repository(interface)를 사용하여 Entity를 영구적으로 저장
		Article saved = articleRepository.save(article); //  자동 INSERT 
		System.out.println("saved : "  + saved);
		// 담기만 하면 저장x = Article
		
		return "redirect:/articles/List";
	}
	
	// 1번 data 조회
	// java.lang.IllegalArgumentException: Name for argument of type [java.lang.Long] not specified
	// 해결1. @PathVariable(value="id")
	// 해결2. sts 설정 추가 방식 : ✅Properties - java compiler - enable, store 체크
	// No default constructor for entity ... --> Arcicle에 @NoArgsConst 추가
	@GetMapping("/articles/{id}") // PathVariable = GET 방식
	public String view(
			@PathVariable(value="id") Long id,  // dto 제외 일반 변수는 value 입력 (이게 귀찮으면 해결2 ㄱㄱ)
			Model model) {
	// 1번 방법 Type mismatch Error
		// Article articleEntity = articleRepository.findById(id); // 공고 번호로 지원자 뽑을 때 findAllbyid
		
		// Optional<Article> articleEntity = articleRepository.findById(id); --> 교재에서 비추 
		// Optional = 값이 있으면 Article 을 return, 없으면 null
		
	// 2번 방법 --> 값이 있으면 담고, 없으면 or에 의해 null 넘어감
		Article articleEntity = articleRepository.findById(id).orElse(null); 
		
		System.out.println("1번 조회 결과 : " + articleEntity);
		model.addAttribute("article", articleEntity); // 조회한 결과 -> model에 담음
		return "articles/view";  // view.mustache
	}
	
	@GetMapping("/articles/List")
	public String list(Model model) { 
		
		// List<Article> articleEntityList = articleRepository.findAll();
		// 1. 오류 처리 1번 방법
		// List<Article> articleEntityList = (List<Article>) articleRepository.findAll(); // null return X
		
		// 2. ArticleRepository (interface)에 함수를 등록 
		
		List<Article> articleEntityList = articleRepository.findAll(); 
		System.out.println("전체목록 : " + articleEntityList);
		// findall 자체가 array type이 아님 -> 맞춰 달라
		
		model.addAttribute("articleList", articleEntityList);
		return "articles/list"; 
	}
	
	// 데이터 수정 페이지로 이동
	@GetMapping("/articles/{id}/EditForm") 
	public String editForm(
			@PathVariable(value="id") Long id,
			Model model) {
		
		return "articles/edit";
	
	}
	
	// 데이터 수정
	@GetMapping("/articles/{id}/Edit") 
	public String edit() {
		
		// db  수정
		return "redirect:/articles/List/";
				
	
	}

	
	
	
	
	
}
