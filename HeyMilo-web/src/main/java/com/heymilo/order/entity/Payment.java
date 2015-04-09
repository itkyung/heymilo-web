package com.heymilo.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heymilo.identity.entity.User;

@Entity
@Table(name=Payment.TABLE_NAME)
public class Payment {
	public static final String TABLE_NAME = "HM_PAYMENT";
	
	@Id
	@Column(name = "PAYMENT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Enumerated(EnumType.STRING)
	private PgType pgType;
	
	@Enumerated(EnumType.STRING)
	private PayType payType;
	
	@ManyToOne
	private User user;
	
	private BigDecimal payAmount;	//결제 금액 
	
	private String pgTid;	//PG Tid
	
	private String pgCode;	//PG Result Code;
	
	private String pgMessage;	//PG Result Message;
	
	private boolean success;	//성공여부. 승인요청 실패시에는 false로 들어감.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public PgType getPgType() {
		return pgType;
	}

	public void setPgType(PgType pgType) {
		this.pgType = pgType;
	}

	public PayType getPayType() {
		return payType;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getPgTid() {
		return pgTid;
	}

	public void setPgTid(String pgTid) {
		this.pgTid = pgTid;
	}

	public String getPgCode() {
		return pgCode;
	}

	public void setPgCode(String pgCode) {
		this.pgCode = pgCode;
	}

	public String getPgMessage() {
		return pgMessage;
	}

	public void setPgMessage(String pgMessage) {
		this.pgMessage = pgMessage;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
