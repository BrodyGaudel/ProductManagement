package com.brody.productManagement.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="CATEGORY")
public class Category {
	
	@Id
	@Column(name="ID", nullable=false, unique=true)
	private String id;
	
	@Column(name="NAME", nullable=false)
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "category")
	List<Product> products;

	public Category(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Category() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	

}
