package com.ticketmanor.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	String street;
	String city;
	String state;
	String country;
	String postcode;
}
