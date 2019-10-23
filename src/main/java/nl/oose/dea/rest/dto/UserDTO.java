package nl.oose.dea.rest.dto;

public class UserDTO {
    private String user;
    private String password;

    public UserDTO() {

    }

    public UserDTO(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
