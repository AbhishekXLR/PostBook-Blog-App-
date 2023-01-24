package com.backendproject.PostBook_App_API.Services;

import com.backendproject.PostBook_App_API.Payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

CategoryDto createCategory(CategoryDto categoryDto);

CategoryDto updateCategory (CategoryDto categoryDto,Integer categoryId);

List<CategoryDto> getAllCategories();

CategoryDto getCategory(Integer categoryId);

void deleteCategory(Integer categoryId);

}
