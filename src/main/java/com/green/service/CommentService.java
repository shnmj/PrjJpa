package com.green.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.CommentDto;
import com.green.entity.Article;
import com.green.entity.Comments;
import com.green.repository.ArticleRepository;
import com.green.repository.CommentRepository;

import jakarta.transaction.Transactional;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ArticleRepository articleRepository;

	
	// 1. 댓글 조회
	public List<CommentDto> comments(Long articleId) {
		/*		
		// 1. 댓글을 db에서 articleId로 조회 -> Entity에 담음
		List<Comments> commentList = commentRepository.findByArticleId(articleId);
		
		// 2. Entity -> Dto 로 변환
		List<CommentDto> dtos = new ArrayList<CommentDto>();
		for (int i = 0; i < commentList.size(); i++) {
			Comments   c   = commentList.get(i);
			CommentDto dto = CommentDto.createCommentDto(c); 
			dtos.add(dto);
		}
		
		// 3. 결과 반환

		return dtos;
		*/
		
		/*
   		  집합.map() : srtream 전용 함수 - 집합(Collection)에 대해 El들을 반복 조작 
		  .map() == .forEach() : 둘 다 배열을 모두 조작
		  차이 : .map()     = 내부 El를 값이나 사이즈가 변경할 때 사용(전체 대문자로)
		         .forEach() = 내부 El 값 변경 x 
		*/
		
		// comments는 날라올때 entity type
		return commentRepository.findByArticleId(articleId)  // arratyListId
				.stream()   // stream으로 전환 - filter or Map 명령 사용 가능
				.map(comment -> CommentDto.createCommentDto(comment)) // 하나씩 꺼내서 map에 담 
				.collect(Collectors.toList()); // stream을 다시 arrayList로 변환
	}

	@Transactional // Error 발생 시 db를 rollback 하기 위해 (throw 사용 이유)
	public CommentDto create(Long articleId, CommentDto dto) {
		// 1. 게시글 조회 및 조회실패 예외 발생
		// 게시글에 존재하지 않는 articleId 가 넘어오면 조회 결과 x -> Throw 
		Article article = articleRepository.findById(articleId)
				.orElseThrow( () -> new IllegalArgumentException(
						"댓글 생성 실패. 해당 게시물이 없습니다") ); // 조회&예외 처리 동시
		
		// 2. 댓글 Entity 생성  ->  저장 할 data(comments) 생성
		Comments comments = Comments.createComment(dto, article);
		
		// 3. 댓글 Entity -> db 저장
		Comments  created  = commentRepository.save(comments);
		
		
		// 4. 저장된 Comments tpye created -> dto 로 변환 후 controller return
		// 변환 이유 : controller 에서 entity type을 사용 x
		return CommentDto.createCommentDto(created);
		
	}
	
	// 3. 댓글 수정
	@Transactional
	public CommentDto update(Long id, CommentDto dto) {
		// 1. 댓글 조회 및 예외 발생
		Comments target = commentRepository.findById(id)  // target : 수정할 데이터
				.orElseThrow( 
						() -> new IllegalArgumentException(
								"댓글 수정 실패. 수정할 댓글이 없습니다.")
				);
		
		// 2. 댓글 수정 : 조회한 데이터의 내용 수정(class 안 내용 변경)
		// dto    = 수정할 입력받은 데이터
		// target = 수정할 원본     데이터
		target.patch(dto);  // target <- dto(nickname, body) 넣 
		
		// 3. db에 정보를 수정
		Comments updated = commentRepository.save(target);
		
		// 4. 결과 update -> commentDto로 변경하여 return
		return CommentDto.createCommentDto(updated); 
	}
	
	@Transactional
	public CommentDto delete(Long id) {
		// 1. 삭제할 댓글 조회 및 예외 처리
		Comments target = commentRepository.findById(id)
				.orElseThrow(
						() -> new IllegalArgumentException(
								"댓글 삭제 실패. 대상이 없습니다.")
						); 
		// 2. 실제 db에서 삭제
		commentRepository.delete(target);
		
		// 3. 삭제 댓글을 dto로 반환
		return CommentDto.createCommentDto(target);
	}


}
