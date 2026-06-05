package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.Kuvert;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.Optional;

@Path("/kuvert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class KuvertResource {

    @Inject
    MockDataService dataService;

    @GET
    @Path("/{kuvertId}")
    public Response getKuvert(@PathParam("kuvertId") String kuvertId) {
        Optional<Kuvert> k = dataService.findKuvert(kuvertId);
        if (k.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(k.get()).build();
    }
}
