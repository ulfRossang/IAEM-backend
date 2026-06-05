package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import se.handelsbanken.iaem.model.MassutskickInput;
import se.handelsbanken.iaem.model.MassutskickItem;
import se.handelsbanken.iaem.service.MockDataService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/massutskick")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MassutskickResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response list() {
        List<MassutskickItem> list = dataService.getMassutskick();
        return Response.ok(list).build();
    }

    @POST
    public Response create(MassutskickInput input) {
        MassutskickItem created = dataService.createMassutskick(input);
        URI location = UriBuilder.fromResource(MassutskickResource.class)
                .path("{meddId}")
                .build(created.meddId);
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{meddId}")
    public Response update(
            @PathParam("meddId") String meddId,
            MassutskickInput input) {
        Optional<MassutskickItem> updated = dataService.updateMassutskick(meddId, input);
        if (updated.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated.get()).build();
    }

    @DELETE
    @Path("/{meddId}")
    public Response delete(@PathParam("meddId") String meddId) {
        boolean deleted = dataService.deleteMassutskick(meddId);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/{meddId}/klarmarkera")
    public Response klarmarkera(@PathParam("meddId") String meddId) {
        int result = dataService.klarmarkeraMassutskick(meddId);
        return switch (result) {
            case 0 -> Response.status(Response.Status.NOT_FOUND).build();
            case 2 -> Response.status(Response.Status.CONFLICT).build();
            default -> Response.noContent().build();
        };
    }
}
