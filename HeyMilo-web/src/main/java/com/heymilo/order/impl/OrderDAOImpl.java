package com.heymilo.order.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.heymilo.order.OrderDAO;
import com.heymilo.order.entity.OneTimeOrder;
import com.heymilo.order.entity.Order;
import com.heymilo.shop.entity.Product;
import com.heymilo.subscription.entity.SubscriptionOrder;
import com.heymilo.ui.param.OrderSearchModel;

@Repository
public class OrderDAOImpl implements OrderDAO {
	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	public void save(OneTimeOrder order) {
		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());
		em.persist(order);
	}

	@Override
	public void update(OneTimeOrder order) {
		order.setUpdatedAt(new Date());
		em.merge(order);
	}

	@Override
	public void save(SubscriptionOrder order) {
		order.setCreatedAt(new Date());
		order.setUpdatedAt(new Date());
		em.persist(order);
	}

	@Override
	public void update(SubscriptionOrder order) {
		order.setUpdatedAt(new Date());
		em.merge(order);
	}

	@Override
	public Order loadOrder(Long orderId) {
		Query query = em.createQuery("FROM Order a WHERE a.id = :id");
		query.setParameter("id", orderId);
		query.setHint("org.hibernate.cacheable", true);
		return (Order)query.getSingleResult();
	}

	@Override
	public List<OneTimeOrder> searchOneTimeOrder(OrderSearchModel searchModel) {
		Query query = makeQuery(false, searchModel);
		return query.getResultList();
	}

	@Override
	public int countOneTimeOrder(OrderSearchModel searchModel) {
		Query query = makeQuery(true, searchModel);
		return ((Number)query.getSingleResult()).intValue();
	}

	private Query makeQuery(boolean countQuery, OrderSearchModel searchModel) {
		StringBuilder strBuilder = new StringBuilder();
		if (countQuery) {
			strBuilder.append("SELECT count(*) ");
		}
		strBuilder.append("FROM OneTimeOrder a WHERE active = :active ");
		
		if(searchModel.getOrderNo() != null && !"".equals(searchModel.getOrderNo())) {
			strBuilder.append("AND a.orderNo = :orderNo ");
		} else {
			if(searchModel.getStatus() != null ){
				strBuilder.append("AND a.status = :status ");
			}
			if(searchModel.getBuyerId() != null) {
				strBuilder.append("AND a.user.loginId = :buyerId ");
			}
		}
		
		Query query = em.createQuery(strBuilder.toString());
		query.setParameter("active", true);
		if(searchModel.getOrderNo() != null && !"".equals(searchModel.getOrderNo())) {
			query.setParameter("orderNo", searchModel.getOrderNo());
		}else {
			if(searchModel.getStatus() != null ){
				query.setParameter("status", searchModel.getStatus());
			}
			if(searchModel.getBuyerId() != null) {
				query.setParameter("buyerId", searchModel.getBuyerId());
			}
		}
		
		if(!countQuery){
			query.setFirstResult(searchModel.getStart());
			query.setMaxResults(searchModel.getLimit());
		}
		query.setHint("org.hibernate.cacheable", true);
		return query;
	}
}
