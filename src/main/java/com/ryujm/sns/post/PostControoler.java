package com.ryujm.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/post")
@Controller
public class PostControoler {

	@GetMapping("/list-view")
	public String list() {
		return "/post/list";
	}
	
}
