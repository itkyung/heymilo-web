package com.heymilo.order.exception;

public class OutOfStockException extends OrderException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2148604501892002108L;

	public OutOfStockException(String msg){
		super(msg);
	}
}
