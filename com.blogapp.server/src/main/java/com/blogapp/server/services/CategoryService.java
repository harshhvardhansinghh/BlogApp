package com.blogapp.server.services;

import com.blogapp.server.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    //create
    public CategoryDto createCategory(CategoryDto categoryDto);

    //update
    public CategoryDto updateCategory (CategoryDto categoryDto, Integer categoryId);

    //delete
    public void deleteCategory(Integer categoryId);

    //get
    public CategoryDto getCategory(Integer categoryId);

    //get All
    List<CategoryDto> getCategories();
}
