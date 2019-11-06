package nl.dfbackend.git.api;

import java.security.Principal;

public class User implements Principal {

    private int userId;
    private String email;
    private String password;
    private String authToken;

    public User(int userId, String email, String password){
        this.userId = userId;
        this.email = email;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getName() {
        return email;
    }
}