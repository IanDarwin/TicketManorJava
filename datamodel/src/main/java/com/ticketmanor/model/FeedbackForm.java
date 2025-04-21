package com.ticketmanor.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * FeedbackForm records a feedback item, complaint,
 * suggestion, etc., from a (real or potential) customer.
 * Since we don't require end-users to sign up just for this, we don't
 * have a ManyToOne with Person; just use a String.
 * @author Ian Darwin
 */
@Entity
public class FeedbackForm implements Serializable {

	private static final long serialVersionUID = 590957238181679158L;

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	String custName;
	String custEmail;
	LocalDateTime date = LocalDateTime.now();
	String comment;
	
	/**
	 * @param id The primary key
	 * @param custName The customer name
	 * @param custEmail The customer email
	 * @param date The date the comment was made
	 * @param comment The actual text of the comment.
	 */
	public FeedbackForm(long id, String custName, String custEmail,
			LocalDateTime date, String comment) {
		super();
		this.id = id;
		this.custName = custName;
		this.custEmail = custEmail;
		this.date = date;
		this.comment = comment;
	}
	
	public FeedbackForm() {
		// Needed for APIs
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
