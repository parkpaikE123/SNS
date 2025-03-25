package com.ryujm.sns.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryujm.sns.like.domain.Like;

public interface LikeRepository extends JpaRepository<Like, Integer>{

	// SELECT COUNT(*) FROM `like` WHERE `postId` = #{postId}
	public int countByPostId(int postId);
}
