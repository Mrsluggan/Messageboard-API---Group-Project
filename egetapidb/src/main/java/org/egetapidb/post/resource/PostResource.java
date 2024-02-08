package org.egetapidb.post.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.egetapidb.post.model.Post;
import org.egetapidb.post.service.PostService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/post")
public class PostResource {

    @Inject
    PostService postService;

    // Hämtar alla posts
    @GET
    @Operation(summary = "Visa alla inlägg", description = "Hämtar och visar alla inlägg som finns sparade i databasen.")
    public Response getAllPosts(@HeaderParam("API-Key") UUID apiKey) {
        List<Post> posts = postService.findAll(apiKey);

        if (posts.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(posts).build();
    }

    @GET
    @Operation(summary = "Visa specifikt inlägg", description = "Hämtar och visar det angivna inlägget")
    @Path("/{id}")
    public Response getPost(@PathParam("id") Long id, @HeaderParam("API-Key") UUID apiKey) {
        Post post = postService.findPost(id, apiKey);
        return Response.ok(post).build();
    }
   

    // Hämta alla posts för user
    @GET
    @Operation(summary = "Visa alla inlägg från en användare", description = "Hämtar och visar alla de inlägg som är skapade av den angivna användaren.")
    @Path("/user/{userId}")
    public Response getPostsByUserId(@PathParam("userId") Long userId, @HeaderParam("API-Key") UUID apiKey) {
        List<Post> posts = postService.findPostbyUser(userId, apiKey);
        return Response.ok(posts).build();
    }

    @POST
    @Operation(summary = "Skapa inlägg", description = "Skapar ett inlägg och sparar det i databasen.")
    @Path("/{userId}")
    public Response createPost(@Valid Post post, @PathParam("userId") Long userId, @HeaderParam("API-Key") UUID apiKey)
            throws URISyntaxException {

        Post createdPost = postService.createPost(post, userId, apiKey);

        URI createdUri = new URI(post.getIdPost().toString());
        return Response.created(createdUri).entity(createdPost).build();

    }

    @DELETE
    @Operation(summary = "Ta bort inlägg", description = "Tar bort angivet inlägg och raderar inlägget från databasen.")
    @Path("/{userId}/post/{postId}")
    public Response deletePost(@PathParam("userId") Long userId, @PathParam("postId") Long postId,
            @HeaderParam("API-Key") UUID apiKey) {
        postService.deletePost(userId, postId, apiKey);
        return Response.noContent().build();
    }

    @PATCH
    @Operation(summary = "Ändra inlägg", description = "Ändrar angivet inlägg från databasen.")
    @Path("/{userId}/post/{postId}")
    public Response changePost(@PathParam("userId") Long userId, @PathParam("postId") Long postId, 
    @RequestBody Post newPost,
    @HeaderParam("API-Key") UUID apiKey) {
        postService.changePost(userId, postId, newPost);
        return Response.noContent().build();
    }


    @GET
    @Operation(summary = "Räkna inlägg", description = "Räknar och visar antalet inlägg som finns sparade i databasen.")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Response countPosts(@HeaderParam("API-Key") UUID apiKey) {
        Long countPosts = postService.countAllPosts(apiKey);
        return Response.ok(countPosts).build();
    }

}
