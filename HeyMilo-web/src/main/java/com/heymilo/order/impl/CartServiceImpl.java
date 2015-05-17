package com.heymilo.order.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heymilo.order.CartService;
import com.heymilo.order.entity.CartItem;

@Service
public class CartServiceImpl implements CartService {

	@Override
	public List<CartItem> findCartItems(Long cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartItem> findCartItemsByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
