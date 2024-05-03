package com.SpringApp.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringApp.BlogApp.Entity.Comment;
import com.SpringApp.BlogApp.Entity.Post;
import com.SpringApp.BlogApp.Entity.User;
import com.SpringApp.BlogApp.Exceptions.ResourceNotFoundException;
import com.SpringApp.BlogApp.ModelMapper.CommentModelMapper;
import com.SpringApp.BlogApp.ModelMapper.UserModelMapper;
import com.SpringApp.BlogApp.Payloads.CommentDto;
import com.SpringApp.BlogApp.Repositories.CommentRepo;
import com.SpringApp.BlogApp.Repositories.PostRepo;
import com.SpringApp.BlogApp.Repositories.UserRepo;
import com.SpringApp.BlogApp.Services.CommentService;

@Service
public class CommentImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private CommentModelMapper commentModelMapper;
	
	@Autowired
	private UserModelMapper userModelMapper;

	@Override
	public CommentDto postComment(Integer postId, Integer userId, CommentDto commentDto) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = commentModelMapper.commentDtoToComment(commentDto);
		comment.setUser(user);
		comment.setPost(post);
		Comment save = commentRepo.save(comment);
		return commentModelMapper.commentToCommentDto(save);
	}

	@Override
	public Boolean deleteComment(Integer commentId) {
		Comment comment = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "Id", commentId));
		commentRepo.delete(comment);
		return true;
	}

	@Override
	public List<CommentDto> getAllCommentsByPostId(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", 0));
		List<Comment> comment = commentRepo.findByPost(post);
		return comment.stream().map(comt -> {
			CommentDto commentDto = commentModelMapper.commentToCommentDto(comt);
			User user = comt.getUser();
		    commentDto.setUserDto(userModelMapper.UserToUserDto(user)); 
		    return commentDto;
			}).collect(Collectors.toList());
		
		
	}

}
