package com.heymilo.subscription.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.heymilo.order.entity.Order;

@Entity
@DiscriminatorValue("SUBSCRIPTION")
public class SubscriptionOrder extends Order {
	
	@ManyToOne
	private Subscription subscription;
}
