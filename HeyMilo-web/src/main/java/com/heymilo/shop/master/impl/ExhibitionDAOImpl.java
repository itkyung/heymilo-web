package com.heymilo.shop.master.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Exhibition;
import com.heymilo.shop.entity.ExhibitionType;
import com.heymilo.shop.master.ExhibitionDAO;

@Repository
public class ExhibitionDAOImpl implements ExhibitionDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public Exhibition load(Long id) {
		Query query = em.createQuery("FROM Exhibition a WHERE a.id = :id");
		query.setParameter("id", id);
		query.setHint("org.hibernate.cacheable", true);
		return (Exhibition)query.getSingleResult();
	}
	
	@Override
	public void save(Exhibition entity) {
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		em.persist(entity);
	}

	@Override
	public void update(Exhibition entity) {
		entity.setUpdated(new Date());
		em.merge(entity);
	}

	@Override
	public List<Exhibition> search(String keyword, ExhibitionType type,
			int start, int limits) {
		Query query = makeQuery(false, keyword, type, start, limits);
		
		return query.getResultList();
	}

	@Override
	public int countSearch(String keyword, ExhibitionType type) {
		Query query = makeQuery(true, keyword, type, 0, 0);
		return ((Number)query.getSingleResult()).intValue();
	}
	
	private Query makeQuery(boolean countQuery,String keyword, ExhibitionType type,int start, int limit) {
		StringBuffer sql = new StringBuffer();
		if(countQuery){
			sql.append("SELECT count(*) ");
		}
		sql.append("FROM Exhibition a WHERE a.active = :active ");
		
		if (type != null){
			sql.append("and a.type = :type ");
		}
		if (keyword != null){
			sql.append("and a.name like :name ");
		}
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("active", true);
		if (type != null){
			query.setParameter("type", type);
		}
		if (keyword != null){
			query.setParameter("name", "%"+keyword +"%");
		}
		if(!countQuery){
			query.setFirstResult(start);
			query.setMaxResults(limit);
		}
		query.setHint("org.hibernate.cacheable", true);
		return query;
	}
}
