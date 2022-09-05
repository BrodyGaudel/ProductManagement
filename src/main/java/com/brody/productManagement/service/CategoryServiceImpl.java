package com.brody.productManagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.brody.productManagement.dto.CategoryDTO;
import com.brody.productManagement.entity.Category;
import com.brody.productManagement.exception.CategoryNotFoundException;
import com.brody.productManagement.mapping.Mappers;
import com.brody.productManagement.repository.CategoryRepository;
import com.brody.productManagement.repository.ProductRepository;

import java.util.Collections;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private static final Logger log = Logger.getLogger(CategoryServiceImpl.class);
	private static final String CATEGORY_NOT_FOUND = "Category(s) Not Found : ";
	private static final String CATEGORY_FOUND = "Category(s) Found : ";
	
	private CategoryRepository categoryRepository;
	private Mappers mappers;
	private GenerateIdService generateIdService;
	private ProductRepository productRepository;
	
	

	public CategoryServiceImpl(CategoryRepository categoryRepository, Mappers mappers,
			GenerateIdService generateIdService, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.mappers = mappers;
		this.generateIdService = generateIdService;
		this.productRepository = productRepository;
	}

	@Override
	public CategoryDTO addCategory(CategoryDTO c) {
		log.info("In addCategory()");
		try {
			Category category = mappers.fromCategoryDTO(c);
			String id = generateIdService.generateCategoryId();
			category.setId(id);
			Category cat = categoryRepository.save(category);
			log.info("Category Saved");
			return mappers.fromCategory(cat);
		}catch(Exception e) {
			log.error("Category Not Saved");
			return null;
		}
		
	}

	@Override
	public CategoryDTO getCategoryById(String id) throws CategoryNotFoundException {
		log.info("In getCategoryById()");
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND));
		
		log.info(CATEGORY_FOUND );
		return mappers.fromCategory(category);
	
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		log.info("In getAllCategories()");
		try {
			List<Category> categories = categoryRepository.findAll();
			log.info(CATEGORY_FOUND );
			return mappers.fromListOfCategory(categories);
		}catch(Exception e) {
			log.error(CATEGORY_NOT_FOUND +e);
			return Collections.emptyList();
		}
		
	}

	@Override
	public List<CategoryDTO> getCategoriesByName(String name) {
		log.info("In getCategoriesByName()");
		try {
			List<Category> categories = categoryRepository.findAll();
			List<Category> cats = categories.stream()
					.filter(category -> category.getName().contains(name))
					.collect(Collectors.toList());
			log.info(CATEGORY_FOUND );
			return mappers.fromListOfCategory(cats);
		}catch(Exception e) {
			log.error(CATEGORY_NOT_FOUND+e);
			return Collections.emptyList();
		}
	
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO c) {
		log.info("In updateCategory()");
		try {
			Category category = mappers.fromCategoryDTO(c);
			Category cat = categoryRepository.save(category);
			log.info("Category Updated");
			return mappers.fromCategory(cat);
		}catch(Exception e) {
			log.error("Category Not Updated "+e);
			return null;
		}
		
	}

	@Override
	public void deleteCategoryById(String id) {
		log.info("In deleteCategoryById()");
		try {
			categoryRepository.deleteById(id);
			log.info("Category Deleted ");
		}catch(Exception e) {
			log.error("Category Not Deleted "+e);
		}
	}

	@Override
	public void deleteAllCategories() {
		log.info("In deleteAllCategories()");
		try {
			categoryRepository.deleteAll();
			log.info("Categories Deleted ");
		}catch(Exception e) {
			log.error("Categories Not Deleted "+e);
		}
		
	}

	@Override
	public CategoryDTO getCategoryByProductId(String id) throws CategoryNotFoundException {
		String idc = productRepository.findCategoryIdByProductId(id);
		Category category = categoryRepository.findById(idc)
				.orElseThrow(() -> new CategoryNotFoundException(CATEGORY_NOT_FOUND ));
		
		log.info(CATEGORY_FOUND );
		return mappers.fromCategory(category);
	}

}
