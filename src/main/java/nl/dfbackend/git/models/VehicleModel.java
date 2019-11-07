package nl.dfbackend.git.models;

import java.util.Objects;

/**
 * @author Bram de Jong
 *
 */
public class VehicleModel {
    private int userId;
	private int totalTrips;
    private String licensePlate;
    private String vehicleName;
    private String vehicleType;

    public VehicleModel(int userId, int totalTrips, String licensePlate, String vehicleName, String vehicleType) {

		this.userId = userId;
		this.licensePlate = licensePlate;
		this.vehicleName = vehicleName;
		this.vehicleType = vehicleType;
		this.totalTrips = totalTrips;
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

	public int getTotalTrips() {
    	return totalTrips;
	}

	public void setTotalTrips(int totalTrips) {
    	this.totalTrips++;
	}

	public String getVehicleName() { return vehicleName; }

	public void setVehicleName(String vehicleName) { this.vehicleName = vehicleName; }

	public String getVehicleType() { return vehicleType; }

	public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }


	
    //equals, hashCode, getters and setters for all fields.
    @Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + Objects.hashCode(this.userId);
		hash = 59 * hash + Objects.hashCode(this.licensePlate);
		hash = 59 * hash + Objects.hashCode(this.vehicleName);
		hash = 59 * hash + Objects.hashCode(this.vehicleType);
		hash = 59 * hash + Objects.hashCode(this.totalTrips);
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
        final VehicleModel other = (VehicleModel) obj;
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.licensePlate, other.licensePlate)) {
            return false;
        }
        if (!Objects.equals(this.vehicleName, other.vehicleName)) {
            return false;
        }
        if (!Objects.equals(this.vehicleType, other.vehicleType)) {
            return false;
        }
        if (!Objects.equals(this.totalTrips, other.totalTrips)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
    	return "Vehicle{" +
    			", userId=" + userId +
    			", licensePlate= '" + licensePlate + '\'' +
    			", vehicleName= '" + vehicleName + '\'' +
    			", vehicleType= '" + vehicleType + '\'' +
    			", totalTrips=" + totalTrips +
    			'}';
    }
}