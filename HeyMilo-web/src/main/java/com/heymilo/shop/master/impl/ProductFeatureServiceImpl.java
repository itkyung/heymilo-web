package com.heymilo.shop.master.impl;

import java.awt.image.ImageFilter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.common.FileService;
import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.master.ProductFeatureDAO;
import com.heymilo.shop.master.ProductFeatureService;
import com.heymilo.shop.master.dto.ProductFeatureDTO;

@Service
public class ProductFeatureServiceImpl implements ProductFeatureService {
	@Autowired
	private ProductFeatureDAO dao;
	
	
	@Override
	public ProductFeature load(Long id) {
		return dao.load(id);
	}

	@Transactional
	@Override
	public void save(ProductFeatureDTO dto) {
		ProductFeature entity;
		
		if(dto.getId() == null){
			entity = new ProductFeature();
		}else{
			entity = dao.load(dto.getId());
		}
		
		entity.setActive(dto.isActive());
		entity.setName(dto.getName());
		entity.setDesc(dto.getDesc());
		if(dto.getImageFile() != null){
			//TODO 아마존 CDN을 이용할수 있는 방법을 찾아본다.
			
		}
		
		if(dto.getId() == null){
			dao.save(entity);
		}else{
			dao.update(entity);
		}
	}

	@Override
	public List<ProductFeature> search(String keyword, int start, int limits) {
		return dao.search(keyword, start, limits);
	}

	@Override
	public int countSearch(String keyword) {
		return dao.countSearch(keyword);
	}

}
