package nl.oose.dea.domain;

import nl.oose.dea.domain.dto.TokenDTO;

public interface iUserDAO {
    TokenDTO getToken(String user);
    void setToken(String user);
    boolean verifyUser(String user, String password);
}
