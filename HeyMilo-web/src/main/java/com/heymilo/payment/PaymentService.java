package com.heymilo.payment;

import com.heymilo.order.entity.Order;

public interface PaymentService {
	
	Payment requestPayment(Order order,PaymentDTO paymentParam) throws Exception;
	
}
