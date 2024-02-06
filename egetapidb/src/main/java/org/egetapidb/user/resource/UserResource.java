package org.egetapidb.user.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


import org.egetapidb.user.model.User;
import org.egetapidb.user.service.UserService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
    public Response getUsers() {

        List<User> users = userService.findAll();

        if (users.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(users).build();
    }


    @GET
    @Path("/{id}")
    public Response getUserById() {
        return null;
    }
 
    @POST
    public Response createUser(@Valid User user) throws URISyntaxException {

        user = userService.create(user);

        URI createdUri = new URI(user.getId().toString());
        return Response.created(createdUri).entity(user).build();
   
    }
 
    @DELETE
    public Response deleteUser() {
        return null;
    } 
} 