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
    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserDTO userDTO) {
        if(userService.userVerify(userDTO.getUser(), userDTO.getPassword())) {
            userService.setToken(userDTO.getUser());
            TokenDTO tokenDTO = userService.getToken(userDTO.getUser());
            return Response.ok().entity(tokenDTO).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }
}
