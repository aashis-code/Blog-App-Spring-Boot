package com.SpringApp.BlogApp.ModelMapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.SpringApp.BlogApp.Entity.Category;
import com.SpringApp.BlogApp.Payloads.CategoryDto;

public class CategoryModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public Category CategoryDtoToCategory(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, Category.class);
	}
	
	public CategoryDto CategoryToCategoryDto(Category category) {
		return modelMapper.map(category,CategoryDto.class);
	}
}
