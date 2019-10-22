package nl.oose.dea.rest;

import nl.oose.dea.domain.services.UserService;
import nl.oose.dea.rest.dto.TokenDTO;
import nl.oose.dea.rest.dto.UserDTO;
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
    void verifySetToken() {
        UserDTO userDTO = new UserDTO();
        sut.login(userDTO);
        Mockito.verify(mockedUserService).setToken(userDTO.getUser());
    }

    @Test
    void verifyGetToken() {
        UserDTO userDTO = new UserDTO();
        sut.login(userDTO);
        Mockito.verify(mockedUserService).getToken(userDTO.getUser());
    }

    //?????????????????????????
    @Test
    void getTokenAsEntity() {
        UserDTO userDTO = new UserDTO();
        var itemsToReturn = new TokenDTO();
        Mockito.when(mockedUserService.getToken(userDTO.getUser())).thenReturn(itemsToReturn);
    }

    @Test
    void loginReturnsUnauthorized() {
        UserDTO userDTO = new UserDTO();
        int statusCode = sut.login(userDTO).getStatus();
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), statusCode);
    }
}