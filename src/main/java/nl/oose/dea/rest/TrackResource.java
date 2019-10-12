package nl.oose.dea.rest;

import nl.oose.dea.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    @Inject
    TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracksNotInPlaylist(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(trackService.getAllNotInPlaylist(id)).build();
    }
}
