package com.sainath.blog_app_apis.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sainath.blog_app_apis.entities.Category;
import com.sainath.blog_app_apis.exception.ResourceNotFoundException;
import com.sainath.blog_app_apis.payloads.CategoryDTO;
import com.sainath.blog_app_apis.repository.CategoryRepo;
import com.sainath.blog_app_apis.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category category=	mapper.map(categoryDTO, Category.class);
		Category addedCategory= categoryRepo.save(category);		
		return mapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category cat= categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		cat.setCategoryTitle(categoryDTO.getCategoryTitle());
		cat.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updatedCat=categoryRepo.save(cat);
		return mapper.map(updatedCat, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		categoryRepo.delete(cat);

	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category cat=categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		return mapper.map(cat, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories= categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs= categories.stream().map((cat)->mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
		return categoryDTOs;
	}

}
