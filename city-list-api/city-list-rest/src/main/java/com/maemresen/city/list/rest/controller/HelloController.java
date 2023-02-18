package com.maemresen.city.list.rest.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import org.springframework.stereotype.Service;

@Path("/hello")
@Service
public class HelloController implements AppController{

	@GET
	@Produces("text/plain")
	public String hello() {
		return "Hello from Spring";
	}
}
