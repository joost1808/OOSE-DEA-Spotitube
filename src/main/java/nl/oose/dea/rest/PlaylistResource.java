package nl.oose.dea.rest;

import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.services.PlaylistService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    @Inject
    PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id) {
        playlistService.deletePlaylist(id);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(Playlist playlist) {
        playlistService.addPlaylist(playlist);
        return Response.status(Response.Status.CREATED).entity(playlist).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(@PathParam("id") int id) {
        playlistService.editPlaylist(id);
        return Response.status(Response.Status.OK).build();
    }

}
