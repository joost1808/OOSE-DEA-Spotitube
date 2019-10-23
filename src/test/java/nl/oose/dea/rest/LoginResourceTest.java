package nl.oose.dea.rest;

import nl.oose.dea.domain.services.UserService;
import nl.oose.dea.rest.dto.TokenDTO;
import nl.oose.dea.rest.dto.UserDTO;
import nl.oose.dea.rest.resources.LoginResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;

class LoginResourceTest {
    private LoginResource sut;
    private UserService mockedUserService;


    @BeforeEach
    void setUp() {
        sut = new LoginResource();
        this.mockedUserService = Mockito.mock(UserService.class);
        this.sut.setUserService(mockedUserService);
    }

    @Test
    void verifyLogin() {
        UserDTO userDTO = new UserDTO();
        sut.login(userDTO);
        Mockito.verify(mockedUserService).userVerify(userDTO.getUser(), userDTO.getPassword());
    }

    @Test
    void loginReturnsOk() {
        UserDTO userDTO = new UserDTO("user", "password");
        TokenDTO tokenDTO = new TokenDTO("user", "test");
        Mockito.when(mockedUserService.userVerify(userDTO.getUser(), userDTO.getPassword())).thenReturn(true);
        Mockito.when(mockedUserService.getToken(userDTO.getUser())).thenReturn(tokenDTO);
        var input = sut.login(userDTO);
        assertEquals(input.getEntity(), tokenDTO);
    }

    @Test
    void loginReturnsUnauthorized() {
        UserDTO userDTO = new UserDTO();
        int statusCode = sut.login(userDTO).getStatus();
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
    }
}