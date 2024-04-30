package com.green.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // pk 영구적 생성
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// 실제 database의 table 구조를 생성 = create table 
@Entity
@Data
@NoArgsConstructor   // 기본 생성자
@AllArgsConstructor  // 모든 인자 생성자
@SequenceGenerator(
		name = "ARTICLE_SEQ_GENERATOR", 
		sequenceName   = "ARTICLE_SEQ",   
		initialValue   = 1,       // 초기값
		allocationSize = 1)       // 증가치
public class Article {
	@Id                    // primary key	
	// @GeneratedValue        // 값 자동 채움 (번호 자동 증가)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
			generator = "ARTICLE_SEQ_GENERATOR") // 1씩 증가
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

	public void patch(Article article) {
		if(article.title   != null)
			this.title   = article.title;
		if(article.content != null)
			this.content = article.content;
		
	}
	
	
	
}
