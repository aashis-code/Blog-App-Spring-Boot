package com.SpringApp.BlogApp.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.SpringApp.BlogApp.ModelMapper.CategoryModelMapper;
import com.SpringApp.BlogApp.ModelMapper.CommentModelMapper;
import com.SpringApp.BlogApp.ModelMapper.PostModelMapper;
import com.SpringApp.BlogApp.ModelMapper.UserModelMapper;

@Configuration
public class application {

	@Bean
	public UserModelMapper userModelMapper() {
		return new UserModelMapper();
	}
	
	@Bean
	public CategoryModelMapper categoryModelMapper() {
		return new CategoryModelMapper();
	}
	
	@Bean
	public PostModelMapper postModelMapper() {
		return new PostModelMapper();
	}
	
	@Bean
	public CommentModelMapper commentModelMapper() {
		return new CommentModelMapper();
	}
}
