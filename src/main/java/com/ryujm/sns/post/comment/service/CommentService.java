package com.ryujm.sns.post.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ryujm.sns.post.comment.domain.Comment;
import com.ryujm.sns.post.comment.repository.CommentRepository;

import jakarta.persistence.PersistenceException;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
	
		Comment comment = Comment.builder()
				.postId(postId)
				.userId(userId)
				.contents(contents)
				.build();
				
				try {
					commentRepository.save(comment);
					
				} catch(PersistenceException e) {
					return false;
				}
				
				return true;
		
	}
	
	public List<Comment> getCommentList(int postId) {
		return commentRepository.findByPostId(postId);
	}
	
}
