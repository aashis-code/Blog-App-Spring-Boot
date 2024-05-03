package com.SpringApp.BlogApp.Payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	
	private int categoryId;
	
	@NotEmpty
	@Size(min = 3,message = "Please enter at least 3 characters of title.")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 5,message = "You should write something about the category")
	private String categoryDescription;
}
