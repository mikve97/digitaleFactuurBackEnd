package nl.dfbackend.git.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Oussamam Fahchouch
 *
 */
public class TripModel {
	@JsonProperty int id;
    @JsonProperty int projectId;
    @JsonProperty int userId;
    @JsonProperty String licensePlate;
    @JsonProperty String startLocation;
    @JsonProperty String endLocation;
    @JsonProperty double startKilometergauge;
    @JsonProperty double endKilometergauge;
    @JsonProperty float drivenKm;
    
    @JsonCreator
    public TripModel(@JsonProperty("id") int id, @JsonProperty("projectId") int projectId, @JsonProperty("userId") int userId, 
    		@JsonProperty("licensePlate") String licensePlate, @JsonProperty("startLocation") String startLocation, 
    		@JsonProperty("endLocation") String endLocation, @JsonProperty("startKilometergauge") double startKilometergauge, 
    		@JsonProperty("endKilometergauge") double endKilometergauge, @JsonProperty("drivenKm") float drivenKm) {
		this.id = id;
		this.projectId = projectId;
		this.userId = userId;
		this.licensePlate = licensePlate;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.startKilometergauge = startKilometergauge;
		this.endKilometergauge = endKilometergauge;
		this.drivenKm = drivenKm;
	}

	public int getTripId() {
		return id;
	}

	public void setTripId(int id) {
		this.id = id;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public double getStartKilometergauge() {
		return startKilometergauge;
	}

	public void setStartKilometergauge(double startKilometergauge) {
		this.startKilometergauge = startKilometergauge;
	}

	public double getEndKilometergauge() {
		return endKilometergauge;
	}

	public void setEndKilometergauge(double endKilometergauge) {
		this.endKilometergauge = endKilometergauge;
	}

	public float getDrivenKm() {
		return this.drivenKm;
	}

	public void setDrivenKm(float drivenKm) {
		this.drivenKm = drivenKm;
	}
}
