package nl.oose.dea.rest;

import nl.oose.dea.domain.dto.TokenDTO;

public interface iUserService {
    TokenDTO getToken(String user);
    void setToken(String user);
    boolean userVerify(String user, String password);
    boolean checkIfTokenExists(String token);
}
