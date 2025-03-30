package com.ryujm.sns.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryujm.sns.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Integer>{

	// SELECT COUNT(*) FROM `like` WHERE `postId` = #{postId}
	public int countByPostId(int postId);
	
	public boolean existsByPostIdAndUserId(int postId, int userId);
	
	public Optional<Like> findByPostIdAndUserId(int postId, int userId);
	
	// SELECT * FROM `like` WHERE `postId` = #{}
	// DELETE FROM `like` WHERE `postId` = #{}
	// transaction
	// Rollback : 이전 상태로 되돌린다.
	// 삭제 과정에 문제가 생기는 경우 진행된 모든 과정을 되돌린다
	@Transactional
	public void deleteByPostId(int postId);
	
}
