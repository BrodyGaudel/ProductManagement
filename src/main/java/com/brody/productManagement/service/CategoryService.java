package com.brody.productManagement.service;

import java.util.List;

import com.brody.productManagement.dto.CategoryDTO;
import com.brody.productManagement.exception.CategoryNotFoundException;

public interface CategoryService {
	
	CategoryDTO addCategory(CategoryDTO c);
	
	CategoryDTO getCategoryById(String id) throws CategoryNotFoundException;
	List<CategoryDTO> getAllCategories();
	List<CategoryDTO> getCategoriesByName(String name);
	CategoryDTO getCategoryByProductId(String id) throws CategoryNotFoundException;
	
	CategoryDTO updateCategory(CategoryDTO c);
	
	void deleteCategoryById(String id);
	
	void deleteAllCategories();
	
	

}
