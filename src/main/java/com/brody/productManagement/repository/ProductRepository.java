package com.brody.productManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brody.productManagement.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {
	
	@Query( value = "SELECT * FROM product WHERE category_id = ?1", nativeQuery = true)
	List<Product> findByCategoryId(String id);
	
	@Query( value = "SELECT category_id FROM product WHERE id= ?1", nativeQuery = true)
	String findCategoryIdByProductId(String id);

	
}
