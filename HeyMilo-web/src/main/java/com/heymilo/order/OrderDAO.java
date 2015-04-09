package com.heymilo.order;

import java.util.List;

import com.heymilo.order.entity.OneTimeOrder;
import com.heymilo.order.entity.Order;
import com.heymilo.order.entity.SubscriptionOrder;
import com.heymilo.ui.param.OrderSearchModel;

public interface OrderDAO {
	void save(OneTimeOrder order);
	void update(OneTimeOrder order);
	
	void save(SubscriptionOrder order);
	void update(SubscriptionOrder order);
	
	Order loadOrder(Long order);
	List<OneTimeOrder> searchOneTimeOrder(OrderSearchModel searchModel);
	int countOneTimeOrder(OrderSearchModel searchModel);
}
