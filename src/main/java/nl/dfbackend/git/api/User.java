package nl.dfbackend.git.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.security.Principal;

/**
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

public class User implements Principal {

    @JsonProperty
    @NotNull
    private int userId;
    @JsonProperty
    @NotNull
    private String username;
    @JsonProperty
    @NotNull
    private String password;
    @JsonProperty
    @NotNull
    private String authToken;

    @JsonCreator
    public User(@JsonProperty("userId") Integer userId,
                @JsonProperty("username") String username,
                @JsonProperty("password") String password){

        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        password = password;
    }

    public String getAuthToken() {
        return authToken;
    }

    @JsonIgnore
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String getName() {
        return username;
    }
}