package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import se.handelsbanken.iaem.model.Debiteringsuppgift;
import se.handelsbanken.iaem.model.DebiteringsuppgiftInput;
import se.handelsbanken.iaem.service.MockDataService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/debiteringsuppgifter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DebiteringsuppgifterResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response list() {
        List<Debiteringsuppgift> list = dataService.getDebiteringsuppgifter();
        return Response.ok(list).build();
    }

    @POST
    public Response create(DebiteringsuppgiftInput input) {
        Debiteringsuppgift created = dataService.createDebiteringsuppgift(input);
        URI location = UriBuilder.fromResource(DebiteringsuppgifterResource.class)
                .path("{produktid}")
                .build(created.produktid);
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{produktid}")
    public Response update(
            @PathParam("produktid") String produktid,
            DebiteringsuppgiftInput input) {
        Optional<Debiteringsuppgift> updated = dataService.updateDebiteringsuppgift(produktid, input);
        if (updated.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated.get()).build();
    }

    @DELETE
    @Path("/{produktid}")
    public Response delete(@PathParam("produktid") String produktid) {
        boolean deleted = dataService.deleteDebiteringsuppgift(produktid);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
