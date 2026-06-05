package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.Kund;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.Optional;

@Path("/kunder")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class KunderResource {

    @Inject
    MockDataService dataService;

    @GET
    @Path("/{kundnr}")
    public Response getKund(
            @PathParam("kundnr") String kundnr,
            @QueryParam("land") String land) {

        Optional<Kund> kund = dataService.findKund(kundnr);
        if (kund.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Kund k = kund.get();
        if (land != null && !land.isBlank() && !land.equalsIgnoreCase(k.land)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(k).build();
    }
}
