package com.brody.productManagement.dto;

public class ProductRequestDTO {
	
	private String id;
	private String name;
	private String description;
	private Double price;
	private Integer quantity;
	private String barcode;
	private String categoryId;
	
	public ProductRequestDTO(String id, String name, String description, Double price, Integer quantity, String barcode,
			String categoryId) {
	
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
		this.barcode = barcode;
		this.categoryId = categoryId;
	}

	public ProductRequestDTO() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public String toString() {
		return "ProductRequestDTO [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
				+ ", quantity=" + quantity + ", barcode=" + barcode + ", categoryId=" + categoryId + "]";
	}
	
	
}
