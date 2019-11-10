package nl.dfbackend.git.services;

import nl.dfbackend.git.models.VehicleModel;
import nl.dfbackend.git.persistences.TripPersistence;
import nl.dfbackend.git.persistences.VehiclePersistence;
import nl.dfbackend.git.util.DbConnector;
import org.skife.jdbi.v2.DBI;

import java.util.List;

/**
 * @author Bram de Jong
 */
public class VehicleService {
    private DBI dbi;
    private VehiclePersistence vehicleDAO;

    /**
     * @author Bram de Jong
     */
    public VehicleService() {
        DbConnector.getInstance();
        this.dbi = DbConnector.getDBI();
    }

    /**
     * @author Bram de Jong
     * @param userId
     * @param totalTrips
     * @param licensePlate
     * @param vehicleName
     * @param vehicleType
     * @return boolean
     */
    public boolean addVehicleByUser(int userId, int totalTrips, String licensePlate, String vehicleName, String vehicleType) {
        System.out.println(userId);
        vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.createVehicleByUser(licensePlate, userId, vehicleName, vehicleType, totalTrips);
        vehicleDAO.close();

        return true;
    }

    /**
     * @author Bram de Jong
     */
    public boolean alterVehicleByUser(int userId, int totalTrips, String licensePlate, String vehicleName, String vehicleType) {

        vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.updateVehicleByUser(licensePlate, userId, vehicleName, vehicleType, totalTrips);
        vehicleDAO.close();

        return true;
    }

    /**
     * @author Bram de Jong
     * @param licenseplate
     * @return fetchedVehicle
     */
    public VehicleModel fetchVehicle(String licenseplate) {
        vehicleDAO = dbi.open(VehiclePersistence.class);
        VehicleModel fetchedVehicle = vehicleDAO.findByLicensePlate(licenseplate);
        vehicleDAO.close();

        return fetchedVehicle;
    }

    /**
     * @author Bram de Jong
     * @return fetchedVehicles
     */
    public List<VehicleModel> fetchAllVehicles() {
        vehicleDAO = dbi.open(VehiclePersistence.class);
        List<VehicleModel> fetchedVehicles = vehicleDAO.findAll();
        vehicleDAO.close();

        return fetchedVehicles;
    }

    /**
     * @author Bram de Jong
     * @param licensePlate
     * @return boolean
     */
    public boolean deleteVehicle(String licensePlate) {
        vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.remove(licensePlate);
        vehicleDAO.close();
        return true;
    }

	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allUniqueLicenseplates
	 */
	public List<String> fetchAllUniqueLicenseplates() {
		vehicleDAO = dbi.open(VehiclePersistence.class);
		List<String> fetchedUniqueLicenseplates = vehicleDAO.findAllUniqueLicenseplates();
		vehicleDAO.close();
		
		return fetchedUniqueLicenseplates;
	}
}