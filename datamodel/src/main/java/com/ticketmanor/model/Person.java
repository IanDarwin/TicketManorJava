package com.ticketmanor.model;

import java.io.Serializable;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Person - One individual person who has some interaction with the system.
 */
@Entity @Table(name="people")
@XmlRootElement
@Inheritance(strategy=InheritanceType.JOINED)
public class Person implements Serializable {

	private static final long serialVersionUID = -9182347783644834607L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	@NotNull
	protected String firstName;
	protected String middles;
	@NotNull
	protected String lastName;
	// Minimal email validation: name@host.something
	@Pattern(regexp="\\w+@\\w+\\..*")
	String email;
	@Embedded
	Address address;
	
	public Person() {
		// EMPTY
	}
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddles() {
		return middles;
	}

	public void setMiddles(String middles) {
		this.middles = middles;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + ' ' + getFullName();
	}

	@Transient
	public String getFullName() {
		StringBuilder sb = new StringBuilder(firstName);
		if (middles != null) {
			sb.append(" ").append(middles);
		}
		sb.append(" ").append(lastName);
		return sb.toString();
	}
}
