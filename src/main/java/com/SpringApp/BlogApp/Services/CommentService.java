package com.SpringApp.BlogApp.Services;

import java.util.List;

import com.SpringApp.BlogApp.Payloads.CommentDto;

public interface CommentService {
	
	// create comment
	CommentDto postComment(Integer postId, Integer userId, CommentDto commentDto);
	
	// Delete comment
	Boolean deleteComment(Integer commentId);
	
//	Get All Comments from Single Post
	List<CommentDto> getAllCommentsByPostId(Integer postId);

}
