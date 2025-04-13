package com.ecom.mapper;

import com.ecom.dto.CategoryDTO;
import com.ecom.entity.Category;

public class CategoryMapper {

	private CategoryMapper() {
		throw new UnsupportedOperationException("Utility class cannot be instantiated");
	}

	public static CategoryDTO toDTO(Category category) {
		CategoryDTO cat = new CategoryDTO();
		cat.setId(category.getId());
		cat.setActive(category.isActive());
		cat.setImage(category.getImage());
		cat.setName(category.getName());
		return cat;
	}

	public static Category toCategory(CategoryDTO category) {
		Category cat = new Category();
		cat.setId(category.getId());
		cat.setActive(category.isActive());
		cat.setImage(category.getImage());
		cat.setName(category.getName());
		return cat;
	}
}
