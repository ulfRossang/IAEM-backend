package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import se.handelsbanken.iaem.model.Informationsprodukt;
import se.handelsbanken.iaem.model.InformationsproduktInput;
import se.handelsbanken.iaem.service.MockDataService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/informationsprodukter")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class InformationsprodukterResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response listProdukter(@QueryParam("land") String land) {
        List<Informationsprodukt> list = dataService.getInformationsprodukter(land);
        return Response.ok(list).build();
    }

    @POST
    public Response createProdukt(InformationsproduktInput input) {
        Informationsprodukt created = dataService.createInformationsprodukt(input);
        URI location = UriBuilder.fromResource(InformationsprodukterResource.class)
                .path("{id}")
                .build(created.id);
        return Response.created(location).entity(created).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProdukt(
            @PathParam("id") String id,
            InformationsproduktInput input) {
        Optional<Informationsprodukt> updated = dataService.updateInformationsprodukt(id, input);
        if (updated.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(updated.get()).build();
    }
}
