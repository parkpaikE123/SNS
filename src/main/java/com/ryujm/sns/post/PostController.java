package com.ryujm.sns.post;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ryujm.sns.post.dto.CardView;
import com.ryujm.sns.post.service.PostService;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/post")
@Controller
public class PostController {

	private final PostService postService;
	
	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping("/add-member")
	public String addMember() {
		return"/user/selectmember";
	}
	
	@GetMapping("/list-view")
	public String list(
					Model model
					, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<CardView> cardList = postService.getPostList(userId);
		
		model.addAttribute("cardList", cardList);
		
		return "/post/list";
	}
	
	@GetMapping("/list-create")
	public String createList() {
		return "/post/create";
	}
	
	@GetMapping("/delete")
	public void deleteList() {
		
	}
	
	
}
