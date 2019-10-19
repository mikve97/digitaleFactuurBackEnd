package nl.dfbackend.git.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

public class Project {
    @Length(max = 3)
    private int id;

    @Length(max = 11)
    private int trips;

    @Length(max = 255)
    private String name;

    public Project() {
        // Jackson deserialization
    }
    public Project(int id, String name, int trips ) {
        this.id = id;

        this.trips = trips;

        this.name = name;
    }

    @JsonProperty
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getName() {
        return this.name;
    }

    public void setId(String name) {
        this.name = name;
    }

    @JsonProperty
    public int getTrips() {
        return this.trips;
    }

    public void setTrips(int trips) {
        this.trips = trips;
    }


}
