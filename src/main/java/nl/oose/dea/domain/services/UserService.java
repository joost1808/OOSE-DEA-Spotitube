package nl.oose.dea.domain.services;

import nl.oose.dea.domain.iUserDAO;
import nl.oose.dea.domain.dto.TokenDTO;
import nl.oose.dea.rest.iUserService;

import javax.inject.Inject;

public class UserService implements iUserService {
    private iUserDAO userDAO;

    @Inject
    public void setUserDAO(iUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public TokenDTO getToken(String user) {
        return userDAO.getToken(user);
    }

    public void setToken(String user) {
        userDAO.setToken(user);
    }

    public boolean userVerify(String user, String password) {
        return userDAO.verifyUser(user, password);
    }
}
