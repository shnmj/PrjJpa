package com.green.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.green.entity.Article;

public interface ArticleRepository 
	extends CrudRepository<Article, Long> {
	
	@Override
	ArrayList<Article> findAll(); // 172p
	// findAll 재정의
	// crud를 상속받음 (interface도 가능)
	// extends 뒤가 class란걸 알림 -> save(mapper)란 단어를 써도 사용 가능
	
	// ArticleController 84 line 형 변환 오류 2번째 해결방법
    // 상속관계를 이용하여 List를 Iterable 로 UpCasting 하여 형변하지 않음
	
    // Iterable(I) <- Collection(C) <- List(I) <- ArrayList(C) 
	// --> Iterable의 상속을 받은 repo를 return하면 형 변환 없이 List 사용가능 
    
	

}
