package com.brody.productManagement.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CompteurCategory {
	
	@Id
	private Long id;

	public CompteurCategory(Long id) {
		this.id = id;
	}

	public CompteurCategory() {
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
		return "CompteurCategory [id=" + id + "]";
	}
	
}
