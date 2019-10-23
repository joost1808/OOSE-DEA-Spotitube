package nl.oose.dea.data;

import nl.oose.dea.rest.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOIntegrationTest {
    UserDAO userDAO;
    ConnectionFactory connectionFactory;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
        connectionFactory = new ConnectionFactory();
        userDAO.setConnectionFactory(connectionFactory);
    }

    @Test
    void getTokenFromValidUser() {
        TokenDTO tokenDTO = userDAO.getToken("test");
        assertEquals("testName", tokenDTO.getUser());
    }

    @Test
    void getTokenFromInvalidUser() {
        TokenDTO tokenDTO = userDAO.getToken("invalid");
        assertNull(tokenDTO);
    }

    @Test
    void getTokenFromNull() {
        TokenDTO tokenDTO = userDAO.getToken(null);
        assertNull(tokenDTO);
    }

    @Test
    @Disabled
    void setTokenForValidUser() {
        TokenDTO oldTokenDTO = userDAO.getToken("test");
        userDAO.setToken("test");
        TokenDTO newTokenDTO = userDAO.getToken("test");
        assertNotEquals(oldTokenDTO, newTokenDTO);
    }

    @Test
    void setTokenForInvalidUser() {
        TokenDTO oldTokenDTO = userDAO.getToken("test");
        userDAO.setToken("invalid");
        TokenDTO newTokenDTO = userDAO.getToken("test");
        assertEquals(oldTokenDTO, newTokenDTO);
    }

    @Test
    void setTokenForNull() {
        TokenDTO oldTokenDTO = userDAO.getToken("test");
        userDAO.setToken(null);
        TokenDTO newTokenDTO = userDAO.getToken("test");
        assertEquals(oldTokenDTO, newTokenDTO);
    }

    @Test
    void verifyUserReturnsTrue() {
        assertTrue(userDAO.verifyUser("test", "password"));
    }

    @Test
    void verifyUserReturnsFalse() {
        assertFalse(userDAO.verifyUser("test", "notthepassword"));
    }
}
