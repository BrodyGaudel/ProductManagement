package com.brody.productManagement.dto;

public class CategoryDTO {
	
	private String id;
	private String name;
	
	public CategoryDTO(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public CategoryDTO() {
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

	@Override
	public String toString() {
		return "CategoryDTO [id=" + id + ", name=" + name + "]";
	}
	
	
}
