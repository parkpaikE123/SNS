package com.ryujm.sns.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryujm.sns.post.service.PostService;
import com.ryujm.sns.user.domain.User;
import com.ryujm.sns.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private final PostService postService;
	private UserService userService;
	
	public PostRestController(PostService postService, UserService userService) {
		this.postService = postService;
		
		this.userService = userService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam String contents
			,@RequestParam MultipartFile imageFile
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, contents, imageFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "success");
		}
		return resultMap;
	}
	
	public List<User> addMember() {
		List<User>nameList = new ArrayList<>();
		nameList.addAll(userService.getAllUser());
		return nameList;
		
	}

}
