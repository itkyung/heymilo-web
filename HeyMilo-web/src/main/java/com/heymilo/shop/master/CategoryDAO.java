package com.heymilo.shop.master;

import java.util.List;

import com.heymilo.shop.entity.Category;

public interface CategoryDAO {
	
	Category loadCategory(Long id);
	List<Category> listCategories(int start, int limits);
	void saveCategory(Category entity);
	void updateCategory(Category entity);
	int countCategory();
	
}
