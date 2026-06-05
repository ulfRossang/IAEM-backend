package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.UtskickInstallning;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.List;

@Path("/kunder/{kundnr}/utskick-installningar")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UtskickInstallningarResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response getInstallningar(@PathParam("kundnr") String kundnr) {
        List<UtskickInstallning> settings = dataService.getUtskickInstallningar(kundnr);
        return Response.ok(settings).build();
    }

    @PUT
    public Response saveInstallningar(
            @PathParam("kundnr") String kundnr,
            List<UtskickInstallning> settings) {
        dataService.saveUtskickInstallningar(kundnr, settings);
        return Response.noContent().build();
    }
}
