package nl.dfbackend.git.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class Credential {
    @JsonProperty
    @NotNull
    private String username;

    @JsonProperty
    @NotNull
    private String password;

    @JsonCreator
    public Credential(@JsonProperty("username") String username,
                       @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
