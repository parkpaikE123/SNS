package com.ryujm.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ryujm.sns.user.domain.User;
import com.ryujm.sns.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	// 회원가입 API
	@PostMapping("/join")
	public Map<String, String> join(
					@RequestParam String loginId
					,@RequestParam String password
					, @RequestParam String name
					, @RequestParam String email) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(userService.addUser(loginId, password, name, email)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		return resultMap;
		
	}
	
	// 중복 체크 API
	@GetMapping("/duplicate-id")
	public Map<String, Boolean> isDuplicateId(@RequestParam String loginId) {
		
		Map<String, Boolean> resultMap = new HashMap<>();
		
		if(userService.isDuplicateId(loginId)) {
			// 중복
			resultMap.put("isDuplicate", true);
		} else {
			// 중복 안됨
			resultMap.put("isDuplicate", false);
		}
		
		return resultMap;
	}
	
	// 로그인 API
//	 한번 로그인된 클라이언트의 정보는 session 에서 관리
	@PostMapping("/login")
	public Map<String, String> login(@RequestParam String loginId
				,@RequestParam String password
				, HttpSession session) {
		User user = userService.getUser(loginId, password);
		
		Map<String, String> resultMap = new HashMap<>();
		if(user != null) {
			// 세션에 사용자 정보 저장
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			
			resultMap.put("result", "success");
			
		}else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
