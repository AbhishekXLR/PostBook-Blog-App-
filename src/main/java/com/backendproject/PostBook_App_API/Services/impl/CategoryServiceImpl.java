package com.backendproject.PostBook_App_API.Services.impl;

import com.backendproject.PostBook_App_API.Entities.Category;
import com.backendproject.PostBook_App_API.Exceptions.ResourceNotFoundException;
import com.backendproject.PostBook_App_API.Payloads.CategoryDto;
import com.backendproject.PostBook_App_API.Repositories.CategoryRepo;
import com.backendproject.PostBook_App_API.Services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto22) {
        Category cat = this.modelMapper.map(categoryDto22, Category.class);
        Category addedCat = categoryRepo.save(cat);
        return this.modelMapper.map(addedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));

        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());

        Category updatedCat = this.categoryRepo.save(cat);
        return this.modelMapper.map(updatedCat, CategoryDto.class);

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> allCategories = this.categoryRepo.findAll();
       List<CategoryDto> categoriesDto= allCategories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return categoriesDto;
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category id", categoryId));
         return this.modelMapper.map(cat, CategoryDto.class);

    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","category Id",categoryId));
               this.categoryRepo.delete(cat);
    }
}
