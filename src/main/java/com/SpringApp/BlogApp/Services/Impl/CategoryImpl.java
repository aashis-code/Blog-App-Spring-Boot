package com.SpringApp.BlogApp.Services.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SpringApp.BlogApp.Entity.Category;
import com.SpringApp.BlogApp.Exceptions.ResourceNotFoundException;
import com.SpringApp.BlogApp.ModelMapper.CategoryModelMapper;
import com.SpringApp.BlogApp.Payloads.CategoryDto;
import com.SpringApp.BlogApp.Repositories.CategoryRepo;
import com.SpringApp.BlogApp.Services.CategoryService;

@Service
public class CategoryImpl implements CategoryService{

	@Autowired
	private CategoryModelMapper categoryModelMapper;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = categoryModelMapper.CategoryDtoToCategory(categoryDto);
		Category save1 = categoryRepo.save(category);
		return categoryModelMapper.CategoryToCategoryDto(save1);
	}

	@Override
	public CategoryDto updateCategoryDto(CategoryDto categoryDto, Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", categoryId));
		category.setCategoryTitleString(categoryDto.getCategoryTitle());
		category.setCategoryDescriptionString(categoryDto.getCategoryDescription());
		categoryRepo.save(category);
		return this.categoryModelMapper.CategoryToCategoryDto(category);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
		return categoryModelMapper.CategoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getCategories() {
		
		List<CategoryDto> categoryDto = categoryRepo.findAll().stream().map(category -> categoryModelMapper.CategoryToCategoryDto(category)).collect(Collectors.toList());
		
		return categoryDto;
	}

}
