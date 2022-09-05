package com.brody.productManagement.mapping;

import java.util.List;

import com.brody.productManagement.dto.CategoryDTO;
import com.brody.productManagement.dto.ProductRequestDTO;
import com.brody.productManagement.dto.ProductResponseDTO;
import com.brody.productManagement.entity.Category;
import com.brody.productManagement.entity.Product;

public interface Mappers {

	Product fromProductRequestDTO(ProductRequestDTO p);
	ProductResponseDTO fromProduct(Product p);
	CategoryDTO fromCategory(Category c);
	Category fromCategoryDTO(CategoryDTO c);
	List<ProductResponseDTO> fromListOfProduct(List<Product> liste);
	List<CategoryDTO> fromListOfCategory(List<Category> liste);

}
