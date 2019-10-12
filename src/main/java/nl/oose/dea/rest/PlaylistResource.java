package nl.oose.dea.rest;

import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.services.PlaylistService;
import nl.oose.dea.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {

    @Inject
    PlaylistService playlistService;

    @Inject
    TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.deletePlaylist(id, token);
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(Playlist playlist, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlist);
        return Response.status(Response.Status.CREATED).entity(playlist).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(Playlist playlist, @PathParam("id") int id, @QueryParam("token") String token) {
        playlistService.editPlaylist(playlist.getName(), id);
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") int id, @QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(id)).build();
    }

    @Path("/{playlistid}/tracks/{trackid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("playlistid") int playlistid, @PathParam("trackid") int trackid) {
        return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
    }

}
