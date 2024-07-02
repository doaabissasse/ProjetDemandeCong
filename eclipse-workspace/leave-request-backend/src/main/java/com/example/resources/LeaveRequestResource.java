package com.example.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/leaves")
public class LeaveRequestResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLeaves() {
        List<String> leaves = Collections.singletonList("{\"message\":\"Hello, world!\"}");
        return Response.ok(leaves).build();
    }
}

