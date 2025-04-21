package com.ticketmanor.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Venue - A place where Events happen. Some (such as modern movie theatres) have more than one Auditorium;
 * others (such as large concert halls) do not.
 */
@Entity @Table(name="venues")
@XmlRootElement
public class Venue implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	String name;
	/** The location */
	String streetAddress, 
		city, 
		provState, 
		country;
	/** The Geometry coordinates; N.B. May be null! */
	Double lat, lng;
	
	@OneToMany(mappedBy="venue")
	Set<Auditorium> auditoria = new HashSet<>();
	
	public Venue() {
		// empty
	}
	
	public Venue(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((auditoria == null) ? 0 : auditoria.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((provState == null) ? 0 : provState.hashCode());
		result = prime * result
				+ ((streetAddress == null) ? 0 : streetAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venue other = (Venue) obj;
		if (auditoria == null) {
			if (other.auditoria != null)
				return false;
		} else if (!auditoria.equals(other.auditoria))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (provState == null) {
			if (other.provState != null)
				return false;
		} else if (!provState.equals(other.provState))
			return false;
		if (streetAddress == null) {
			if (other.streetAddress != null)
				return false;
		} else if (!streetAddress.equals(other.streetAddress))
			return false;
		return true;
	}

	public String toString() {
		return name;
	};
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}
