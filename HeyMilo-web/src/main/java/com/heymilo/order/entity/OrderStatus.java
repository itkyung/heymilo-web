package com.heymilo.order.entity;

public enum OrderStatus {
	ORDERD("결재완료"),SHIPPING("배송중"),COMPLETED("배송완료"),CANCELED("취소완료"),CANCEL_REQ("취소요청"),RETURN_REQ("반품요청"),RETURNED("반품완료");
	
	private OrderStatus(String desc){
		this.desc = desc;
	}
	
	private String desc;
	
	public String getDesc(){
		return desc;
	}
}
