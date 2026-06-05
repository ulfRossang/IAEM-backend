package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.Meddelande;
import se.handelsbanken.iaem.model.MeddelandeDetail;
import se.handelsbanken.iaem.model.PagedResult;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.List;
import java.util.Optional;

@Path("/kunder/{kundnr}/meddelanden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MeddelandenResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response listMeddelanden(
            @PathParam("kundnr") String kundnr,
            @QueryParam("land") String land,
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        List<MeddelandeDetail> all = dataService.getMeddelanden(kundnr);

        // Apply date filters
        List<MeddelandeDetail> filtered = all.stream()
                .filter(m -> from == null || from.isBlank() || m.datum.compareTo(from) >= 0)
                .filter(m -> to == null || to.isBlank() || m.datum.compareTo(to + "T23:59:59") <= 0)
                .toList();

        int total = filtered.size();
        int fromIndex = Math.min((page - 1) * size, total);
        int toIndex = Math.min(fromIndex + size, total);
        List<Meddelande> pageItems = filtered.subList(fromIndex, toIndex)
                .stream()
                .map(m -> (Meddelande) m)
                .toList();

        return Response.ok(new PagedResult<>(total, pageItems)).build();
    }

    @GET
    @Path("/{meddId}")
    public Response getMeddelande(
            @PathParam("kundnr") String kundnr,
            @PathParam("meddId") String meddId) {

        Optional<MeddelandeDetail> m = dataService.getMeddelande(kundnr, meddId);
        if (m.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(m.get()).build();
    }
}
