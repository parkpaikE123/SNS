package com.ryujm.sns.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryujm.sns.post.dto.CardView;
import com.ryujm.sns.post.service.PostService;

@RequestMapping("/post")
@Controller
public class PostController {

	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/list-view")
	public String list(Model model) {
		
		List<CardView> cardList = postService.getPostList();
		
		model.addAttribute("cardList", cardList);
		
		return "/post/list";
	}
	
	@GetMapping("/list-create")
	public String createList() {
		return "/post/create";
	}
	
}
