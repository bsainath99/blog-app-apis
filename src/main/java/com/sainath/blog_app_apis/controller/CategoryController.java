package com.sainath.blog_app_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sainath.blog_app_apis.payloads.ApiResponse;
import com.sainath.blog_app_apis.payloads.CategoryDTO;
import com.sainath.blog_app_apis.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@PostMapping("/create")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
		CategoryDTO createCategory=categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategory,HttpStatus.CREATED);
	}
	@PutMapping("/update/{catId}")
	public ResponseEntity<CategoryDTO> updateCategory( @Valid @RequestBody CategoryDTO categoryDTO,@PathVariable("catId") Integer categoryId){
		CategoryDTO updatedCategory=categoryService.updateCategory(categoryDTO,categoryId);
		return new ResponseEntity<CategoryDTO>(updatedCategory,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{catId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("catId") Integer categoryId){
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully with Id :"+categoryId,false),HttpStatus.OK);
	}
	
	@GetMapping("/get/{catId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable("catId") Integer categoryId){
		CategoryDTO categoryDTO= categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO,HttpStatus.OK);
	}
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> categoryDTO= categoryService.getCategories();
		return new ResponseEntity<List<CategoryDTO>>(categoryDTO,HttpStatus.OK);
	}
	
	
}
