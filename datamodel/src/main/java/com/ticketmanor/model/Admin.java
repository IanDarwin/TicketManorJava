package com.ticketmanor.model;

import jakarta.persistence.Entity;

/*
 * Admin - A Person who has administrative privileges
 */
@Entity
public class Admin extends Person  {

	private static final long serialVersionUID = -2510480063855738029L;

	// TODO Does the Admin need any special properties?
	// Or would it be better to dispense with this and use
	// Container Managed Security for administrative control?
}
