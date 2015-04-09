package com.heymilo.shop.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heymilo.identity.entity.User;

/**
 * 상품평 엔티티 
 * @author itkyung
 *
 */
@Entity
@Table(name=ProductComment.TABLE_NAME)
public class ProductComment {

	public static final String TABLE_NAME = "HM_PRODUCT_COMMENT";
	
	@Id
	@Column(name = "COMMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Product product;
	
	private double point; //별점.
	
	@ManyToOne
	private User owner;
	
	@Column(length=1024)
	private String title;	//한줄평 
	
	@Column(columnDefinition = "TEXT")
	private String contents;	//내용 
	
	private String nickName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
	
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
