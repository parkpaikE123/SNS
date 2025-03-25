package com.ryujm.sns.post.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="`post`")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="userId")
	private int userId;
	
	@Column(name="imagePath")
	private String imagePath;
	
	@Column(name="contents")
	private String contents;
	
	@Column(name="locationPath")
	private String locationPath;
	
	@Column(name="musicPath")
	private String musicPath;
	
	@Column(name="tagMemberId")
	private int tagMemberId;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	
}
