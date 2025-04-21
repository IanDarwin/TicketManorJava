package com.ticketmanor.rest;

import java.util.List;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import com.ticketmanor.model.Venue;

@Path("/venues")
public class VenueResource {

	@PersistenceUnit EntityManagerFactory emf; // Web tier is multi-threaded
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Venue> getConcerts() {
		return emf
				.createEntityManager()
				.createQuery("FROM Venue", Venue.class)
				.getResultList();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	@Transactional(value=TxType.REQUIRED)
	public Venue getItem(@PathParam("id") long id) {
		System.out.println("VenueResource.getItem(" + id + ")");
		return emf.createEntityManager().find(Venue.class, id);
	}
}
