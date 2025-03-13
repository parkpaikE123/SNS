package com.ryujm.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryujm.sns.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	// 회원가입 API
	@PostMapping("/join")
	public Map<String, String> join(@RequestParam("loginId") String loginId
					,@RequestParam("password")  String password
					, @RequestParam("name") String name
					, @RequestParam("email") String email) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, email)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	
//	// 로그인 API
	// 한번 로그인된 클라이언트의 정보는 session 에서 관리
//	@PostMapping("/login")
//	public Map<String, String> login(@RequestParam("loginId") String loginId
//				,@RequestParam("password") String password
//				, HttpServletRequest request) {
//		
//	}
	
}
