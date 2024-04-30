package com.green.entity;

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
}
