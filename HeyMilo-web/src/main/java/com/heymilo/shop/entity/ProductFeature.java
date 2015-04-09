package com.heymilo.shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;
import com.heymilo.common.IdVariableObject;

/**
 * 상품의 성분종류를 나타내는 엔티티
 * @author itkyung
 *
 */
@Entity
@Table(name=ProductFeature.TABLE_NAME)
public class ProductFeature implements IdVariableObject{
	public static final String TABLE_NAME = "HM_PRODUCT_FEATURE";
	@Expose
	@Id
	@Column(name = "FEATURE_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Expose
	@Column(length=128)
	private String name;
	
	@Expose
	@Column(name = "FEATURE_DESC",length=2048)
	private String desc;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	@Expose
	private boolean active;
	
	@Expose
	@Column(length=128)
	private String imagePath;
	
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
