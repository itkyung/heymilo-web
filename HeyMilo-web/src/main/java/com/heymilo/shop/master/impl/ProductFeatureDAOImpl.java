package com.heymilo.shop.master.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.heymilo.shop.entity.ProductFeature;
import com.heymilo.shop.master.ProductFeatureDAO;

@Repository
public class ProductFeatureDAOImpl implements ProductFeatureDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public ProductFeature load(Long id) {
		Query query = em.createQuery("From ProductFeature a WHERE a.id = :id");
		query.setParameter("id", id);
		query.setHint("org.hibernate.cacheable", true);
		return (ProductFeature)query.getSingleResult();
	}

	@Override
	public void save(ProductFeature entity) {
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		em.persist(entity);
	}

	@Override
	public void update(ProductFeature entity) {
		entity.setUpdated(new Date());
		em.merge(entity);
	}

	@Override
	public List<ProductFeature> search(String keyword, int start, int limits) {
		StringBuffer sql = new StringBuffer("FROM ProductFeature a WHERE a.active = :active ");
		if(keyword != null){
			sql.append("AND a.name like :keyword");
		}
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("active", true);
		if(keyword != null){
			query.setParameter("keyword", "%"+keyword+"%");
		}
		query.setFirstResult(start);
		query.setMaxResults(limits);
		query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	@Override
	public int countSearch(String keyword) {
		StringBuffer sql = new StringBuffer("SELECT count(*) FROM ProductFeature a WHERE a.active = :active ");
		if(keyword != null){
			sql.append("AND a.name like :keyword");
		}
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("active", true);
		if(keyword != null){
			query.setParameter("keyword", "%"+keyword+"%");
		}
		query.setHint("org.hibernate.cacheable", true);
		return ((Number)query.getSingleResult()).intValue();
	}

}
