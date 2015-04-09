package com.heymilo.shop.product;

import java.util.List;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.entity.ProductStatus;

public interface ProductDAO {
	
	List<Product> searchProduct(String keyword,Boolean newProduct,Boolean canSubscription,
			ProductStatus status,Category category,int start,int limits);
	
	int countProduct(String keyword,Boolean newProduct,Boolean canSubscription,ProductStatus status,Category category);
	
	Product load(Long id);
	
	void save(Product product);
	void update(Product product);
}
