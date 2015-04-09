package com.heymilo.shop.product;

import java.util.List;

import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.entity.ProductStatus;
import com.heymilo.ui.param.ProductSaveModel;

public interface ProductService {
	List<Product> searchProduct(String keyword,Boolean newProduct,Boolean canSubscription,
			ProductStatus status,Category category,int start,int limits);
	
	int countProduct(String keyword,Boolean newProduct,Boolean canSubscription,ProductStatus status,Category category);
	Product load(Long id);
	
	Product saveProduct(ProductSaveModel productModel);
	
}
