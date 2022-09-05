package com.brody.productManagement.mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.brody.productManagement.dto.CategoryDTO;
import com.brody.productManagement.dto.ProductRequestDTO;
import com.brody.productManagement.dto.ProductResponseDTO;
import com.brody.productManagement.entity.Category;
import com.brody.productManagement.entity.Product;

@Service
public class MappersImpl implements Mappers{

	@Override
	public Product fromProductRequestDTO(ProductRequestDTO p) {
		
		try {
			return new Product(
					p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getQuantity(), p.getBarcode()
					);
		}catch(Exception e) {
			return null;
		}
		
		
	}

	@Override
	public ProductResponseDTO fromProduct(Product p) {
		
		try {
			return new ProductResponseDTO(
					p.getId(), p.getName(), p.getDescription(), p.getPrice(), p.getQuantity(), p.getBarcode()
					);
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public CategoryDTO fromCategory(Category c) {
		try {
			return new CategoryDTO(
					c.getId(), c.getName());
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public Category fromCategoryDTO(CategoryDTO c) {
		try {
			return new Category(
					c.getId(), c.getName());
		}catch(Exception e) {
			return null;
		}
	}
	

	@Override
	public List<ProductResponseDTO> fromListOfProduct(List<Product> liste) {
		if(liste!=null) {
			return liste.stream()
					.map(this::fromProduct)
					.collect(Collectors.toList());
		}
		else {
			return Collections.emptyList();
		}
	}

	@Override
	public List<CategoryDTO> fromListOfCategory(List<Category> liste) {
		try {
			return liste.stream()
					.map(this::fromCategory)
					.collect(Collectors.toList());
		}catch(Exception e) {
			return Collections.emptyList();
		}
	}

}
