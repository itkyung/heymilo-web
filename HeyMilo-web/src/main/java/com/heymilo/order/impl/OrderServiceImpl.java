package com.heymilo.order.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.heymilo.identity.entity.User;
import com.heymilo.order.OrderDAO;
import com.heymilo.order.OrderDTO;
import com.heymilo.order.OrderItemDto;
import com.heymilo.order.OrderService;
import com.heymilo.order.OrderUpdateDTO;
import com.heymilo.order.entity.DeliveryCompany;
import com.heymilo.order.entity.OneTimeOrder;
import com.heymilo.order.entity.Order;
import com.heymilo.order.entity.OrderItem;
import com.heymilo.order.entity.OrderStatus;
import com.heymilo.order.exception.OrderException;
import com.heymilo.order.exception.OutOfStockException;
import com.heymilo.payment.Payment;
import com.heymilo.payment.PaymentService;
import com.heymilo.shop.entity.Product;
import com.heymilo.shop.product.ProductService;
import com.heymilo.shop.product.StockService;
import com.heymilo.subscription.entity.SubscriptionOrder;
import com.heymilo.ui.param.OrderSearchModel;

@Service
public class OrderServiceImpl implements OrderService {
	
	private Log log = LogFactory.getLog(OrderServiceImpl.class);
	
	@Autowired 
	private PaymentService paymentService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderDAO orderDao;
	
	@Transactional
	@Override
	public OneTimeOrder createOrder(OrderDTO orderDto, User user)
			throws OrderException {
		
		Payment payment = null;
		
		try {
		//1. orderitem별로 validation을 한다. 재고수량 확인 및 재고를 Lock처리한다.
			checkStock(orderDto.getOrderItems());
			
			OneTimeOrder order = generateOrder(orderDto, user);
			
		//2. PG사에 승인요청을 한다. 
			payment = paymentService.requestPayment(order, orderDto.getPaymentParam());
		
		//3. 오더를 저장한다.
			orderDao.save(order);
			
		//4. 각각의 재고를 줄인다. 이때에 재고가 부족할수있으니 다시 체크한다.
			for(OrderItem orderItem : order.getItems()) {
				stockService.changeStock(orderItem.getProduct(), orderItem.getItemCount());
			}
			
			return order;
			
		} catch (OutOfStockException outOfStock) {
			log.info("OutOfStock ", outOfStock);
			if(payment != null) {
				//취소 처리를 한다.
				
			}
			throw outOfStock;
		} catch (Exception e) {
			log.error("Order Create Error ", e);
			if(payment != null) {
				//취소 처리를 한다.
				
			}
			throw new OrderException(e.getMessage());
		}
	}
	
	private void checkStock(List<OrderItemDto> itemDtos) throws OutOfStockException {
		for (OrderItemDto item : itemDtos) {
			if(!stockService.canOrder(item.getProductId(), item.getItemCount())) {
				throw new OutOfStockException("제품 " + item.getProductId() + "의 재고가 부족합니다.");
			}
		}
	}
	
	private OneTimeOrder generateOrder(OrderDTO orderDto, User user){
		OneTimeOrder order = new OneTimeOrder();
		
		order.setPhone1(orderDto.getPhone1());
		order.setPhone2(orderDto.getPhone2());
		order.setReceiveName(orderDto.getReceiveName());
		order.setZipCode(orderDto.getZipCode());
		order.setDetailAddress(orderDto.getDetailAddress());
		order.setAddress(orderDto.getAddress());
		order.setStatus(OrderStatus.ORDERD);
		order.setUser(user);
		order.setActive(true);
		
		if(order.getOrderNo() == null)
			order.setOrderNo(generateOrderNo(user.getId()));
		
		double totalPrice = 0;
		String productDesc=null;
		List<OrderItem> orderItems = Lists.newArrayList();
		order.setItems(orderItems);
		
		for(OrderItemDto itemDto : orderDto.getOrderItems()) {
			OrderItem item = new OrderItem();
			Product product = productService.load(itemDto.getProductId());
			item.setItemPrice(product.getMiloPrice());
			item.setProduct(product);
			item.setItemCount(itemDto.getItemCount());
			item.setCreatedAt(new Date());
			item.setOrder(order);
			orderItems.add(item);
			if(productDesc == null) {
				productDesc = product.getName() + " 외";
			}
			totalPrice += product.getMiloPrice().doubleValue();
		}
		
		order.setTotalPrice(new BigDecimal(totalPrice));
		order.setProductDesc(productDesc);
		
		return order;
	}

