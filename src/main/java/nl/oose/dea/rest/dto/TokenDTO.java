package nl.oose.dea.rest.dto;

import java.util.Objects;

public class TokenDTO {
    private String user;
    private String token;

    public TokenDTO() {

    }

    public TokenDTO(String user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDTO tokenDTO = (TokenDTO) o;
        return Objects.equals(user, tokenDTO.user) &&
                Objects.equals(token, tokenDTO.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, token);
    }
}
