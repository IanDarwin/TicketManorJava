package com.ticketmanor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Order - An order is one or more Tickets for one Member (a.k.a. "customer")
 */
@XmlRootElement
@Entity @Table(name="orders")
public class Order implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	@ManyToOne
	Member member;
	@OneToMany
	List<OrderItem> items = new ArrayList<>();
}
