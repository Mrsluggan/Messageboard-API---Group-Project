package org.egetapidb.user.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import org.egetapidb.user.model.User;
import org.egetapidb.user.service.UserService;

import jakarta.inject.Inject;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;

@Path("/api/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    @Operation(summary = "Visa alla användare", description = "Hämtar och visar alla användare som finns sparade i databasen.")
    public Response getUsers(@HeaderParam("API-Key") UUID apiKey) {

        List<User> users = userService.findAll(apiKey);

        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users).build();
    }

    @GET
    @Operation(summary = "Visa specifik användare", description = "Hämtar och visar användaren med det angivna id:t.")
    @Path("/{id}")
    public Response getUserById(@PathParam("id") @Min(1) Long id, @HeaderParam("API-Key") UUID apiKey) {
        User user = userService.findUser(id, apiKey);
        return Response.ok(user).build();
    }

    @POST
    @Operation(summary = "Skapa användare", description = "Skapar en ny användare och sparar den i databasen.")
    public Response createUser(@Valid User user, @HeaderParam("API-Key") UUID apiKey) throws URISyntaxException {

        user = userService.create(user, apiKey);

        URI createdUri = new URI(user.getId().toString());
        return Response.created(createdUri).entity(user).build();

    }

    @DELETE
    @Operation(summary = "Ta bort användare", description = "Tar bort angiven användare och raderar den från databasen.")
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") @Min(1) Long id, @HeaderParam("API-Key") UUID apiKey) {
        userService.deleteUser(id, apiKey);
        return Response.noContent().build();
    }

    @PATCH
    @Operation(summary = "Ändra username på användare", description = "Ändrar till angivet username från user entitet i databasen.")
    @Path("/{userId}")
    public Response changeUser(@PathParam("userId") @Min(1) Long userId,
            @RequestBody String newUser,
            @HeaderParam("API-Key") UUID apiKey) {
        userService.changeUser(userId, newUser, apiKey);
        return Response.ok().build();
    }

    @GET
    @Operation(summary = "Räkna användare", description = "Räknar och visar antalet användare som finns sparade i databasen.")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Response countUsers(@HeaderParam("API-Key") UUID apiKey) {
        Long countPosts = userService.countAllUsers(apiKey);
        return Response.ok(countPosts).build();
    }
}