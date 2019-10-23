package nl.oose.dea.data;

import nl.oose.dea.rest.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class UserDAOTest {
    @InjectMocks
    private UserDAO sut;
    private TokenDTO tokenDTO;

    @Mock
    private Connection connection;

    @Mock
    private ConnectionFactory mockedConnectionFactory;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet rs;

    @BeforeEach
    void setUp() throws SQLException, ClassNotFoundException {
        this.sut.setConnectionFactory(mockedConnectionFactory);
        when(connection.prepareStatement(any(String.class))).thenReturn(statement);
        when(mockedConnectionFactory.getConnection()).thenReturn(connection);

        tokenDTO = new TokenDTO("1234-1234-1234", "test");
        when(rs.next()).thenReturn(true, false);
        when(rs.getString(1)).thenReturn(tokenDTO.getUser());
        when(rs.getString(2)).thenReturn(tokenDTO.getToken());
        when(statement.executeQuery()).thenReturn(rs);
    }

    @Test
    void getToken() {
        var checkToken = sut.getToken("test").getToken();
        assertEquals(checkToken, "test");
    }
}