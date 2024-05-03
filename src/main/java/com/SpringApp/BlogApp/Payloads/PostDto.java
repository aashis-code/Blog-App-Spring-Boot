package com.SpringApp.BlogApp.Payloads;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	
	private Integer postId;
	
	@NotNull
	@Size(min=5, max=1000, message="Post title must 5 to 1000 characters.")
	private String postTitle;
	
	@NotNull
	@Size(min=5, message="Post title must minimum of 5 characters.")
	private String postContent;
	
	private String imageName;
	
	private Date createdDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
