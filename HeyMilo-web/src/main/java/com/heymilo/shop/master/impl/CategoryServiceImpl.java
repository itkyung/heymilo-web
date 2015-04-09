package com.heymilo.shop.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.master.CategoryDAO;
import com.heymilo.shop.master.CategoryService;
import com.heymilo.shop.master.dto.CategoryDTO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public Category loadCategory(Long id) {
		return categoryDao.loadCategory(id);
	}

	@Override
	public List<Category> listCategories(int start, int limits) {
		return categoryDao.listCategories(start, limits);
	}

	@Transactional
	@Override
	public void saveCategory(CategoryDTO dto) {
		Category category;
		if(dto.getId() == null){
			category = new Category();
		}else{
			category = loadCategory(dto.getId());
		}
		
		category.setName(dto.getName());
		category.setDesc(dto.getDesc());
		category.setActive(dto.isActive());
		
		if(dto.getId() == null){
			categoryDao.saveCategory(category);
		}else{
			categoryDao.updateCategory(category);
		}
	}

	@Override
	public int countCategory() {
		return categoryDao.countCategory();
	}

	
}
