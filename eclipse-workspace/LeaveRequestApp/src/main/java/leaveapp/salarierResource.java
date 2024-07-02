package leaveapp;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/salarier")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class salarierResource {

    private final salarierDAO salarierDAO = new salarierDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<salarier> getAllSalarier() {
        return salarierDAO.findAll();
    }
}

