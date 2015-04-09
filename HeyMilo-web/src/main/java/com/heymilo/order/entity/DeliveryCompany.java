package com.heymilo.order.entity;

public enum DeliveryCompany {
	HANJIN("한진택배"),POST("우체국택배");
	
	private DeliveryCompany(String desc){
		this.desc = desc;
	}
	
	private String desc;

	public String getDesc() {
		return desc;
	}
	
	
}
