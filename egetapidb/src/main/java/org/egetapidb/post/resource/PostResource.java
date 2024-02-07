package org.egetapidb.post.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.egetapidb.post.model.Post;
import org.egetapidb.post.service.PostService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
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
    public Response getAllPosts() {

        List<Post> posts = postService.findAll();
        if (posts.isEmpty()) {
            return Response.noContent().build();
        } else {
            return Response.ok(posts).build();

        }
    }

    @GET
    @Operation(summary = "Visa specifikt inlägg", description = "Hämtar och visar det angivna inlägget")
    @Path("/{id}")
    public Response getPost(@PathParam("id") Long id) {
        Post post = postService.findPost(id);
        return Response.ok(post).build();
    }

    // Hämta alla posts för user
    @GET
    @Path("/{userId}")
    public Response getPostsByUserId(@PathParam("userId") Long userId) {
        List<Post> posts = postService.findPostbyUser(userId);
        return Response.ok(posts).build();
    }


    @POST
    @Operation(summary = "Skapa inlägg", description = "Skapar ett inlägg och sparar det i databasen.")
    @Path("/{userId}")
    public Response createPost(@Valid Post post, @PathParam("userId") Long userId) throws URISyntaxException {

        Post createdPost = postService.createPost(post, userId);

        URI createdUri = new URI(post.getIdPost().toString());
        return Response.created(createdUri).entity(createdPost).build();

    }

    @DELETE
    @Operation(summary = "Ta bort inlägg", description = "Tar bort angivet inlägg och raderar inlägget från databasen.")
    @Path("/{userId}/post/{postId}")
    public Response deletePost(@PathParam("userId") Long userId, @PathParam("postId") Long postId) {
        postService.deletePost(userId, postId);
        return Response.noContent().build();
    }

    @GET
    @Operation(summary = "Räkna inlägg", description = "Räknar och visar antalet inlägg som finns sparade i databasen.")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Response countPosts() {
        Long countPosts = postService.countAllPosts();
        return Response.ok(countPosts).build();
    }

}
