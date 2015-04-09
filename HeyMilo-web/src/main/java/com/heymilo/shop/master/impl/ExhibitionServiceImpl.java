package com.heymilo.shop.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;
import com.heymilo.shop.master.ExhibitionDAO;
import com.heymilo.shop.master.ExhibitionService;
import com.heymilo.shop.master.dto.ExhibitionDTO;

@Service
public class ExhibitionServiceImpl implements ExhibitionService {
	@Autowired
	private ExhibitionDAO exhibitionDao;
	
	@Override
	public Exhibition load(Long id) {
		return exhibitionDao.load(id);
	}

	@Transactional
	@Override
	public void save(ExhibitionDTO dto) {
		Exhibition exhibition;
		
		if(dto.getId() == null){
			exhibition = new Exhibition();
		}else{
			exhibition = load(dto.getId());
		}
		
		exhibition.setActive(dto.isActive());
		exhibition.setName(dto.getName());
		exhibition.setDesc(dto.getDesc());
		exhibition.setType(dto.getType());
		
		if(dto.getId() == null){
			exhibitionDao.save(exhibition);
		}else{
			exhibitionDao.update(exhibition);
		}
	}

	@Override
	public List<Exhibition> search(String keyword, ExhibitionType type,
			int start, int limits) {
		return exhibitionDao.search(keyword, type, start, limits);
	}

	@Override
	public int countSearch(String keyword, ExhibitionType type) {
		return exhibitionDao.countSearch(keyword, type);
	}

}
