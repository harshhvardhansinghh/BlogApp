package com.blogapp.server.services.impl;

import com.blogapp.server.entities.Category;
import com.blogapp.server.exceptions.ResourceNotFoundException;
import com.blogapp.server.payloads.CategoryDto;
import com.blogapp.server.repositories.CategoryRepo;
import com.blogapp.server.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


public class CategoryServiceImpl  implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category cat=this.modelMapper.map(categoryDto, Category.class);
        Category addedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

         Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
         cat.setCategoryTitle(categoryDto.getCategoryTitle());
         cat.setCategoryDescription(categoryDto.getCategoryDescription());

         Category updatedcat = this.categoryRepo.save(cat);

         return this.modelMapper.map(updatedcat,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat= this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        return this.modelMapper.map(cat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category>categories=this.categoryRepo.findAll();
        List<CategoryDto>catDtos =categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }
}
