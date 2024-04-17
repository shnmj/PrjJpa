package com.green.dto;

import com.green.entity.Article;

public class ArticleDto {
	private String title;
	private String content;
	
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
	public Article toEntity() {
		Article article = new Article(null, title, content);
		return article;
	}
	
	
	
	
}
