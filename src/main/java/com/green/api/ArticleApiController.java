package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.service.ArticleService;

@RestController   // @Cont + @RespBody
public class ArticleApiController {
	
	@Autowired
	private ArticleService articleService;
	
	// GET LIST : 목록 조회
	@GetMapping(value="/api/articles", 
			produces = MediaType.APPLICATION_JSON_VALUE)
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
			@RequestBody ArticleForm dto   
			// Reqbody : json string으로 넘어오는 값을 java의 객체(ArtocleForm)로 저장
			) {
		Article created = articleService.create(dto);  // result 이후 만들어진 data
		ResponseEntity<Article> result
			= (created != null) 
			? ResponseEntity.status(HttpStatus.OK).body(created)         // 200
			: ResponseEntity.status(HttpStatus.BAD_REQUEST).build();     
				// 400 사용자 정의 에러  .build() == 값(body)이 없는 경우. 
		return result;

	}
	// Generic(param type을 객체 (type T)을 사용해라
	// <T>      : class type, <?> T는 외부에 입력된 type
	
	
	// PATCH    : UPDATE
	@PatchMapping("/api/articles/{id}")
	public ResponseEntity<Article> update(
			@PathVariable Long id, 
			@RequestBody  ArticleForm dto
			) {
		System.out.println("id:" + id + ",dto:" + dto);
		Article updated = articleService.update(id, dto);
		ResponseEntity<Article> result
			= (updated != null) 
			? ResponseEntity.status(HttpStatus.OK).body(updated)     
			: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
	return result;
	}
	
	// DELETE   : DELETE
	@DeleteMapping("/api/articles/{id}")
	public ResponseEntity<Article> delete(
			@PathVariable Long id, 
			@RequestBody  ArticleForm dto
			) {
		System.out.println("id:" + id + ",dto:" + dto);
		Article deleted = articleService.delete(id);
		ResponseEntity<Article> result
			= (deleted != null) 
			? ResponseEntity.status(HttpStatus.NO_CONTENT).build()     
			: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
	return result;
	}
	
	/*
	 [
	   {title: "시간 예약",   content : "1240"},
       {title: "테이블 지정", content :  "A12"},
	   {title: "메뉴 선택",   content :  "Branch A"}
	 ]
	*/
	
	// Transaction : 3개의 data 받아서 서비스 함수에 넘겨주고 결과 받음 
	@PostMapping("/api/transaction-test")
	public ResponseEntity<List<Article>> transactionTest(
		@RequestBody List<ArticleForm> dtos ) {
			
			List<Article> createdList = articleService.createArticles(dtos);
			
			ResponseEntity<List<Article>> result
				= (createdList != null) 
				? ResponseEntity.status(HttpStatus.OK).body(createdList)     
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
			
			return result;
		}
	
	@PostMapping("/api/transaction-test2")
	public ResponseEntity<List<Article>> transactionTest2(
		@RequestBody List<ArticleForm> dtos ) {
			
			List<Article> createdList = articleService.createArticles2(dtos);
			
			ResponseEntity<List<Article>> result
				= (createdList != null) 
				? ResponseEntity.status(HttpStatus.OK).body(createdList)     
				: ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
			
			return result;
		}
		
	
	}
	

