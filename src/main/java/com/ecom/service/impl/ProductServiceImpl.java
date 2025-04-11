package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.entity.Product;
import com.ecom.repo.ProductRepo;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public boolean deleteProduct(Integer id) {
		Product product = productRepo.findById(id).orElse(null);
		if (product != null) {
			productRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Product getProductById(Integer id) {
		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Product updateProduct(Product product, MultipartFile multipart) {
		Product dbProduct = productRepo.findById(product.getId()).orElse(null);
		if (dbProduct == null)
			return null;
		String imageName = multipart.isEmpty() ? dbProduct.getImage() : multipart.getOriginalFilename();
		dbProduct.setTitle(product.getTitle());
		dbProduct.setDescription(product.getDescription());
		dbProduct.setCategory(product.getCategory());
		dbProduct.setPrice(product.getPrice());
		dbProduct.setStock(product.getStock());
		dbProduct.setImage(product.getImage());
		Product updateProduct = productRepo.save(dbProduct);
		if (!multipart.isEmpty()) {
			try {
//					todo something with mongo db for saving image

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return updateProduct;
	}

}
