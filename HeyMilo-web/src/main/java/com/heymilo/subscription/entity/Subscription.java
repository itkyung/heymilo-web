package com.heymilo.subscription.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heymilo.identity.entity.User;
import com.heymilo.shop.entity.Product;

@Entity
@Table(name=Subscription.TABLE_NAME)
public class Subscription {
	public static final String TABLE_NAME = "HM_SUBSCRIPTION";
	
	@Id
	@Column(name = "SUBSCRIPTION_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	
	@ManyToOne
	private Product product;
	
	@Enumerated(EnumType.STRING)
	private SubscriptionPeriod period;
	
	private int itemCount;	//한번에 주문되는 수량.
	
	private BigDecimal price;	//매번 주문시점에 승인될 금액 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public SubscriptionPeriod getPeriod() {
		return period;
	}

	public void setPeriod(SubscriptionPeriod period) {
		this.period = period;
	}

	

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
}
