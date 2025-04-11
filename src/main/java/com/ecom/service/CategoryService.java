package com.ecom.service;

import java.util.List;

import com.ecom.entity.Category;

public interface CategoryService {
	public Category saveCategory(Category category);

	public boolean categoryExists(String name);

	public List<Category> getAllCategory();

	public boolean deleteCategory(int id);

	public Category getCategoryById(int id);
}
