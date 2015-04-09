package com.heymilo.shop.master;

import java.util.List;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.master.dto.CategoryDTO;

public interface CategoryService {
	Category loadCategory(Long id);
	List<Category> listCategories(int start, int limits);
	int countCategory();
	void saveCategory(CategoryDTO dto);
}
