package com.brody.productManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompterProduct {
	
	@Id
	private Long id;

	public CompterProduct(Long id) {
		this.id = id;
	}

	public CompterProduct() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "CompterProduct [id=" + id + "]";
	}
	
}
