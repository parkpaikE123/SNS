package com.ryujm.sns.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryujm.sns.post.domain.Post;

public interface PostRepository extends JpaRepository<Post, Integer>{

	
}
