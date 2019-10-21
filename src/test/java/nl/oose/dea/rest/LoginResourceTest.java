package nl.oose.dea.rest;

import nl.oose.dea.domain.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

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
        UUID token = UUID.randomUUID();
    }
}