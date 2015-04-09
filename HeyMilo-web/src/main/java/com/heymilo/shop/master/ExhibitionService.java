package com.heymilo.shop.master;

import java.util.List;

import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;
import com.heymilo.shop.master.dto.ExhibitionDTO;

public interface ExhibitionService {
	Exhibition load(Long id);
	void save(ExhibitionDTO dto);
	List<Exhibition> search(String keyword,ExhibitionType type,int start, int limits);
	int countSearch(String keyword,ExhibitionType type);
}
