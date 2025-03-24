package com.ryujm.sns.like.service;

import org.springframework.stereotype.Service;

import com.ryujm.sns.like.domain.Like;
import com.ryujm.sns.like.repository.LikeRepository;

@Service
public class LikeService {

	private final LikeRepository likeRepositroy;
	
	public LikeService(LikeRepository likeRepository) {
		this.likeRepositroy = likeRepository;
	}
	
	public boolean addLike(int postId, int userId) {
		
		Like like = Like.builder()
		.postId(postId)
		.userId(userId)
		.build();
		
		
	}
	
}
