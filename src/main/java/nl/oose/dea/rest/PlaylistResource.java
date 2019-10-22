package nl.oose.dea.rest;

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
    private PlaylistService playlistService;
    private TrackService trackService;

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if (token != null) {
            return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
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
        if (token != null) {
            playlistService.addPlaylist(playlistDTO.getName(), token);
            return Response.status(Response.Status.CREATED).entity(playlistService.getAll(token)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editPlaylist(PlaylistDTO playlistDTO, @PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            playlistService.editPlaylist(playlistDTO.getName(), playlistid);
            return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    //Kan deze in trackResource? reden: url werkte niet of functie werd niet aangeroepen
    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    //Kan deze in trackResource? reden: url werkte niet of functie werd niet aangeroepen
    @Path("/{playlistid}/tracks/{trackid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistid") int playlistid, @PathParam("trackid") int trackid, @QueryParam("token") String token) {
        if (token != null) {
            trackService.removeTrackFromPlaylist(playlistid, trackid);
            return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    //Kan deze in trackResource? reden: url werkte niet of functie werd niet aangeroepen
    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            trackService.addTrackToPlaylist(playlistid, trackDTO.getId(), trackDTO.isOfflineAvailable());
            return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
