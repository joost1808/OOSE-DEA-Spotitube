package nl.oose.dea.domain.services;

import nl.oose.dea.data.UserDAO;
import nl.oose.dea.rest.dto.TokenDTO;

public class UserService {
    private UserDAO userDAO = new UserDAO();

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
