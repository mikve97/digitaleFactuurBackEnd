package nl.dfbackend.git.models;

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
}