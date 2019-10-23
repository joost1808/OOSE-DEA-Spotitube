package nl.oose.dea.rest.resources;

import nl.oose.dea.domain.dto.PlaylistDTO;
import nl.oose.dea.domain.dto.TrackDTO;
import nl.oose.dea.rest.iPlaylistService;
import nl.oose.dea.rest.iTrackService;
import nl.oose.dea.rest.iUserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistResource {
    private iPlaylistService playlistService;
    private iTrackService trackService;
    private iUserService userService;

    @Inject
    public void setPlaylistService(iPlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @Inject
    public void setTrackService(iTrackService trackService) {
        this.trackService = trackService;
    }

    @Inject
    public void setUserService(iUserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                playlistService.deletePlaylist(playlistid, token);
                return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(PlaylistDTO playlistDTO, @QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                playlistService.addPlaylist(playlistDTO.getName(), token);
                return Response.status(Response.Status.CREATED).entity(playlistService.getAll(token)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
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
            if (userService.checkIfTokenExists(token)) {
                playlistService.editPlaylist(playlistDTO.getName(), playlistid);
                return Response.status(Response.Status.OK).entity(playlistService.getAll(token)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}/tracks")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksInPlaylist(@PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{playlistid}/tracks/{trackid}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeTrackFromPlaylist(@PathParam("playlistid") int playlistid, @PathParam("trackid") int trackid, @QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                trackService.removeTrackFromPlaylist(playlistid, trackid);
                return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("/{id}/tracks")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTrackToPlaylist(TrackDTO trackDTO, @PathParam("id") int playlistid, @QueryParam("token") String token) {
        if (token != null) {
            if (userService.checkIfTokenExists(token)) {
                trackService.addTrackToPlaylist(playlistid, trackDTO.getId(), trackDTO.isOfflineAvailable());
                return Response.status(Response.Status.OK).entity(trackService.getAllInPlaylist(playlistid)).build();
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
