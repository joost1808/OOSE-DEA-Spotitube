package nl.oose.dea.rest;

import nl.oose.dea.domain.services.UserService;
import nl.oose.dea.rest.dto.TokenDTO;
import nl.oose.dea.rest.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginResource {

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        TokenDTO tokenDTO = userService.getToken(userDTO.getUser());
        return Response.ok().entity(tokenDTO).build();
    }
}
