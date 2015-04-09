package com.heymilo.subscription.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heymilo.order.OrderService;
import com.heymilo.subscription.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired
	private OrderService orderService;
	
	
}
