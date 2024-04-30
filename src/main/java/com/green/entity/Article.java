package com.green.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id; // pk 영구적 생성
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 실제 database의 table 구조를 생성 = create table 
@Entity
@NoArgsConstructor 
@Getter
@Setter
public class Article {
	@Id                    // primary key	
	@GeneratedValue        // 값 자동 채움
	private Long   id;     // Long : null 입력x -> Long
	@Column  //  Column() 로 type 설정 가능
	private String title;
	@Column
	private String content;
	
	// java에 사용 위한 내용들
	// toString
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	} 

	// Constructor
	public Article(Long id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public void patch(Article article) {
		if(article.title   != null)
			this.title   = article.title;
		if(article.content != null)
			this.content = article.content;
		
	}
	
	
	
}
