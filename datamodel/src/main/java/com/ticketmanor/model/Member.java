package com.ticketmanor.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;

/*
 * Member - A Person who has signed up for the site.
 */
@XmlRootElement
@Entity
@Table(name="members")
public class Member extends Person  {
	private static final long serialVersionUID = -4101899127440929717L;
	
	// the id property is inherited
	/** The profile photo. A full implementation of the UI will support uploading this. */
	private byte[] profilePhoto;

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
}
