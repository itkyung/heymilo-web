package com.heymilo.order;

import java.util.List;

import com.heymilo.order.entity.CartItem;

public interface CartService {
	
	List<CartItem> findCartItems(Long cartId);
	
	List<CartItem> findCartItemsByUser(Long userId);
}
