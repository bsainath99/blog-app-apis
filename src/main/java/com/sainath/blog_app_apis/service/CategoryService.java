package com.sainath.blog_app_apis.service;

import java.util.List;

import com.sainath.blog_app_apis.payloads.CategoryDTO;

public interface CategoryService {
 CategoryDTO createCategory(CategoryDTO categoryDTO);
 CategoryDTO updateCategory(CategoryDTO categoryDTO,Integer categoryId);
 void deleteCategory(Integer categoryId);
 CategoryDTO getCategory(Integer categoryId);
List<CategoryDTO> getCategories();
}
