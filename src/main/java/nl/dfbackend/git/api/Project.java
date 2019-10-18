package nl.dfbackend.git.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Project {
    private long id;

    @Length(max = 12)
    private String content;

    public Project() {
        // Jackson deserialization
    }

    public Project(long id, String content) {
        this.id = id;
        this.content = content;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
