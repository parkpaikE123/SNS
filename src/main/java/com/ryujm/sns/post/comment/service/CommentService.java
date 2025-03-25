package com.ryujm.sns.post.comment.service;

import org.springframework.stereotype.Service;

import com.ryujm.sns.post.comment.repository.CommentRepository;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
	
		
		
	}
	
}
