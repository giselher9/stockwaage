package com.stockwaage.service.resources;

import com.stockwaage.service.dto.*;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/helloworld")
public class HelloWorldResource {
    
	@GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldDto healthCheck() {
        return new HelloWorldDto("huhu");
    }
	
}