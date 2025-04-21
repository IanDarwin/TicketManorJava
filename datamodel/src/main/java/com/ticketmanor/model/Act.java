package com.ticketmanor.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * An Act is one entity that people pay to see: A movie, a soloist, a troupe, a rock group, an orchestra...
 */
@XmlRootElement @JsonIgnoreProperties(ignoreUnknown=true)
@Entity @Table(name="acts")
@Inheritance(strategy=InheritanceType.JOINED)
public class Act implements Serializable {

	private static final long serialVersionUID = -1765800918306693071L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;

	String title;
	ActType type;
	int year;
	String notes;
	@OneToMany
	List<Event> events;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	public ActType getType() {
		return type;
	}

	public void setType(ActType type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	/** A list of recordings related to this Act that we can sell;
	 * can be movie soundtracks, music videos, etc. */
	// List<Recording> discography;
	
	public Act() {
		// required by JPA
	}
	
	public Act(ActType type) {
		this.type = type;
	}
	
	public Act(ActType type, String title) {
		this.type = type;
		this.title = title;
	}
	
	@Override
	public String toString() {
		return title;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((notes == null) ? 0 : notes.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + year;
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
		Act other = (Act) obj;
		if (id != other.id)
			return false;
		if (notes == null) {
			if (other.notes != null)
				return false;
		} else if (!notes.equals(other.notes))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
}
