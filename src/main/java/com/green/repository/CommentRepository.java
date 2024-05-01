package com.green.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.green.entity.Comments;

public interface CommentRepository 
	extends JpaRepository<Comments, Long> {
	
		// @Query 를 통해 findByArticleId() 를 실행
		// @Query = jap 기능 사용x , @Query 안의 sql 사용
		// findByArticleId() 함수 = Comments Table Column을 사용해서 생성
	
		// Native Query Method : Oracle 전용 문법으로 작성된 query를 입력하여 조회
	    // native = true : oracle 전용 함수 / false : JPA 함수(JPQL 문법)
	    // :articleId Param로 조회 (넘어온 값)
		@Query(value="SELECT * FROM comments WHERE article_id=:articleId",
				nativeQuery=true) // NVL = if null
		List<Comments> findByArticleId(Long articleId);
		
		// native query xml : 
		// resources/META-INF/orm.mxl  // 폴더와 파일 생성 필요
		// orm.xml 에 sql 을 저장해서 findByNickname() 함수 호출
		List<Comments> findByNickname(String nickname);

}