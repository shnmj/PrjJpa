package com.green.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.dto.ArticleForm;
import com.green.entity.Article;
import com.green.repository.ArticleRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;
	
	// article 목록 조회
	public List<Article> index() {
		// db 저장 전 작업할 코딩 입력
		// jpa 함수 : findAll()
		return articleRepository.findAll();
	}

	// article ID로 조회
	public Article show(Long id) {
		Article article = articleRepository.findById(id).orElse(null);
		return  article;
	}

	public Article create(ArticleForm dto) {
		
		// 입력 data dto : {"title" : "새 글", content" : "새 글 내용"}
		Article article = dto.toEntity();
		
		// create 는 생성 요청이고 번호는 자동증가이므로 번호 필요x
		if(article.getId() != null )
			return null; // ID가 존재하면 Error 발생
		
		Article saved = articleRepository.save(article);
		return  saved;
	}

	public Article update(Long id, ArticleForm dto) {
		// 1. DTO -> Entity로 변환 
		Article article = dto.toEntity();
		log.info("id:{}, article:{}", id, article.toString() );  // {}가 param
		
		// 2. target(기존글)을 id로 조회
		Article target = articleRepository.findById(id).orElse(null);
		
		// 3. 잘못된 요청을 처리
		if (target == null || id != article.getId() ) {
			log.info("id:{}, article:{}", id, article.toString() );
			return null;  // 조회한 자료가 없거나 id가 틀리면
		}
		
		// 4. 업데이트 및 정상 응답 (ok)
		target.patch(article);
		Article updated = articleRepository.save(target);
		return  updated;
	}

	public Article delete(Long id) {
		// 1. target(삭제할 글)을 id로 조회
		Article target = articleRepository.findById(id).orElse(null);
		
		// 2. 잘못된 요청을 처리
		if (target == null) {
			return null;  // 조회한 자료가 없거나 id가 틀리면
		}
		
		// ㄷ. 업데이트 및 정상 응답 (ok)
		articleRepository.delete(target);
		return  target;
	
	}

	public List<Article> createArticles(List<ArticleForm> dtos) {
		// 1. DTO 묶음을 Entity 묶음으로 변환
		List<Article> articleList = new ArrayList<Article>();
			for(ArticleForm dto : dtos) {
				Article article = dto.toEntity();
				articleList.add(article);
			}
			
			
		// 2. Entity 묶음을 db에 저장
			for(int i=0; i<articleList.size(); i++) {
				Article article = articleList.get(i);
				articleRepository.save(article);
			}
			
		// 3. 강제 Error 발생 시키기 (id: -1L)은 X
			articleRepository.findById(-1L).orElseThrow( () -> 
				new IllegalArgumentException("!!!결재 실패!!!"));
			
		return null;
	}

	
	// transaction 기능 활성화
	@Transactional    // 이거만 추가
	public List<Article> createArticles2(List<ArticleForm> dtos) {
		// 1. DTO 묶음을 Entity 묶음으로 변환
		List<Article> articleList = new ArrayList<Article>();
			for(ArticleForm dto : dtos) {
				Article article = dto.toEntity();
				articleList.add(article);
			}
			
		// 2. Entity 묶음을 db에 저장
			for(int i=0; i<articleList.size(); i++) {
				Article article = articleList.get(i);
				articleRepository.save(article);
			}
			
		// 3. 강제 Error 발생 시키기 (id: -1L)은 X
			articleRepository.findById(-1L).orElseThrow( () -> 
				new IllegalArgumentException("!!!결재 실패!!!"));
			
		return null;

	}
	
	

	
}
