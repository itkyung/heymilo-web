package com.heymilo.shop.master;

import java.util.List;

import com.heymilo.shop.entity.ProductFeature;

public interface ProductFeatureDAO {
	ProductFeature load(Long id);
	void save(ProductFeature entity);
	void update(ProductFeature entity);
	List<ProductFeature> search(String keyword,int start,int limits);
	int countSearch(String keyword);
}
