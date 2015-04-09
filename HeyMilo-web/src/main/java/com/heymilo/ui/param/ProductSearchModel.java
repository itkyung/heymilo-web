package com.heymilo.ui.param;

import com.heymilo.shop.entity.ProductStatus;

public class ProductSearchModel extends DataTableSearchModel {
	private String keyword;
	private Long categoryId;
	private Boolean newProduct;
	private Boolean canSubscription;
	private ProductStatus status;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Boolean getNewProduct() {
		return newProduct;
	}
	public void setNewProduct(Boolean newProduct) {
		this.newProduct = newProduct;
	}
	public Boolean getCanSubscription() {
		return canSubscription;
	}
	public void setCanSubscription(Boolean canSubscription) {
		this.canSubscription = canSubscription;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	
	
}
