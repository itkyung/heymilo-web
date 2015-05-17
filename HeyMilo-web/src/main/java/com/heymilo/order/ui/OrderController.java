package com.heymilo.order.ui;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.heymilo.identity.ILogin;
import com.heymilo.identity.UserService;
import com.heymilo.identity.entity.AddressBook;
import com.heymilo.identity.entity.User;
import com.heymilo.order.CartService;
import com.heymilo.order.OrderDTO;
import com.heymilo.order.OrderItemDto;
import com.heymilo.order.OrderService;
import com.heymilo.order.entity.CartItem;
import com.heymilo.order.entity.OrderItem;
import com.heymilo.shop.product.ProductService;

@Controller
public class OrderController {
	
	@Autowired
	private ILogin login;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 우선은 해당 Order생성은 회원만이 가능하다.
	 * @param orderDTO
	 * @param bindingResult
	 * @param model
	 * @return
	 * @throws IOException
	 */
	
	@RequestMapping(value = "/order/form", method = RequestMethod.POST)
	public String orderForm(@ModelAttribute OrderDTO orderDTO,BindingResult bindingResult, Model model) throws IOException {
		
		User user = login.getCurrentUser();
		
		//1.고객의 기본주소지를 찾아온다.
		AddressBook addressBook = userService.findUserActiveAddress(user);
		if(addressBook != null) {
			orderDTO.setAddress(addressBook.getAddress());
			orderDTO.setZipCode(addressBook.getZipCode());
			orderDTO.setDetailAddress(addressBook.getDetailAddress());
			orderDTO.setPhone1(addressBook.getPhone1());
			orderDTO.setPhone2(addressBook.getPhone2());
			orderDTO.setReceiveName(addressBook.getReceiveName());
			orderDTO.setAddressBookId(addressBook.getId());
		}
		
		
		//2.제품 아이디를 이용해서 제품정보를 읽어온다. 이때 장바구니 부터 왔으면 거기에서 데이타를 옮겨온다.
		if(orderDTO.getCartId() != null) {
			copyFromCart(orderDTO);
		}else{
			for(OrderItemDto oItem : orderDTO.getOrderItems()) {
				oItem.setProduct(productService.load(oItem.getProductId()));
				oItem.setItemCount(1);
				oItem.setTotalAmount( oItem.getUnitAmount() * oItem.getItemCount());
			}
		}
		
		model.addAttribute("orderInfo", orderDTO);
		
		return "order/orderForm";
	}
	
	@RequestMapping(value = "/order/action", method = RequestMethod.POST)
	public String orderAction(@ModelAttribute OrderDTO orderDTO,BindingResult bindingResult, Model model) throws IOException {
		
		
		return "";
	}
	
	private void copyFromCart(OrderDTO orderDTO) {
		
		List<CartItem> cartItems = cartService.findCartItems(orderDTO.getCartId());
		
		for(CartItem cItem : cartItems) {
			OrderItemDto oItem = new OrderItemDto();
			oItem.setItemCount(1);
			oItem.setProduct(cItem.getProduct());
			oItem.setProductId(cItem.getProduct().getId());
			oItem.setTotalAmount( oItem.getUnitAmount() * oItem.getItemCount());
			orderDTO.getOrderItems().add(oItem);
		}
		
	}
	
}
