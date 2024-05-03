package com.SpringApp.BlogApp.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringApp.BlogApp.Entity.Comment;
import com.SpringApp.BlogApp.Payloads.CommentDto;

public class CommentModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public Comment commentDtoToComment(CommentDto commentDto) {
		return modelMapper.map(commentDto, Comment.class);
	}
	
	public CommentDto commentToCommentDto(Comment comment) {
		return modelMapper.map(comment, CommentDto.class); 
	}

}
