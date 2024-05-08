package com.green.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.dto.UserDto;
import com.green.entity.User;
import com.green.service.UserDetailService;
import com.green.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserApiController {

    private final UserService       userService;
    private final UserDetailService userDetailService;

    // 회원가입
    @PostMapping("/user")
    public String signup(UserDto userDto) {
        userService.save(userDto);
        return "redirect:/login";
    }

	// 로그아웃
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(
        		request, 
        		response, 
        		SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }

}

