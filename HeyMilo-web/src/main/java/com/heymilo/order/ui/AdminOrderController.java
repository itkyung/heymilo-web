package com.heymilo.order.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.heymilo.common.CommonUtils;
import com.heymilo.identity.ILogin;
import com.heymilo.identity.UserService;
import com.heymilo.identity.entity.User;
import com.heymilo.order.OrderDTO;
import com.heymilo.order.OrderItemDto;
import com.heymilo.order.OrderListDTO;
import com.heymilo.order.OrderService;
import com.heymilo.order.OrderUpdateDTO;
import com.heymilo.order.entity.DeliveryCompany;
import com.heymilo.order.entity.OneTimeOrder;
import com.heymilo.order.exception.OrderException;
import com.heymilo.shop.entity.Category;
import com.heymilo.shop.entity.Product;
import com.heymilo.ui.DatatableJson;
import com.heymilo.ui.param.OrderSearchModel;
import com.heymilo.ui.param.ProductSearchModel;

@Controller
public class AdminOrderController {
	
	private static Log log = LogFactory.getLog(AdminOrderController.class);
	
	@Autowired
	private ILogin login;	
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/admin/testOrderCreate", method = RequestMethod.GET)
	public String testOrderCreate() throws IOException  {
		
		User user = userService.loadUser(5L);
		
		
		OrderDTO orderDto = new OrderDTO();
		orderDto.setAddress("서울시 송파구 잠실동");
		orderDto.setDetailAddress("엘스아파트");
		orderDto.setPhone1("010-111-2222");
		orderDto.setPhone2("010-222-2333");
		orderDto.setZipCode("135-101");
		orderDto.setReceiveName("경인태");
		
		
		OrderItemDto itemDto1 = new OrderItemDto();
		itemDto1.setItemCount(5);
		itemDto1.setProductId(2L);
		orderDto.getOrderItems().add(itemDto1);
		
		OrderItemDto itemDto2 = new OrderItemDto();
		itemDto2.setItemCount(3);
		itemDto2.setProductId(3L);
		orderDto.getOrderItems().add(itemDto2);
		
		
		try {
			orderService.createOrder(orderDto, user);
		} catch(OrderException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@RequestMapping(value = "/admin/listOrders", method = RequestMethod.GET)
	public String listOrders() throws IOException {
		return "admin/order/listOrders";
	}
	
	@RequestMapping(value = "/admin/searchOrders", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String searchOrders(@ModelAttribute OrderSearchModel condition, BindingResult bindingResult) throws IOException{
		int _perPage = condition.getLength();
		
		condition.setStart(condition.getStart());
		condition.setLimit(_perPage);
		
		List<OrderListDTO> results = new ArrayList<OrderListDTO>();
		
		List<OneTimeOrder> orders = orderService.searchOrder(condition);
		for(OneTimeOrder order : orders) {
			OrderListDTO dto = new OrderListDTO(order);
			results.add(dto);
		}
		
		int totalCount = orderService.countOrder(condition);
		
		int totalPage = 0;
		if(totalCount > 0){
			totalPage =  (totalCount % _perPage) == 0 ? totalCount/_perPage : (totalCount/_perPage)+1;
		}
		
		DatatableJson json = new DatatableJson(condition.getDraw(), totalCount, results.size(), results.toArray(), condition.getStart(), condition.getLimit());
		
		return CommonUtils.toJson(json);
	}
	
	@RequestMapping(value = "/admin/orderForm", method = RequestMethod.GET)
	public String orderForm(@RequestParam("id") Long orderId, Model model) throws IOException {
		OneTimeOrder order = orderService.loadOneTimeOrder(orderId);
		
		model.addAttribute("orderInfo", order);
		model.addAttribute("nextStatusList", orderService.getNextStatusByAdmin(order.getStatus()));
		model.addAttribute("deliveryCompany", DeliveryCompany.values());
		
		return "admin/order/orderForm";
	}
	
	@RequestMapping(value = "/admin/orderUpdate", method = RequestMethod.POST)
	public String orderUpdate(@ModelAttribute OrderUpdateDTO updateDto, BindingResult result) throws IOException {
		try {
			orderService.upateOrder(updateDto);
		} catch (OrderException oe) {
			log.error("Error ", oe);
		}
		
		return "redirect:/admin/orderForm?id=" + updateDto.getOrderId();
	}
	
}
