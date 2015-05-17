package com.heymilo.order;

import com.heymilo.shop.entity.Product;

public class OrderItemDto {
	
	private Long productId;
	
	private int itemCount;
	
	private double totalAmount;

	
	private Product product;
	
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getUnitAmount() {
		return product == null ? 0 : product.getMiloPrice().doubleValue();
	}

	
	
	
}
