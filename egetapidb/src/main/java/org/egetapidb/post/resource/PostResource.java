package org.egetapidb.post.resource;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.egetapidb.post.model.Post;
import org.egetapidb.post.service.PostService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/post")
public class PostResource {

    @Inject
    PostService postService;

    @GET
    @Operation(summary = "Visa alla inlägg", description = "Hämtar och visar alla inlägg som finns sparade i databasen.")
    @APIResponse(
        responseCode = "200",
        description = "Alla inlägg"
    )
    @APIResponse(
        responseCode = "204",
        description = "Fanns inga inlägg"
    )
    public Response getAllPosts(@HeaderParam("API-Key") UUID apiKey) {
        List<Post> posts = postService.findAll(apiKey);

        if (posts.isEmpty()) {
            return Response.noContent().build();
        }

        return Response.ok(posts).build();
    }

    @GET
    @Operation(summary = "Visa specifikt inlägg", description = "Hämtar och visar det angivna inlägget")
    @APIResponse(
        responseCode = "200",
        description = "Angivna inlägget visas"
    )
    @APIResponse(
        responseCode = "404",
        description = "Angivna inlägget hittades inte"
    )
    @Path("/{id}")
    public Response getPost(@PathParam("id") @Min(1) Long id, @HeaderParam("API-Key") UUID apiKey) {
        try {
            Post post = postService.findPost(id, apiKey);
            return Response.ok(post).build();
        } catch (NotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Användaren med det angivna id:t hittades inte.").build();
        }

    }

    @GET
    @Operation(summary = "Visa alla inlägg från en användare", description = "Hämtar och visar alla de inlägg som är skapade av den angivna användaren.")
    @Path("/user/{userId}")
    @APIResponse(
        responseCode = "200", 
        description = "Inläggen hämtades framgångsrikt"
    )
    @APIResponse(
        responseCode = "404", 
        description = "Användaren hittades inte"
        )
    @APIResponse(
        responseCode = "500", 
        description = "Internt serverfel"
        )
    public Response getPostsByUserId(@PathParam("userId") @Min(1) Long userId, @HeaderParam("API-Key") UUID apiKey) {
        List<Post> posts = postService.findPostbyUser(userId, apiKey);
        return Response.ok(posts).build();
    }

    @POST
    @Operation(summary = "Skapa inlägg", description = "Skapar ett inlägg och sparar det i databasen.")
    @Path("/{userId}")
    @APIResponse(
        responseCode = "201",
        description = "Inlägget skapades framgångsrikt"
    )
    @APIResponse(
        responseCode = "400",
        description = "Ogiltig begäran"
    )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel"
    )
    public Response createPost(@Valid Post post, @PathParam("userId") @Min(1) Long userId, @HeaderParam("API-Key") UUID apiKey)
            throws URISyntaxException {

        Post createdPost = postService.createPost(post, userId, apiKey);

        URI createdUri = new URI(post.getPostId().toString());
        return Response.created(createdUri).entity(createdPost).build();

    }

    @DELETE
    @Operation(summary = "Ta bort inlägg", description = "Tar bort angivet inlägg och raderar inlägget från databasen.")
    @Path("/{userId}/post/{postId}")
    @APIResponse(
        responseCode = "204",
        description = "Inlägget togs bort framgångsrikt"
    )
    @APIResponse(
        responseCode = "404",
        description = "Inlägget hittades inte"
    )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel"
    )
    public Response deletePost(@PathParam("userId") @Min(1) Long userId, @PathParam("postId") @Min(1) Long postId,
            @HeaderParam("API-Key") UUID apiKey) {
        postService.deletePost(userId, postId, apiKey);
        return Response.noContent().build();
    }

    @PATCH
    @Operation(summary = "Ändra inlägg", description = "Ändrar angivet inlägg från databasen.")
    @Path("/{userId}/post/{postId}")
    @APIResponse(
        responseCode = "200",
        description = "Inlägget ändrades framgångsrikt"
    )
    @APIResponse(
        responseCode = "404",
        description = "Inlägget hittades inte"
    )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel"
    )
    public Response changePost(@PathParam("userId") @Min(1) Long userId, @PathParam("postId") @Min(1) Long postId,
            @RequestBody Post newPost,
            @HeaderParam("API-Key") UUID apiKey) {
        postService.changePost(userId, postId, newPost);
        return Response.ok().build();
    }

    @GET
    @Operation(summary = "Räkna inlägg", description = "Räknar och visar antalet inlägg som finns sparade i databasen.")
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    @APIResponse(
        responseCode = "200",
        description = "Antalet inlägg returnerades framgångsrikt"
        )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel"
        )
    public Response countPosts(@HeaderParam("API-Key") UUID apiKey) {
        Long countPosts = postService.countAllPosts(apiKey);
        return Response.ok(countPosts).build();
    }

    @PUT
    @Operation(summary = "Öka likes på inlägg", description = "Tar in postId och uppdaterar den posten likes med 1")
    @Path("{userId}/like/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "200",
        description = "Inlägget gillades framgångsrikt"
        )
    @APIResponse(
        responseCode = "400",
        description = "Användaren har redan gillat inlägget"
    )
    @APIResponse(
        responseCode = "404",
        description = "Inlägget hittades inte"
        )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel")
    public Response likePost(@PathParam("userId") @Min(1) Long userId, @PathParam("postId") @Min(1) Long postId,
            @HeaderParam("API-Key") UUID apiKey) {

        Post post = postService.findPost(postId, apiKey);

        if (post.getWhoLiked().contains(userId)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Använndare har redan gillat post")
                    .build();
        } else {
            postService.updateLike(post, postId, userId, apiKey);
            return Response.ok(post).build();
        }
    }

    @PUT
    @Operation(summary = "Öka dislikes på inlägg", description = "Tar in postId och uppdaterar den posten dislikes med 1")
    @Path("{userId}/dislike/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(
        responseCode = "200",
        description = "Inlägget ogillades framgångsrikt"
        )
    @APIResponse(
        responseCode = "400",
        description = "Användaren har redan ogillat inlägget"
        )
    @APIResponse(
        responseCode = "404",
        description = "Inlägget hittades inte"
        )
    @APIResponse(
        responseCode = "500",
        description = "Internt serverfel")
    public Response dislikePost(@PathParam("userId") @Min(1) Long userId, @PathParam("postId") @Min(1) Long postId,
            @HeaderParam("API-Key") UUID apiKey) {
        Post post = postService.findPost(postId, apiKey);

        if (post.getWhoDisliked().contains(userId)) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Använndare har redan ogillat post")
                    .build();
        }
        postService.updateDislike(post, postId, userId, apiKey);
        return Response.ok(post).build();
    }
}