package com.ryujm.sns.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ryujm.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	private final PostService postService;
	
	public PostRestController(PostService postService) {
		this.postService = postService;
	}
	
	@PostMapping("/create")
	public Map<String, String> createPost(
			@RequestParam String contents
			,@RequestParam(required=false) MultipartFile imageFile
			,@RequestParam int memberId
			,@RequestParam String location
			,@RequestParam(required=false) MultipartFile musicFile
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(postService.addPost(userId, contents, imageFile, memberId, location, musicFile)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "success");
		}
		return resultMap;
	}

}
