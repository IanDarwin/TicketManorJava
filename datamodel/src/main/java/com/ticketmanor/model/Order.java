package com.ticketmanor.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
