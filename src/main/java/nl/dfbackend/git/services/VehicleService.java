package nl.dfbackend.git.services;

import nl.dfbackend.git.models.VehicleModel;
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
	 */
	public VehicleModel fetchVehicle(String licenseplate) {
		vehicleDAO = dbi.open(VehiclePersistence.class);
		VehicleModel fetchedVehicle = vehicleDAO.findByLicensePlate(licenseplate);
		vehicleDAO.close();

		return fetchedVehicle;
	}

	/**
	 * @author Bram de Jong
	 */
	public List<VehicleModel> fetchAllVehicles() {
		vehicleDAO = dbi.open(VehiclePersistence.class);
		List<VehicleModel> fetchedVehicles = vehicleDAO.findAll();
		vehicleDAO.close();
		
		return fetchedVehicles;
	}

	public boolean deleteVehicle(String licensePlate) {
		vehicleDAO = dbi.open(VehiclePersistence.class);
		vehicleDAO.remove(licensePlate);
		vehicleDAO.close();
		return true;
	}
}
