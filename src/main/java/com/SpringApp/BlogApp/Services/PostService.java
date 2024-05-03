package com.SpringApp.BlogApp.Services;

import java.util.List;

import com.SpringApp.BlogApp.Entity.Post;
import com.SpringApp.BlogApp.Payloads.PostDto;
import com.SpringApp.BlogApp.Payloads.PostResponse;

public interface PostService {

	//create post
	PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId);
	
	//update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//get all posts
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getSinglePost(Integer postId);
	
	//delete Post
	void deletePost(Integer postId);
	
	//get all posts by category
	List<PostDto> getAllPostsByCategory(Integer categoryId);
	
	//get All Posts by User
	List<PostDto> getAllPostsByUser(Integer userId);
	
	//search post based on keyword
	List<PostDto> getPostsByKeyword(String keyword);
	
}
