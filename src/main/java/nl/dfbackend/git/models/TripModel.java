package nl.dfbackend.git.models;

import java.util.Objects;

/**
 * @author Oussamam Fahchouch
 *
 */
public class TripModel {
    private int tripId;
    private int projectId;
    private int userId;
    private String licenseplate;
    private String startLocation;
    private String endLocation;
    private double startKilometergauge;
    private double endKilometergauge;
    
    public TripModel(int id, int projectId, int userId, String licensePlate, String startLocation, String endLocation, double startKilometergauge, double endKilometergauge) {
		this.tripId = id;
		this.projectId = projectId;
		this.userId = userId;
		this.licenseplate = licensePlate;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.startKilometergauge = startKilometergauge;
		this.endKilometergauge = endKilometergauge;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int id) {
		this.tripId = id;
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

	public String getLicenseplate() {
		return licenseplate;
	}

	public void setLicenseplate(String licensePlate) {
		this.licenseplate = licensePlate;
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
	
    //equals, hashCode, getters and setters for all fields.
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.tripId ^ (this.tripId >>> 32));
        hash = 59 * hash + Objects.hashCode(this.projectId);
        hash = 59 * hash + Objects.hashCode(this.userId);
        hash = 59 * hash + Objects.hashCode(this.licenseplate);
        hash = 59 * hash + Objects.hashCode(this.startLocation);
        hash = 59 * hash + Objects.hashCode(this.endLocation);
        hash = 59 * hash + Objects.hashCode(this.startKilometergauge);
        hash = 59 * hash + Objects.hashCode(this.endKilometergauge);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TripModel other = (TripModel) obj;
        if (this.tripId != other.tripId) {
            return false;
        }
        if (!Objects.equals(this.projectId, other.projectId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.licenseplate, other.licenseplate)) {
            return false;
        }
        if (!Objects.equals(this.startLocation, other.startLocation)) {
            return false;
        }
        if (!Objects.equals(this.endLocation, other.endLocation)) {
            return false;
        }
        if (!Objects.equals(this.startKilometergauge, other.startKilometergauge)) {
            return false;
        }
        if (!Objects.equals(this.endKilometergauge, other.endKilometergauge)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
    	return "Trip{" +
    			"id=" + tripId +
    			", projectId=" + projectId +
    			", userId=" + userId +
    			", licensePlate= '" + licenseplate + '\'' +
    			", startLocation= '" + startLocation + '\'' +
    			", endLocation= '" + endLocation + '\'' +
    			", startKilometergauge=" + startKilometergauge +
    			", endKilometergauge=" + endKilometergauge +
    			'}';
    }
}