package nl.oose.dea.rest;

import nl.oose.dea.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {
private TrackService trackService;

    @Inject
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksNotInPlaylist(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        if (token != null) {
            return Response.status(Response.Status.OK).entity(trackService.getAllNotInPlaylist(id)).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
