package com.heymilo.shop.product;

import com.heymilo.order.exception.OutOfStockException;
import com.heymilo.shop.entity.Product;

public interface StockService {
	boolean canOrder(Long productId,int count);
	void changeStock(Product product, int varitaionCount) throws OutOfStockException;
}
