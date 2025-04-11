package com.ecom.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.Product;

public interface ProductService {
	public Product saveProduct(Product product);

	public List<Product> getAllProducts();

	public boolean deleteProduct(Integer id);

	public Product getProductById(Integer id);

	public Product updateProduct(Product product, MultipartFile multipart);
}
