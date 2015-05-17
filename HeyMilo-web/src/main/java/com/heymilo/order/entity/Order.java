package com.heymilo.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;
import com.heymilo.identity.entity.User;

@Entity
@Table(name=Order.TABLE_NAME)
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="ORDER_TYPE" , discriminatorType=DiscriminatorType.STRING)
public class Order {
	public static final String TABLE_NAME = "HM_ORDER";
	
	
	@Id
	@Column(name = "ORDER_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String orderNo;	//오더번호 12자리를 만들자.
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	
	@ManyToOne
	private User user;
	
	private BigDecimal delieveryPrice;	//배송비 
	
	private String shippingNo;	//출고번호. - invoiceNo
	
	@Enumerated(EnumType.STRING)
	private DeliveryCompany deliveryCompany;
	
	private BigDecimal totalPrice;
	
	private BigDecimal paymentPrice;	//고객이 실제로 결제하는 가격.
	
	private boolean active;
	
	private String productDesc;	//주문한 상품들에 대한 내용정보..
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	//배송지 정보.
	private String zipCode;
	
	private String address;
	
	private String detailAddress;
	
	private String phone1;	//수령지 전화번호 
	
	private String phone2;	//전화번호 2
	
	private String receiveName;	//수령자 이름 
	
	private Date shippingStartedAt;	//출고시간. 
	
	private Date completedAt;	//배송도착시간
	
	private Date cancelRequestedAt;	//취소요청시간.
	
	private Date returnRequestedAt;
	
	@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("createdAt")
	private List<OrderItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
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

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getDelieveryPrice() {
		return delieveryPrice;
	}

	public void setDelieveryPrice(BigDecimal delieveryPrice) {
		this.delieveryPrice = delieveryPrice;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public DeliveryCompany getDeliveryCompany() {
		return deliveryCompany;
	}

	public void setDeliveryCompany(DeliveryCompany deliveryCompany) {
		this.deliveryCompany = deliveryCompany;
	}

	public Date getShippingStartedAt() {
		return shippingStartedAt;
	}

	public void setShippingStartedAt(Date shippingStartedAt) {
		this.shippingStartedAt = shippingStartedAt;
	}

	public Date getCompletedAt() {
		return completedAt;
	}

	public void setCompletedAt(Date completedAt) {
		this.completedAt = completedAt;
	}

	public Date getCancelRequestedAt() {
		return cancelRequestedAt;
	}

	public void setCancelRequestedAt(Date cancelRequestedAt) {
		this.cancelRequestedAt = cancelRequestedAt;
	}

	public Date getReturnRequestedAt() {
		return returnRequestedAt;
	}

	public void setReturnRequestedAt(Date returnRequestedAt) {
		this.returnRequestedAt = returnRequestedAt;
	}

	public BigDecimal getPaymentPrice() {
		return paymentPrice;
	}

	public void setPaymentPrice(BigDecimal paymentPrice) {
		this.paymentPrice = paymentPrice;
	}
	
	
}
