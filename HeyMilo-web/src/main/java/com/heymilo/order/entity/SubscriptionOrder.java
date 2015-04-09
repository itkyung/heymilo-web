package com.heymilo.order.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("SUBSCRIPTION")
public class SubscriptionOrder extends Order {
	
	@ManyToOne
	private Subscription subscription;
}
