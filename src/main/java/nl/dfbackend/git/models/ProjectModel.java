package nl.dfbackend.git.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class ProjectModel {
    @Length(max = 3)
    private int id;

    @Length(max = 11)
    private List<TripModel> trips;

    @Length(max = 255)
    private String name;

    /**
     * Basic model so we can return our projects to our frond-end
     */
    public ProjectModel() {
        // Jackson deserialization
    }
    public ProjectModel(int id, String name, List<TripModel> tripModel) {
        this.id = id;
        this.name = name;
        this.trips = tripModel;
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
    public List<TripModel> getTrips() {
        return this.trips;
    }

    public void setTrips(TripModel trip) {
        this.trips.add(trip);
    }


}
