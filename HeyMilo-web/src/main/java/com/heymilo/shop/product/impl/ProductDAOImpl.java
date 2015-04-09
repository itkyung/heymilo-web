package com.heymilo.shop.product.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.entity.ProductStatus;
import com.heymilo.shop.product.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public List<Product> searchProduct(String keyword, Boolean newProduct,
			Boolean canSubscription, ProductStatus status, Category category,
			int start, int limits) {
		Query query = makeQuery(false, keyword, newProduct, canSubscription, status, category);
		query.setFirstResult(start);
		query.setMaxResults(limits);
		
		return query.getResultList();
	}
	
	@Override
	public int countProduct(String keyword, Boolean newProduct,
			Boolean canSubscription, ProductStatus status, Category category) {
		Query query = makeQuery(true, keyword, newProduct, canSubscription, status, category);
		
		return ((Number)query.getSingleResult()).intValue();
	}
	
	private Query makeQuery(boolean countQuery,String keyword, Boolean newProduct,
			Boolean canSubscription, ProductStatus status, Category category){
		StringBuffer sql = new StringBuffer();
		
		if(countQuery){
			sql.append("SELECT count(*) "); 
		}
		sql.append("FROM Product a WHERE a.created <= :created ");
		
		if(keyword != null){
			sql.append("AND a.name like :keyword ");
		}
		
		if(newProduct != null){
			sql.append("AND a.newProduct = :newProduct ");
		}
		if(canSubscription != null){
			sql.append("AND a.canSubscription = :canSubscription ");
		}
		if(status != null){
			sql.append("AND a.status = :status ");
		}
		if(category != null){
			sql.append("AND :category in indices(a.categories) ");
		}
		
		Query query = em.createQuery(sql.toString());
		query.setParameter("created", new Date());
		if(newProduct != null){
			query.setParameter("newProduct", newProduct);
		}
		if(canSubscription != null){
			query.setParameter("canSubscription", canSubscription);
		}
		if(status != null){
			query.setParameter("status", status);
		}
		if(category != null){
			query.setParameter("category", category);
		}
		
		if(keyword != null){
			query.setParameter("keyword", "%"+keyword+"%");
		}
		query.setHint("org.hibernate.cacheable", true);
		return query;
	}

	@Override
	public Product load(Long id) {
		Query query = em.createQuery("FROM Product a WHERE a.id = :id");
		query.setParameter("id", id);
		query.setHint("org.hibernate.cacheable", true);
		return (Product)query.getSingleResult();
	}

	@Override
	public void save(Product product) {
		product.setCreated(new Date());
		product.setUpdated(new Date());
		em.persist(product);
	}

	@Override
	public void update(Product product) {
		product.setUpdated(new Date());
		em.merge(product);
	}

	
}
