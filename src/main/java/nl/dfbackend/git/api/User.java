package nl.dfbackend.git.api;

import java.security.Principal;

public class User implements Principal {
    private int userId;
    private String email;
    private String Password;

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
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String getName() {
        return email;
    }
}
