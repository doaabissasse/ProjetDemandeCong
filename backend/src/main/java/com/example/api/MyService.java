package com.example.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/message")
public class MyService {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMessage() {
        return "{\"message\":\"Hello from Java EE!\"}";
    }
}