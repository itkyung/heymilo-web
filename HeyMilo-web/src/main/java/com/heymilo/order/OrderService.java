package com.heymilo.order;

import java.util.List;

import com.heymilo.identity.entity.User;
import com.heymilo.order.entity.OneTimeOrder;
import com.heymilo.order.entity.Order;
import com.heymilo.order.entity.OrderStatus;
import com.heymilo.order.exception.OrderException;
import com.heymilo.subscription.entity.SubscriptionOrder;
import com.heymilo.ui.param.OrderSearchModel;

public interface OrderService {
	
	public OneTimeOrder createOrder(OrderDTO orderDto, User user) throws OrderException;
	
	public SubscriptionOrder createSubscriptionOrder(OrderDTO orderDto, User user) throws OrderException;
	
	public List<OneTimeOrder> searchOrder(OrderSearchModel searchModel);
	
	public int countOrder(OrderSearchModel searchModel);
	
	public OneTimeOrder loadOneTimeOrder(Long orderId);
	
	public List<OrderStatus> getNextStatusByAdmin(OrderStatus status);
	
	public OneTimeOrder upateOrder(OrderUpdateDTO dto) throws OrderException;
	
	public String generateOrderNo(Long memberId);
}
