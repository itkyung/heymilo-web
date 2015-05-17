package com.heymilo.order;

import java.util.ArrayList;
import java.util.List;

import com.heymilo.payment.PaymentDTO;

public class OrderDTO {
	
	private double totalPrice;
	
	//배송지 정보.
	private String zipCode;
	
	private String address;
	
	private String detailAddress;
	
	private String phone1;	//수령지 전화번호 
	
	private String phone2;	//전화번호 2
	
	private String receiveName;	//수령자 이름  
	
	private Long cartId;	//장바구니 아이디 
	
	private Long addressBookId;	//주소록 아이디
	
	private List<OrderItemDto> orderItems = new ArrayList<OrderItemDto>();

	private PaymentDTO paymentParam = new PaymentDTO();
	
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
		this.orderItems = orderItems;
	}

	public PaymentDTO getPaymentParam() {
		return paymentParam;
	}

	public void setPaymentParam(PaymentDTO paymentParam) {
		this.paymentParam = paymentParam;
	}

	public Long getAddressBookId() {
		return addressBookId;
	}

	public void setAddressBookId(Long addressBookId) {
		this.addressBookId = addressBookId;
	}

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	
	
}	
