package com.ryujm.sns.post.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryujm.sns.post.comment.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}