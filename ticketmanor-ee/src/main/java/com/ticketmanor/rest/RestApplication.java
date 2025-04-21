package com.ticketmanor.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/** This class is needed to enable REST and to specify the base path for REST services */
@ApplicationPath("/rest")
public class RestApplication extends Application {
	// Empty
}
