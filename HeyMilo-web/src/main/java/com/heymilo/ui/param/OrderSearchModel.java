package com.heymilo.ui.param;

import com.heymilo.order.entity.OrderStatus;

public class OrderSearchModel extends DataTableSearchModel {
	
	private OrderStatus status;
	private String orderNo;
	private Long buyerId;
	
	public OrderStatus getStatus() {
		return status;
	}
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Long buyerId) {
		this.buyerId = buyerId;
	}
	
	
}
