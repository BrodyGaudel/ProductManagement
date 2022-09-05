package com.brody.productManagement.service;

import java.util.List;

import com.brody.productManagement.dto.ProductRequestDTO;
import com.brody.productManagement.dto.ProductResponseDTO;
import com.brody.productManagement.exception.ProductNotFoundException;



public interface ProductService {
	
	ProductResponseDTO addProduct(ProductRequestDTO productRequestDTO);
	
	ProductResponseDTO getProductById(String id) throws ProductNotFoundException;
	List<ProductResponseDTO> getProductByCodebare(String codebare) throws ProductNotFoundException;
	List<ProductResponseDTO> getAllProducts();
	List<ProductResponseDTO> getProductsByName(String name);
	List<ProductResponseDTO> getProductsByDescription(String description);
	List<ProductResponseDTO> getProductsByNameOrDescription(String word);
	List<ProductResponseDTO> getProductsByPrice(Double minPrice, Double maxPrice);
	List<ProductResponseDTO> getProductsByCategoryId(String id);
	List<ProductResponseDTO> getProductsByCategoryName(String name);
	
	ProductResponseDTO updateProduct(ProductRequestDTO productDTO);
	
	void deleteProductById(String id);
	
	void deleteAllProducts();

}
