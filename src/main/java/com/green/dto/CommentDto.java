package com.green.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.entity.Comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

	private Long   id;         // 댓글 id
	@JsonProperty("article_Id")
	private Long   articleId;  // article에 있는 부모글 id
	private String nickname;   
	private String body;
	
	// CommentDto <- Comments(db에서 조회한) -> entity type
	public static CommentDto // entity에서 가져와서 가공
			createCommentDto(Comments comments) { //  static = 객체 이름으로 당겨 쓴다
		return new CommentDto (
			comments.getId(),
			comments.getArticle().getId(),
			comments.getNickname(),
			comments.getBody()
			);
	}
}