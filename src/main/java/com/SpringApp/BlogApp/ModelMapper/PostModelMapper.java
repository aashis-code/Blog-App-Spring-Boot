package com.SpringApp.BlogApp.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringApp.BlogApp.Entity.Post;
import com.SpringApp.BlogApp.Payloads.PostDto;

public class PostModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Post PostDtoToPost(PostDto postDto) {
		return modelMapper.map(postDto, Post.class);
	}
	
	public PostDto PostToPostDto(Post post) {
		return modelMapper.map(post,PostDto.class);
	}
}
