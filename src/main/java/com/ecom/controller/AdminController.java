package com.ecom.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.CategoryDTO;
import com.ecom.dto.ProductDTO;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.mapper.CategoryMapper;
import com.ecom.mapper.ProductMapper;
import com.ecom.service.CategoryService;
import com.ecom.service.ProductService;
import com.ecom.utilities.APIResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	private CategoryService categoryService;
	private ProductService productService;

	public AdminController(CategoryService categoryService, ProductService productService) {
		super();
		this.categoryService = categoryService;
		this.productService = productService;
	}

	@GetMapping("/")
	private ResponseEntity<?> index() {
		return ResponseEntity.ok("Welcome to admin dashboard");
	}

	@GetMapping("/category")
	public ResponseEntity<?> getAllCategory() {
		return ResponseEntity.ok(APIResponse.success("All category found", categoryService.getAllCategory()));
	}

	@GetMapping("/category/{id}")
	public ResponseEntity<?> getCategoryById(@PathVariable int id) {
		Category category = categoryService.getCategoryById(id);
		if (category == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(APIResponse.error("No category found with this id"));
		CategoryDTO categoryDto = CategoryMapper.toDTO(category);
		return ResponseEntity.ok(APIResponse.success("Category Found", categoryDto));
	}

	@PostMapping("/category")
	public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> error = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return ResponseEntity.badRequest().body(APIResponse.error(String.join(", ", error)));
		}
		if (categoryService.categoryExists(categoryDTO.getName())) {
			return ResponseEntity.badRequest().body(APIResponse.error("Category already exists with the same name"));
		}
		Category category = CategoryMapper.toCategory(categoryDTO);
		categoryService.saveCategory(category);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(APIResponse.success("Category created successfully" + categoryDTO));
	}

	@DeleteMapping("/category/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable int id) {
		if (categoryService.deleteCategory(id)) {
			return ResponseEntity.ok(APIResponse.success("Category Deleted Successfully"));
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("Category not found with id " + id));
	}

	@PutMapping("/category/{id}")
	public ResponseEntity<?> updateCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
			return ResponseEntity.badRequest().body(APIResponse.error(String.join(", ", errors)));
		}
		Category category = categoryService.getCategoryById(id);
		if (category == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("No category found for this id"));
		}
		category.setName(categoryDTO.getName());
		category.setImage(categoryDTO.getImage());
		category.setActive(categoryDTO.isActive());
		categoryService.saveCategory(category);
		return ResponseEntity.ok(APIResponse.success("Category updated successfully"));
	}

	@PostMapping("/product")
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDTO product, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
			return ResponseEntity.badRequest().body(APIResponse.error(String.join(", ", errors)));
		}
		Category category = categoryService.getCategoryById(product.getCategoryId());
		if (category == null)
			return ResponseEntity.badRequest().body(APIResponse.error("Category doesnt Exist"));
		productService.saveProduct(ProductMapper.toProduct(product, category));
		return ResponseEntity.ok(APIResponse.success("Product Added Successfully"));
	}

	@GetMapping("/product")
	public ResponseEntity<?> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		List<ProductDTO> productsDto = products.stream().map(p -> ProductMapper.toDTO(p)).toList();
		return ResponseEntity.ok(APIResponse.success("Success", productsDto));
	}

	@GetMapping("product/{id}")
	public ResponseEntity<?> getProductById(@PathVariable int id) {
		Product product = productService.getProductById(id);
		if (product == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("Product not found"));
		ProductDTO productDto = ProductMapper.toDTO(product);
		return ResponseEntity.ok(APIResponse.success("Success", productDto));
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable int id) {
		Product product = productService.getProductById(id);
		if (product == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("No product exist with this id"));
		productService.deleteProduct(id);
		return ResponseEntity.ok(APIResponse.success("Product Deleted Successfully"));
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable int id, @Valid @RequestBody ProductDTO productDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(APIResponse.error(String.join(", ", errors)));
		}
		Product existingProduct = productService.getProductById(id);
		if (existingProduct == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(APIResponse.error("No product exist for this id"));
		}
		Category category = categoryService.getCategoryById(productDto.getCategoryId());
		if (category == null) {
			return ResponseEntity.badRequest().body(APIResponse.error("Invalid category id"));
		}
		existingProduct.setTitle(productDto.getTitle());
		existingProduct.setDescription(productDto.getDescription());
		existingProduct.setPrice(productDto.getPrice());
		existingProduct.setStock(productDto.getStock());
		existingProduct.setImage(productDto.getImage());
		existingProduct.setCategory(category);
		productService.saveProduct(existingProduct);
		return ResponseEntity.ok(APIResponse.success("Product updated successfully"));
	}

}
