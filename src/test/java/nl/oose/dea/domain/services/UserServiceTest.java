package nl.oose.dea.domain.services;

import nl.oose.dea.data.dao.UserDAO;
import nl.oose.dea.domain.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService sut;
    private UserDAO mockedUserDAO;

    @BeforeEach
    void setUp() {
        sut = new UserService();
        this.mockedUserDAO = Mockito.mock(UserDAO.class);
        this.sut.setUserDAO(mockedUserDAO);
    }

    @Test
    void verifyGetToken() {
        sut.getToken("test");
        Mockito.verify(mockedUserDAO).getToken("test");
    }

    @Test
    void getTokenAsEntity() {
        var itemToReturn = new TokenDTO();
        Mockito.when(mockedUserDAO.getToken("test")).thenReturn(itemToReturn);
        var input = sut.getToken("test");
        assertEquals(input, itemToReturn);
    }

    @Test
    void verifySetToken() {
        sut.setToken("test");
        Mockito.verify(mockedUserDAO).setToken("test");
    }

    @Test
    void verifyUserVerify() {
        sut.userVerify("test", "password");
        Mockito.verify(mockedUserDAO).verifyUser("test", "password");
    }

    @Test
    void verifyCheckIfTokenExists() {
        sut.checkIfTokenExists("1234-1234-1234");
        Mockito.verify(mockedUserDAO).checkIfTokenExists("1234-1234-1234");
    }
}