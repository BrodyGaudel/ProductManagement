package com.brody.productManagement.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.brody.productManagement.dto.CategoryDTO;
import com.brody.productManagement.exception.CategoryNotFoundException;
import com.brody.productManagement.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins="*")
public class CategoryRestController {
	
	private CategoryService categoryService;

	public CategoryRestController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@PostMapping(path="/save")
	@ResponseBody
	public CategoryDTO addCategory(@RequestBody CategoryDTO c) {
		return categoryService.addCategory(c);
	}

	@GetMapping(path="/findById/{id}")
	@ResponseBody
	public CategoryDTO getCategoryById(@PathVariable(name="id") String id) throws CategoryNotFoundException {
		return categoryService.getCategoryById(id);
	
	}

	@GetMapping(path="/findAll")
	@ResponseBody
	public List<CategoryDTO> getAllCategories() {
		return categoryService.getAllCategories();		
	}

	@GetMapping(path="/findByName/{name}")
	@ResponseBody
	public List<CategoryDTO> getCategoriesByName(@PathVariable(name="name")  String name) {
		return categoryService.getCategoriesByName(name);
	
	}
	
	@GetMapping(path="/findByProductId/{id}")
	@ResponseBody
	public CategoryDTO getCategoryByProductId(@PathVariable(name="id") String id) throws CategoryNotFoundException {
		return categoryService.getCategoryByProductId(id);
	}

	@PutMapping(path="/update")
	@ResponseBody
	public CategoryDTO updateCategory(@RequestBody CategoryDTO c) {
		return categoryService.updateCategory(c);
		
	}

	@DeleteMapping(path="/deleteById/{id}")
	public void deleteCategoryById(@PathVariable(name="id")  String id) {
		categoryService.deleteCategoryById(id);
	}

	@DeleteMapping(path="/deleteAll")
	public void deleteAllCategories() {
		categoryService.deleteAllCategories();
		
	}
	
	

}
