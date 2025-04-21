package com.ticketmanor.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Auditorium - a part of a Venue where Events play out.
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity @Table(name="auditoriums")
public class Auditorium implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;

	String name;
	String address;
	// The intention here is that one Venue might have several Auditoria within it,
	// like a Multiplex cinema, and we'd need to track those in real life.
	@ManyToOne Venue venue;
}
