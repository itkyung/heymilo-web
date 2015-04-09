package com.heymilo.shop.master;

import java.util.List;

import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;

public interface ExhibitionDAO {
	Exhibition load(Long id);
	void save(Exhibition entity);
	void update(Exhibition entity);
	List<Exhibition> search(String keyword,ExhibitionType type,int start, int limits);
	int countSearch(String keyword,ExhibitionType type);
	
}
