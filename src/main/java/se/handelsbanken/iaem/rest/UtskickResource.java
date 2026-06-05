package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.PagedResult;
import se.handelsbanken.iaem.model.Utskick;
import se.handelsbanken.iaem.model.UtskickDetail;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.List;
import java.util.Optional;

@Path("/kunder/{kundnr}/utskick")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UtskickResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response listUtskick(
            @PathParam("kundnr") String kundnr,
            @QueryParam("land") String land,
            @QueryParam("kategori") String kategori,
            @QueryParam("from") String from,
            @QueryParam("to") String to,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        List<UtskickDetail> all = dataService.getUtskick(kundnr);

        List<UtskickDetail> filtered = all.stream()
                .filter(u -> kategori == null || kategori.isBlank() || kategori.equalsIgnoreCase(u.kategori))
                .filter(u -> from == null || from.isBlank() || u.datum.compareTo(from) >= 0)
                .filter(u -> to == null || to.isBlank() || u.datum.compareTo(to + "T23:59:59") <= 0)
                .toList();

        int total = filtered.size();
        int fromIndex = Math.min((page - 1) * size, total);
        int toIndex = Math.min(fromIndex + size, total);
        List<Utskick> pageItems = filtered.subList(fromIndex, toIndex)
                .stream()
                .map(u -> (Utskick) u)
                .toList();

        return Response.ok(new PagedResult<>(total, pageItems)).build();
    }

    @GET
    @Path("/{utskickId}")
    public Response getUtskick(
            @PathParam("kundnr") String kundnr,
            @PathParam("utskickId") String utskickId) {

        Optional<UtskickDetail> u = dataService.getUtskickById(kundnr, utskickId);
        if (u.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(u.get()).build();
    }
}
