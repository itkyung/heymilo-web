package com.heymilo.subscription.entity;

public enum SubscriptionPeriod {
	ONE_WEEK("일주일주기"),
	TWO_WEEKS("이주일주기"),
	ONE_MONTH("한달주기"),
	THREE_MONTH("석달주기");
	
	private SubscriptionPeriod(String desc){
		this.desc = desc;
	}
	
	private String desc;
	
	public String getDesc(){
		return desc;
	}
}
