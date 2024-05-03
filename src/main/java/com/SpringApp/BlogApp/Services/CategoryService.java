package com.SpringApp.BlogApp.Services;

import java.util.List;

import com.SpringApp.BlogApp.Payloads.CategoryDto;

public interface CategoryService {

	
	//Create category
	CategoryDto createCategory(CategoryDto categoryDto);
	
	//Update Category Dto
	CategoryDto updateCategoryDto(CategoryDto categoryDto,Integer categoryId);
	
	//Delete Category 
	void deleteCategory(Integer categoryId);
	
	//get single category
	CategoryDto getCategory(Integer categoryId);
	
	//get all available categories
	List<CategoryDto> getCategories();
}
