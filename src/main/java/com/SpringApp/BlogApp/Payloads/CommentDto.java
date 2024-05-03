package com.SpringApp.BlogApp.Payloads;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {

	private int commentId;

	@NotEmpty(message = "Comment can not be blank.")
	@Size(min = 3, message = "Comment must be at least 3 characters.")
	private String content;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private UserDto userDto;

}
