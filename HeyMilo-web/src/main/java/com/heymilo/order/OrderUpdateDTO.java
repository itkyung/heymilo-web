package com.heymilo.order;

import com.heymilo.order.entity.OrderStatus;

public class OrderUpdateDTO {
	
	private Long orderId;
	
	private OrderStatus orderStatus;
	
	private String shippingNo;
	
	private String deliveryCompany;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public String getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(String deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}
	
	
}
