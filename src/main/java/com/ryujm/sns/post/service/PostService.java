package com.ryujm.sns.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ryujm.sns.post.domain.Post;
import com.ryujm.sns.post.dto.CardView;
import com.ryujm.sns.post.repository.PostRepository;
import com.ryujm.sns.user.domain.User;
import com.ryujm.sns.user.service.UserService;

@Service
public class PostService {
	
	private final PostRepository postRepository;
	
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	
	public List<CardView> getPostList() {
		List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		List <CardView> cardList = new ArrayList <>();
		for(Post post:postList) {
			
			User user = userService.getUserById(post.getUserId());
			
			CardView cardView = CardView.builder()
			.postId(post.getId())
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.userId(post.getUserId())
			.loginId(user.getLoginId())
			.build();
			
			cardList.add(cardView);
		}
		
		return cardList;
		
	}
	
}