	public String generateOrderNo(Long memberId) {
		Date current = new Date();
		return String.valueOf(memberId + current.getTime());
	}
	
	@Transactional
	@Override
	public SubscriptionOrder createSubscriptionOrder(OrderDTO orderDto,
			User user) throws OrderException {
		
		
		
		
		
		return null;
	}

	@Override
	public List<OneTimeOrder> searchOrder(OrderSearchModel searchModel) {
		return orderDao.searchOneTimeOrder(searchModel);
	}
	
	

	@Override
	public int countOrder(OrderSearchModel searchModel) {
		return orderDao.countOneTimeOrder(searchModel);
	}

	@Override
	public OneTimeOrder loadOneTimeOrder(Long orderId) {
		Order order = orderDao.loadOrder(orderId);
		if(order instanceof OneTimeOrder) {
			//lazy요소인 item을 한번 get한다.
			order.getItems();
			return (OneTimeOrder)order;
		}
		
		return null;
	}

	/**
	 * 일단 이곳에서 오더의 Status이동에 대해서 관리한다.
	 * 어드민이 처리할수있는 상태이다.
	 */
	@Override
	public List<OrderStatus> getNextStatusByAdmin(OrderStatus status) {
		List<OrderStatus> nextStatus = Lists.newArrayList();
		
		switch(status) {
		case ORDERD:
			nextStatus.add(OrderStatus.ORDERD);
			nextStatus.add(OrderStatus.SHIPPING);
			nextStatus.add(OrderStatus.COMPLETED);
			nextStatus.add(OrderStatus.CANCELED);
			break;
		case SHIPPING:
			nextStatus.add(OrderStatus.ORDERD);
			nextStatus.add(OrderStatus.SHIPPING);
			nextStatus.add(OrderStatus.COMPLETED);
			nextStatus.add(OrderStatus.RETURN_REQ);
			nextStatus.add(OrderStatus.RETURNED);
			break;
		case CANCEL_REQ:
			nextStatus.add(OrderStatus.CANCEL_REQ);
			nextStatus.add(OrderStatus.CANCELED);
			//nextStatus.add(OrderStatus.COMPLETED);
			break;
		case RETURN_REQ:
			nextStatus.add(OrderStatus.RETURN_REQ);
			nextStatus.add(OrderStatus.RETURNED);
			break;	
		}
		
		
		return nextStatus;
	}

	@Transactional
	@Override
	public OneTimeOrder upateOrder(OrderUpdateDTO dto) throws OrderException {
		
		OneTimeOrder order = (OneTimeOrder)orderDao.loadOrder(dto.getOrderId());
		
		if(!dto.getOrderStatus().equals(OrderStatus.ORDERD)) {
			//주문완료상태가 아니면 운송장번호를 저장한다.
			order.setShippingNo(dto.getShippingNo());
			order.setDeliveryCompany(DeliveryCompany.valueOf(dto.getDeliveryCompany()));
		}
		
		if(order.getStatus() != dto.getOrderStatus()) {
			//상태가 변경된 경우임.
			switch(dto.getOrderStatus()) {
			case SHIPPING:
				order.setShippingStartedAt(new Date());
				break;
			case COMPLETED:
				order.setCompletedAt(new Date());
				break;
			case CANCEL_REQ:
				order.setCancelRequestedAt(new Date());
				break;
			case RETURN_REQ:
				order.setReturnRequestedAt(new Date());
				break;
			}
		}
		
		order.setStatus(dto.getOrderStatus());
		order.setUpdatedAt(new Date());
		
		orderDao.update(order);
		
		return order;
	}
	
	
	
}
