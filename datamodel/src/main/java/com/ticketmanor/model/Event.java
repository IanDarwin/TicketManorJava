package com.ticketmanor.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/*
 * Event - An Event is one act at one time at one Venue
 */
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.ticketmanor.model.jackson.MyLocalDateTimeDeserializer;
import com.ticketmanor.model.jackson.MyLocalDateTimeSerializer;

@XmlRootElement
@Entity @Table(name="events")
public class Event   {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	/** The Act; may be a movie, a troupe, a rock group, etc. */
	@ManyToOne
	Act what;
	/** When this Event is scheduled for (to the minute, e.g., 2014-11-11T11:11) */
	@Column(name="date_time")
	@JsonSerialize(using = MyLocalDateTimeSerializer.class)
	@JsonDeserialize(using = MyLocalDateTimeDeserializer.class)
	private LocalDateTime date;
	/** The Venue at which the event takes place. */
	@ManyToOne
	Venue venue;
	
	public Event(Act act, LocalDateTime dateTime, Venue venue) {
		this.what = act;
		this.setDate(dateTime);
		this.venue = venue;
	}
	
	public Event() {
		// required by JPA
	}

	public String toString() {
		return what + "@" + getDate() + " " + venue;
	}

	public long getId() {
		return id;
	}

	public Act getWhat() {
		return what;
	}
	public void setWhat(Act what) {
		this.what = what;
	}

	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Venue getVenue() {
		return venue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getDate() == null) ? 0 : getDate().hashCode());
		result = prime * result + ((what == null) ? 0 : what.hashCode());
		result = prime * result + ((venue == null) ? 0 : venue.hashCode());
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
		Event other = (Event) obj;
		if (getDate() == null) {
			if (other.getDate() != null)
				return false;
		} else if (!getDate().equals(other.getDate()))
			return false;
		if (what == null) {
			if (other.what != null)
				return false;
		} else if (!what.equals(other.what))
			return false;
		if (venue == null) {
			if (other.venue != null)
				return false;
		} else if (!venue.equals(other.venue))
			return false;
		return true;
	}
}
