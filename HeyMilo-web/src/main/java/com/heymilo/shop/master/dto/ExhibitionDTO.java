package com.heymilo.shop.master.dto;

import com.heymilo.shop.entity.ExhibitionType;

public class ExhibitionDTO {
	private Long id;
	private String name;
	private String desc;
	private boolean active;
	private ExhibitionType type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public ExhibitionType getType() {
		return type;
	}
	public void setType(ExhibitionType type) {
		this.type = type;
	}
	
}
