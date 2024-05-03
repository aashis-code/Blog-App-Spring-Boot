package com.SpringApp.BlogApp.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.SpringApp.BlogApp.Entity.Category;
import com.SpringApp.BlogApp.Entity.Post;
import com.SpringApp.BlogApp.Entity.User;
import com.SpringApp.BlogApp.Exceptions.ResourceNotFoundException;
import com.SpringApp.BlogApp.ModelMapper.PostModelMapper;
import com.SpringApp.BlogApp.Payloads.PostDto;
import com.SpringApp.BlogApp.Payloads.PostResponse;
import com.SpringApp.BlogApp.Repositories.CategoryRepo;
import com.SpringApp.BlogApp.Repositories.PostRepo;
import com.SpringApp.BlogApp.Repositories.UserRepo;
import com.SpringApp.BlogApp.Services.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private PostModelMapper postModelMapper;
	
//	User creation Method
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer CategoryId) {
		User user = userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		Category category = categoryRepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id",CategoryId));
		Post post = postModelMapper.PostDtoToPost(postDto);
		post.setImageName("default.png");
		post.setCreatedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post save = postRepo.save(post);
		return postModelMapper.PostToPostDto(save);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		Post save = postRepo.save(post);
		return postModelMapper.PostToPostDto(save);
	}

	//get post using pagination
	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		
		PostResponse postResponse = new PostResponse(); 
		
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?(Sort.by(sortBy).ascending()):(Sort.by(sortBy).descending());
		
		Pageable page = PageRequest.of(pageNumber,pageSize, sort);
		Page<Post> findAll = postRepo.findAll(page);
		List<Post> content = findAll.getContent();
		List<PostDto> collect = content.stream().map((post)-> postModelMapper.PostToPostDto(post)).collect(Collectors.toList());
		
		postResponse.setContent(collect);
		postResponse.setPageNumber(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setLastPage(findAll.isLast());
		postResponse.setTotalPages(findAll.getTotalPages());
		
		return postResponse;
	}

	@Override
	public PostDto getSinglePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","postId",postId));
		return postModelMapper.PostToPostDto(post);
	}

	@Override
	public void deletePost(Integer postId) {
		postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postId));
		
	}

	@Override
	public List<PostDto> getAllPostsByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","cat_id",categoryId));
		List<Post> post = postRepo.findByCategory(category);
		List<PostDto> collect = post.stream().map(post1-> postModelMapper.PostToPostDto(post1)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getAllPostsByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user_Id",userId));
		List<Post> findByUser = postRepo.findByUser(user);
		List<PostDto> collect = findByUser.stream().map(user1 -> postModelMapper.PostToPostDto(user1)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostsByKeyword(String keyword) {
		List<Post> post = postRepo.findByPostTitleContaining(keyword);
		List<PostDto> collect = post.stream().map(post1 -> postModelMapper.PostToPostDto(post1)).collect(Collectors.toList());
		return collect;
	}

}
