package com.SpringApp.BlogApp.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SpringApp.BlogApp.Payloads.CommentDto;
import com.SpringApp.BlogApp.Services.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

//	Posting Comment
	@PostMapping(path = "/user/{userId}/post/{postId}", consumes = "application/json")
	public ResponseEntity<CommentDto> postComment(@PathVariable Integer userId, @PathVariable Integer postId,
		@Valid @RequestBody CommentDto commentDto) {
		CommentDto postComment = commentService.postComment(postId, userId, commentDto);
		return new ResponseEntity<CommentDto>(postComment, HttpStatus.CREATED);
	}

// Deleting comment
	@DeleteMapping(path = "/{commentId}")
	public ResponseEntity<Map<String, String>> deleteComment(@PathVariable Integer commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>(Map.of("message", "Successfully deleted !"), HttpStatus.OK);
	}
	
//	Get all comments by postId
	@GetMapping(path = "/post/{postId}")
	public ResponseEntity<List<CommentDto>> getCommentsByPostId(@PathVariable Integer postId){
		List<CommentDto> commentDto = commentService.getAllCommentsByPostId(postId);
		return new ResponseEntity<>(commentDto, HttpStatus.OK);
	}

}
