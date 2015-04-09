package com.heymilo.order.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 단발성 주문
 * @author itkyung
 *
 */
@Entity
@DiscriminatorValue("ONETIME")
public class OneTimeOrder extends Order {
	
}
