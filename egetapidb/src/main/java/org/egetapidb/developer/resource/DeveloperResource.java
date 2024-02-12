package org.egetapidb.developer.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.egetapidb.developer.model.Developer;
import org.egetapidb.developer.service.DeveloperService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/api/dev")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeveloperResource {

    @Inject
    DeveloperService developerService;

    @POST
    @Operation(summary = "Skapa developer", description = "Skapar en ny developer med en api-nyckel och sparar den i databasen.")
    public Response createDeveloper(@Valid Developer developer) throws URISyntaxException {

        developer = developerService.createDev(developer);

        URI createdUri = new URI(developer.getDevId().toString());
        return Response.created(createdUri).entity(developer).build();

    }

    @GET
    @Operation(summary = "Visa alla användare", description = "Hämtar och visar alla användare som finns sparade i databasen.")
    public Response getDevs() {

        List<Developer> devs = developerService.findAll();

        if (devs.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(devs).build();
    }
}