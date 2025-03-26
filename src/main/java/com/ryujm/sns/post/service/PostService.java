package com.ryujm.sns.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryujm.sns.common.FileManager;
import com.ryujm.sns.like.service.LikeService;
import com.ryujm.sns.post.comment.domain.Comment;
import com.ryujm.sns.post.comment.service.CommentService;
import com.ryujm.sns.post.domain.Post;
import com.ryujm.sns.post.dto.CardView;
import com.ryujm.sns.post.repository.PostRepository;
import com.ryujm.sns.user.domain.User;
import com.ryujm.sns.user.service.UserService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

// lombok의 기능으로 생성자를 만드는 Autowired
@RequiredArgsConstructor

@Service
public class PostService {
	
	private final PostRepository postRepository;
	private final UserService userService;
	private final LikeService likeService;
	private final CommentService commentService;
	
	public List<CardView> getPostList(int userId) {
		
		List<Post> postList = postRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
		
		List <CardView> cardList = new ArrayList <>();
		for(Post post:postList) {
			
			User user = userService.getUserById(post.getUserId());
			
			int likeCount = likeService.getLikeCount(post.getId());
			
			boolean isLike = likeService.isLikeByPostIdAndUserId(post.getId(), userId);
			
			List<Comment> commentList = commentService.getCommentList(post.getId());
			
			CardView cardView = CardView.builder()
			.postId(post.getId())
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.userId(post.getUserId())
			.loginId(user.getLoginId())
			.likeCount(likeCount)
			.isLike(isLike)
			.commentList(commentList)
			.build();
			
			cardList.add(cardView);
		}
		
		return cardList;
		
	}
	
	public boolean addPost(int userId
				,String contents
				, MultipartFile imageFile) {
		String imagePath = FileManager.saveFile(userId, imageFile);
		
		Post post = Post.builder()
		.contents(contents)
		.imagePath(imagePath)
		.userId(userId)
		.build();
		
		try {
			postRepository.save(post);
			
		} catch (PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	
	
}
