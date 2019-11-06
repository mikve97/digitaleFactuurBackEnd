package nl.dfbackend.git.models;

import java.util.Objects;

/**
 * @author Oussamam Fahchouch
 *
 */
public class TripModel {
    private int id;
    private int projectId;
    private int userId;
    private String licensePlate;
    private String startLocation;
    private String endLocation;
    private double startKilometergauge;
    private double endKilometergauge;
    
    public TripModel(int id, int projectId, int userId, String licensePlate, String startLocation, String endLocation,
    		double startKilometergauge, double endKilometergauge) {
		this.id = id;
		this.projectId = projectId;
		this.userId = userId;
		this.licensePlate = licensePlate;
		this.startLocation = startLocation;
		this.endLocation = endLocation;
		this.startKilometergauge = startKilometergauge;
		this.endKilometergauge = endKilometergauge;
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
	
    //equals, hashCode, getters and setters for all fields.
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 59 * hash + Objects.hashCode(this.projectId);
        hash = 59 * hash + Objects.hashCode(this.userId);
        hash = 59 * hash + Objects.hashCode(this.licensePlate);
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
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.projectId, other.projectId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.licensePlate, other.licensePlate)) {
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
    			"id=" + id +
    			", projectId=" + projectId +
    			", userId=" + userId +
    			", licensePlate= '" + licensePlate + '\'' +
    			", startLocation= '" + startLocation + '\'' +
    			", endLocation= '" + endLocation + '\'' +
    			", startKilometergauge=" + startKilometergauge +
    			", endKilometergauge=" + endKilometergauge +
    			'}';
    }
}