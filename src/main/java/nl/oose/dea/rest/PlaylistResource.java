package nl.oose.dea.rest;

import nl.oose.dea.domain.Playlist;
import nl.oose.dea.domain.Track;
import nl.oose.dea.domain.services.PlaylistService;
import nl.oose.dea.domain.services.TrackService;
import nl.oose.dea.rest.dto.PlaylistDTO;
import nl.oose.dea.rest.dto.TrackDTO;

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
    public Response deletePlaylist(@PathParam("id") int playlistid, @QueryParam("token") String token) {
        playlistService.deletePlaylist(playlistid, token);
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        playlistService.addPlaylist(playlistDTO.getId(), token);
        return Response.status(Response.Status.CREATED).entity(playlistDTO).build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(Playlist playlist, @PathParam("id") int playlistid, @QueryParam("token") String token) {
        playlistService.editPlaylist(playlist.getName(), playlistid);
        return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") int playlistid, @QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
    }

    @Path("/{playlistid}/tracks/{trackid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistid") int playlistid, @PathParam("trackid") int trackid, @QueryParam("token") String token) {
        trackService.removeTrackFromPlaylist(playlistid, trackid);
        return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistid, @QueryParam("token") String token) {
        trackService.addTrackToPlaylist(playlistid, trackDTO.getId(), trackDTO.isOfflineAvailable());
        return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
    }
}
