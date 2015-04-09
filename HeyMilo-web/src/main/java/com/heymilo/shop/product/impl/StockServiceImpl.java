package com.heymilo.shop.product.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.heymilo.order.exception.OutOfStockException;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.product.ProductDAO;
import com.heymilo.shop.product.ProductService;
import com.heymilo.shop.product.StockService;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private ProductDAO productDao;

	@Override
	public boolean canOrder(Long productId, int count) {
		return canOrder(productDao.load(productId), count);
	}

	private boolean canOrder(Product product, int count) {
		synchronized (product) {
			if (product.getTotalCount() - product.getSalesCount() >= count) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Transactional
	@Override
	public void changeStock(Product product, int varitaionCount)
			throws OutOfStockException {
		if (!canOrder(product, varitaionCount)) {
			throw new OutOfStockException("[" + product.getName()
					+ "] 의 재고가 부족합니다.");
		}
		synchronized (product) {
			product.setSalesCount(product.getSalesCount() + varitaionCount);
			productDao.update(product);
		}
	}

}
