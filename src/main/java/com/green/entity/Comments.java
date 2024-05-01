package com.green.entity;

import com.green.dto.CommentDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
		name = "COMMENTS_SEQ_GENERATOR", 
		sequenceName   = "COMMENTS_SEQ",   
		initialValue   = 1,       // 초기값
		allocationSize = 1)       // 증가치
public class Comments {
	@Id                  // PK
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "COMMENTS_SEQ_GENERATOR")
	private Long    id;
	
	@ManyToOne                     // 관계 : 다대일 설정 (comments <-> article) 
	@JoinColumn(name="article_id") // FK column (부모키 Article id column) 
	private Article article;       // 연결될 entity 객체 이름
	
	// @Column(name="nick_name", nullable=false, length=50)
	@Column
	private String  nickname;
	
	@Column
	private String  body;

	public static Comments createComment(
			CommentDto dto, Article article) {
		if(dto.getId() != null)  // 입력된 댓글에 id 존재 시. 
			throw new IllegalArgumentException("댓글 생성 실패. 댓글 ID가 없어야 합니다");
		if(dto.getArticleId() != article.getId() ) // 입력받은 게시글의 id  !=  조회한 게시글 id 
			throw new IllegalArgumentException("댓글 생성 실패. 게시글 ID가 잘못되었습니다");
		
		return new Comments(
			dto.getId(),          // 입력 받은 댓글 ID
			article,              // 조회한 부모 게시글 정보 (article id)
			dto.getNickname(),    // 입력받은 댓글 닉
			dto.getBody()         // 입력받은 댓글 내용
			);
	}

	public void patch(CommentDto dto) {
		if(this.id  != dto.getId() )
			throw new IllegalArgumentException("댓글 수정 실패. 잘못된 아이디가 입력되었습니다.");
		if(dto.getNickname() != null)           // 입력받은 수정할 닉네임이 존재하면
			this.nickname = dto.getNickname();
		if(dto.getBody()     != null)           // 입력받은 수정할 댓글 내용이 존재하면
			this.body     = dto.getBody();
		
	}
}
