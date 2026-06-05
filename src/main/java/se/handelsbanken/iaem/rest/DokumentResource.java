package se.handelsbanken.iaem.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.handelsbanken.iaem.model.Dokument;
import se.handelsbanken.iaem.service.MockDataService;

import java.util.List;

@Path("/kunder/{kundnr}/dokument")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class DokumentResource {

    @Inject
    MockDataService dataService;

    @GET
    public Response searchDokument(
            @PathParam("kundnr") String kundnr,
            @QueryParam("land") String land,
            @QueryParam("kategori") String kategori,
            @QueryParam("avser") String avser,
            @QueryParam("forbindelse") String forbindelse,
            @QueryParam("datumFran") String datumFran,
            @QueryParam("datumTill") String datumTill) {

        List<Dokument> all = dataService.getDokument(kundnr);

        List<Dokument> filtered = all.stream()
                .filter(d -> forbindelse == null || forbindelse.isBlank()
                        || forbindelse.equalsIgnoreCase(d.forbindelse))
                .filter(d -> datumFran == null || datumFran.isBlank()
                        || d.dokumentdatum.compareTo(datumFran) >= 0)
                .filter(d -> datumTill == null || datumTill.isBlank()
                        || d.dokumentdatum.compareTo(datumTill) <= 0)
                .toList();

        return Response.ok(filtered).build();
    }
}
