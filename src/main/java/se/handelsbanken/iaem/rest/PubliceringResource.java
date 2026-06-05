package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import se.handelsbanken.iaem.model.*;
import se.handelsbanken.iaem.service.MockDataService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/publicering")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PubliceringResource {

    @Inject
    MockDataService dataService;

    // ---- Installningar ----

    @GET
    @Path("/installningar")
    public Response getInstallningar() {
        List<Informationssamband> list = dataService.getInformationssamband();
        return Response.ok(list).build();
    }

    @POST
    @Path("/installningar")
    public Response createInstallning(InformationssambandInput input) {
        Informationssamband created = dataService.createInformationssamband(input);
        URI location = UriBuilder.fromResource(PubliceringResource.class)
                .path("installningar/{id}")
                .build(created.id);
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/installningar/{id}")
    public Response updateInstallning(
            @PathParam("id") String id,
            InformationssambandInput input) {
        Optional<Informationssamband> updated = dataService.updateInformationssamband(id, input);
        if (updated.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated.get()).build();
    }

    @DELETE
    @Path("/installningar/{id}")
    public Response deleteInstallning(@PathParam("id") String id) {
        boolean deleted = dataService.deleteInformationssamband(id);
        if (!deleted) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }

    // ---- Jobb ----

    @GET
    @Path("/jobb")
    public Response getJobb() {
        List<PubliceringJobb> list = dataService.getPubliceringJobb();
        return Response.ok(list).build();
    }

    @POST
    @Path("/jobb/{jobbId}/godkann")
    public Response godkann(
            @PathParam("jobbId") String jobbId,
            GodkannRequest request) {
        boolean found = dataService.godkannJobb(jobbId, request.godkand);
        if (!found) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.noContent().build();
    }
}
