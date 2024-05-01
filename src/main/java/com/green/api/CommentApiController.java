package com.green.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.green.dto.CommentDto;
import com.green.entity.Comments;
import com.green.service.CommentService;

import jakarta.transaction.Transactional;


// 댓글 조회, 추가, 수정, 삭제
@RestController
public class CommentApiController {
	
	@Autowired
	private CommentService commentService;
	
	// 1. 댓글 조회(GET)
	@GetMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<List<CommentDto>> comments(
			@PathVariable Long articleId) {
		
		// 정보조회를 서비스에게 위임 (db에서 읽어와서 결과만 처리 : 결과=comment.dto)
		List<CommentDto> dtos = commentService.comments(articleId);
		
		// ResponseEntity : status.ok + dtos(ArrayList -> json으로 출력) 를 return
		// --> @RestCont 라서.
		return ResponseEntity.status(HttpStatus.OK).body(dtos);
	}
	
	// 2. 댓글 생성(POST)
	// http://localhost:9090/api/articles/{articleId}/comments
	
	/*
	  입력 data      : {"articleId":4, "nickname":"Hoon", "body":"이프 온리"}
 	  결과           : {"id": null, "articleId": 4, "nickname": "SHIN", "body": "이프 온리"}
	  Error 입력     : {id : 4, "articleId": 4, "nickname": "SHIN", "body": "이프 온리"}
	  결과 400 Error : {"id":4, 입력data key json type "" 안에 저장 
	  500 Error      : 댓글 안 
	*/
	@Transactional
	@PostMapping("/api/articles/{articleId}/comments")
	public ResponseEntity<CommentDto> create(
			@PathVariable Long articleId,     // {articleId} : 게시글 번호
			@RequestBody CommentDto dto  ) {  // 저장을 위해 입력된 자료들(input, select : type으로 넘어온) 
		CommentDto createdDto = commentService.create(articleId, dto);
		// 결과를 응답
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		
	}
	
	// 3. 댓글 수정(PATCH)
	// Patch   http://localhost:9090/api/comments/7
	// 수정 전 data {"article_id":6, "id":7, "body"="조깅" "nickname"= "Park"}
	// 입력    data {"article_id":6, "id":7, "body"="수영" "nickname"= "Park2"}
	@PatchMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> update (
			@PathVariable  Long       id,
			@RequestBody   CommentDto dto  // 수정 할 데이터를 가지고 있
			) {
				
		CommentDto updateDto = commentService.update(id, dto);
		return ResponseEntity.status(HttpStatus.OK).body(updateDto);
		
	}
	
	// 4. 댓글 삭제(DELETE)
	// Delete http://localhost:9090/api/comments/7
	// @DeleteMapping("/api/comments/{id}")
	@DeleteMapping("/api/comments/{id}")
	public ResponseEntity<CommentDto> delete (@PathVariable Long id) {
		CommentDto deletedDto = commentService.delete(id);
		return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
	}
	
	
	
}
