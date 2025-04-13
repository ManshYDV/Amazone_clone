package com.ecom.mapper;

import com.ecom.dto.ProductDTO;
import com.ecom.entity.Category;
import com.ecom.entity.Product;

public class ProductMapper {
	private ProductMapper() {
		super();
	}

	public static Product toProduct(ProductDTO productDto, Category category) {
		Product product = new Product();
		product.setId(productDto.getId());
		product.setCategory(category);
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setStock(productDto.getStock());
		product.setTitle(productDto.getTitle());
		product.setImage(productDto.getImage());
		return product;
	}

	public static ProductDTO toDTO(Product product) {
		ProductDTO productDto = new ProductDTO();
		productDto.setId(product.getId());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		productDto.setStock(product.getStock());
		productDto.setTitle(product.getTitle());
		productDto.setImage(product.getImage());
		return productDto;
	}
}
