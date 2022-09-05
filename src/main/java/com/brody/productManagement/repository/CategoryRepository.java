package com.brody.productManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.brody.productManagement.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
	
	@Query( value = "SELECT * FROM category WHERE name = ?1", nativeQuery = true)
	Category findByName(String name);

}
