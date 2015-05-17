package com.heymilo.payment;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.heymilo.identity.entity.User;
import com.heymilo.order.entity.Order;

@Entity
@Table(name = PayCancel.TABLE_NAME)
public class PayCancel {
	public static final String TABLE_NAME = "HM_PAYMENT_CANCEL";
	
	@Id
	@Column(name = "PAYMENT_CANCEL_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	private BigDecimal cancelAmount;
	
	@ManyToOne
	private User user;
	
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

	public BigDecimal getCancelAmount() {
		return cancelAmount;
	}

	public void setCancelAmount(BigDecimal cancelAmount) {
		this.cancelAmount = cancelAmount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	
}
