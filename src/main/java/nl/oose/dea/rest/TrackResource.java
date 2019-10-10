package nl.oose.dea.rest;

import nl.oose.dea.domain.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackResource {

    @Inject
    TrackService trackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("forPlaylist") int id, @QueryParam("token") String token) {
        return Response.status(Response.Status.OK).entity(trackService.getAll(id)).build();
    }
}
