package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dto.CategoryDTO;
import com.ecom.entity.Category;
import com.ecom.mapper.CategoryMapper;
import com.ecom.repo.CategoryRepo;
import com.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public boolean categoryExists(String name) {
		return categoryRepo.existsByName(name.trim());
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		return categoryRepo.findAll().stream().map(CategoryMapper::toDTO).toList();
	}

	@Override
	public boolean deleteCategory(int id) {
		Category category = categoryRepo.findById(id).orElse(null);
		if (category != null) {
			categoryRepo.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Category getCategoryById(int id) {
		return categoryRepo.findById(id).orElse(null);
	}

}
