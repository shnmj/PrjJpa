package com.green.dto;

import com.green.entity.Article;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ArticleForm {
	private Long   id;      // 수정을 위해 추가
	private String title;
	private String content;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	// Cons
	public ArticleForm(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		
	// toSt
	}
	@Override
	public String toString() {
		return "ArticleForm [id=" + id + ", title=" + title + ", content=" + content + "]";
	}
	
	// Method (data 중심의 클래스 vo, dto, entity / 기능 중심의 클래스 calc, controller)
	public Article toEntity() {
		
		return new Article(id, title, content);
		
	}
	
	
	
}
