package com.SpringApp.BlogApp.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.SpringApp.BlogApp.Payloads.PostDto;
import com.SpringApp.BlogApp.Payloads.PostResponse;
import com.SpringApp.BlogApp.Services.FileSystem;
import com.SpringApp.BlogApp.Services.PostService;
import com.SpringApp.BlogApp.Utils.PaginationConstant;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController implements PaginationConstant {

	@Autowired
	private PostService postService;

	@Autowired
	private FileSystem fileSystem;

	@Value("${project.image}")
	private String path;

	// Create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getPagination(
			@RequestParam(value = "pageNumber", defaultValue = PAGE_NUM, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = SORT_DIR, required = false) String sortDir) {
		PostResponse allPosts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}

//	Get All posts By category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postDtos = postService.getAllPostsByCategory(categoryId);
		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}

//	Get All posts By User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUser(@PathVariable Integer userId) {
		List<PostDto> postDtos = postService.getAllPostsByUser(userId);
		return new ResponseEntity<>(postDtos, HttpStatus.OK);
	}
	
//	Get Single post By PostId
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostBYPostId(@PathVariable Integer postId){
		PostDto singlePost = postService.getSinglePost(postId);
		return new ResponseEntity<>(singlePost, HttpStatus.OK);
	}

	// Search post by keyword
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchWithKeyword(@PathVariable String keyword) {
		List<PostDto> postsByKeyword = postService.getPostsByKeyword(keyword);
		return new ResponseEntity<List<PostDto>>(postsByKeyword, HttpStatus.OK);
	}

	// Upload Image for post resource
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable Integer postId)
			throws IOException {
		PostDto singlePost = postService.getSinglePost(postId);
		String uploadFile = fileSystem.uploadFile(path, image);

		singlePost.setImageName(uploadFile);
		PostDto updatePost = postService.updatePost(singlePost, postId);

		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}

	// receive image from file
	@GetMapping(path = "/images/{imagePath}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void getPhoto(@PathVariable String imagePath, HttpServletResponse httpServletResponse) throws IOException {
		InputStream serveFile = fileSystem.serveFile(path, imagePath);
		httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(serveFile, httpServletResponse.getOutputStream());
	}

}
