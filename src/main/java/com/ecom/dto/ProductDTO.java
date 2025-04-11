package com.ecom.dto;

import jakarta.validation.constraints.*;

public class ProductDTO {

	private int id; // optional, used for update

	@NotBlank(message = "Product title is required")
	private String title;

	private String description;

	@NotNull(message = "Category ID is required")
	private Integer categoryId;

	@NotNull(message = "Price is required")
	@Positive(message = "Price must be positive")
	private Double price;

	@Min(value = 0, message = "Stock must be zero or more")
	private int stock;

	private String image;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
