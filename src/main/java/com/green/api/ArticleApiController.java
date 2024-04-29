package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.service.ArticleService;

@RestController
public class ArticleApiController {
	
	@Autowired
	private ArticleService articleService;
	
	// GET LIST : 목록 조회
	@GetMapping("/api/articles")
	public List<Article> index() {
		return articleService.index();  // index안  request 포함 x 
	}
	
	// GET ID   : ID로 조회
	@GetMapping("/api/articles/{id}")
	public Article show(@PathVariable Long id) {
		Article article = articleService.show(id);
		return article;
	}
	
	// POST     : INSERT
	// 결과     : 저장된 article 객체, 상태코드 <- 저장되었습니다.
	// ResponseEntity<Article> = Article Data 와 http state code : 200
	// {"id":12, "title":"새글", "content":"새글 내용"}          : 400 error
	// {"title":"새글", "content":"새글 내용"}                   : 200
	@PostMapping("/api/articles")
	public ResponseEntity<Article> create(
			@RequestBody ArticleForm dto   // reqbody : 넘어오는 값 json
			) {
		Article created = articleService.create(dto);  // result 이후 만들어진 data
		ResponseEntity<Article> result
			= (created != null) 
			? ResponseEntity.status(HttpStatus.OK).body(created)         // 200
			: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();     // 400  .build() == 값(body)이 없는 경우. 
		return result;

	}
	// Generic(param type을 객체 (type T)을 사용해라
	// <T>      : class type, <?> T는 외부에 입력된 type
	
	// PATCH    : UPDATE
	
	// DELETE   : DELETE
}
