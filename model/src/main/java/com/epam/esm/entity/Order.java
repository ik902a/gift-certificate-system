package com.epam.esm.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="orders")
public class Order extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name = "date")
	private ZonedDateTime date;
	@Column(name="cost")
	private BigDecimal cost;
	@Column(name="number")
	private int number;
	
	
	
	
	
	public Order() {
		super();
	}
	

	
	

}
