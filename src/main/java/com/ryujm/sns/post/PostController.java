package com.ryujm.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostController {

	@GetMapping("/list-view")
	public String list() {
		return "/post/list";
	}
	
	@GetMapping("/list-create")
	public String createList() {
		return "/post/create";
	}
	
}
