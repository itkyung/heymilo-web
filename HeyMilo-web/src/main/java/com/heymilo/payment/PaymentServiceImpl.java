package com.heymilo.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.order.entity.Order;


@Service
public class PaymentServiceImpl implements PaymentService{
	
	
	/**
	 * PG사에 승인요청을 하고 Payment정보를 생성해서 저장한다.
	 */
	@Transactional
	@Override
	public Payment requestPayment(Order order, PaymentDTO paymentParam) throws Exception {
		Payment payment = new Payment();
		
		
		
		
		return payment;
	}
	
	
	
}
