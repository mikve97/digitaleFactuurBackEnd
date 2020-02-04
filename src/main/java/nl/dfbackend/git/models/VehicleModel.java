package nl.dfbackend.git.models;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Bram de Jong
 *
 */
public class VehicleModel {
	@JsonProperty int vehicle_id;
    @JsonProperty int userId;
    @JsonProperty String licensePlate;
    @JsonProperty String vehicleName;
    @JsonProperty String vehicleType;
    @JsonProperty String vehicleBody;
    
    @JsonCreator
    public VehicleModel(@JsonProperty("vehicle_id") int vehicle_id, @JsonProperty("userId") int userId, 
    		@JsonProperty("licensePlate") String licensePlate, @JsonProperty("vehicleName") String vehicleName, 
    		@JsonProperty("vehicleType") String vehicleType, @JsonProperty("vehicleBody") String vehicleBody) {
        this.vehicle_id = vehicle_id;
        this.userId = userId;
        this.licensePlate = licensePlate;
        this.vehicleName = vehicleName;
        this.vehicleType = vehicleType;
        this.vehicleBody = vehicleBody;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
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
    
    public String getVehicleBody() {
		return vehicleBody;
	}

	public void setVehicleBody(String vehicleBody) {
		this.vehicleBody = vehicleBody;
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
        return true;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                ", userId=" + userId +
                ", licensePlate= '" + licensePlate + '\'' +
                ", vehicleName= '" + vehicleName + '\'' +
                ", vehicleType= '" + vehicleType + '}';
    }
}