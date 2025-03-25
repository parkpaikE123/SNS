package com.ryujm.sns.post.comment;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentRestController {
	
	@PostMapping("/post/comment/create")
	public Map<String, String> createComment() {
		
	}
	
}
