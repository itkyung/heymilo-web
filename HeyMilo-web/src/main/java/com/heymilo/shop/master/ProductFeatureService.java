package com.heymilo.shop.master;

import java.util.List;


import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.master.dto.ProductFeatureDTO;

public interface ProductFeatureService {
	ProductFeature load(Long id);
	void save(ProductFeatureDTO dto);
	List<ProductFeature> search(String keyword,int start, int limits);
	int countSearch(String keyword);
}
