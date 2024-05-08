package com.green.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 실제 database 의 table 구조를 만든다 : Create Table 을 실행함
// import jakarta.persistence.
@Entity
@NoArgsConstructor    // 기본생성자 : default constructor
@Getter
@Setter
@SequenceGenerator(name="ARTICLE_SEQ_GENERATOR", 
   sequenceName   = "ARTICLE_SEQ", 
   initialValue   = 1,     // 초기값
   allocationSize = 1 )   // 증가치
public class Article {
	// primary key        : @id
	// 값을 자동으로 채움 : @GeneratedValue
	@Id
	//@GeneratedValue  // 번호자동증가
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
	    generator = "ARTICLE_SEQ_GENERATOR")
	//  create sequence article_seq start with 1 increment by 50
	private  Long    id;      // long : null 입력안됨 -> Long
	@Column
	private  String  title;
	@Column
	private  String  content;
	
	// 자바에 사용하기 위해 만든 내용들
	// 생성자 (@AllArsConstructor)
	public Article(Long id, String title, String content) {		
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	// @ToString
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
	}

	//  수정하기 위한 용도를 추가
	public void patch(Article article) {
		if(article.title != null)
			this.title   = article.title;
		if(article.content != null)
			this.content = article.content;		
	}
	
}





