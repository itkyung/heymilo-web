package com.heymilo.shop.master.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.master.CategoryDAO;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public Category loadCategory(Long id) {
		Query query = em.createQuery("FROM Category a WHERE a.id = :id");
		query.setParameter("id", id);
		query.setHint("org.hibernate.cacheable", true);
		return (Category)query.getSingleResult();
	}

	
	
	@Override
	public int countCategory() {
		Query query = em.createQuery("SELECT count(*) FROM Category a WHERE a.active = :active");
		query.setParameter("active", true);
		query.setHint("org.hibernate.cacheable", true);
		return ((Number)query.getSingleResult()).intValue();
	}



	@Override
	public List<Category> listCategories(int start, int limits) {
		Query query = em.createQuery("FROM Category a WHERE a.active = :active");
		query.setParameter("active", true);
		query.setFirstResult(start);
		query.setMaxResults(limits);
		query.setHint("org.hibernate.cacheable", true);
		return query.getResultList();
	}

	@Override
	public void saveCategory(Category entity) {
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		em.persist(entity);
	}

	@Override
	public void updateCategory(Category entity) {
		entity.setUpdated(new Date());
		em.merge(entity);
	}

}
