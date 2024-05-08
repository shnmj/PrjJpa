package com.green.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "usersjpa")  //  table 이름과 클래스 이름이 다를때 사용 (oracle은 user table 못만듬)
// @NoArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Entity
public class User implements UserDetails { // UserDetails 을 상속받아 인증객체로 사용
	// import org.springframework.security.core.userdetails.UserDetails;
	// p 207 스프링 부트 백엔드 개발자 되기 

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    
    // @Builder : 생성자 대신 아래 문법으로 호출가능 가독성높고, 파라미터 순서무관사용가능
    //User user = User.builder()
	//                .email("email")
    //                .auth(auth)
    //                .password("password")	
	//                .build();

    @Builder
    public User(String email, String password, String auth) {
        this.email = email;
        this.password = password;
    }

    @Override     // 권한 반환
    public Collection<? extends GrantedAuthority> getAuthorities() {
    	// 사용자가 가지고 있는 권한의 목록을 반환, 현재  user 권한만 반환
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override    // 사용자의 id (고유한 값)를 반환
    public String getUsername() {
        return email;
    }

    @Override   // 사용자 비밀번호 반환
    public String getPassword() {
        return password;
    }

    @Override // 계정 만료여부 반환
    public boolean isAccountNonExpired() {
    	// 계정이 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }
    
    @Override  // 패스워드 만료여부 반환
    public boolean isCredentialsNonExpired() {
    	// 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }
    
    @Override  // 계정 사용 가능 여부 반환
    public boolean isEnabled() {
    	// 계정 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }

	@Override  // 계정 잠금 여부 반환
	public boolean isAccountNonLocked() {
		// 계정 잠금 여부 확인하는 로직
		return true; // true -> 잠금되지 않았음
	}

}
