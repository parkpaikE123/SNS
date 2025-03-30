package com.ryujm.sns.post.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ryujm.sns.common.FileManager;
import com.ryujm.sns.like.service.LikeService;
import com.ryujm.sns.post.comment.dto.CommentView;
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
			
			List<CommentView> commentList = commentService.getCommentList(post.getId());
			
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
	
	public boolean deletePost(int id, int userId) {
		Optional<Post> optionalPost = postRepository.findById(id);
		
		if(optionalPost.isPresent()) {
			
			Post post = optionalPost.get();
			
			// 삭제 대상 게시글 정보의 작성자와 로그인한 사용자가 일치하지 않는 경우
			// 삭제 실패
			if(post.getUserId() != userId) {
				return false;
			}
			
			FileManager.removeFile(post.getImagePath());
			
			likeService.deleteLikeByPostId(post.getId());
			commentService.deleteCommentByPostId(post.getId());
			
			
			
			try {
				postRepository.delete(post);
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
		
	}
	
	
	
}
