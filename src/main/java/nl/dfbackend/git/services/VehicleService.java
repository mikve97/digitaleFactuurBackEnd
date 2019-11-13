package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.ds.PGPoolingDataSource;
import org.skife.jdbi.v2.DBI;

import nl.dfbackend.git.models.VehicleModel;
import nl.dfbackend.git.persistences.VehiclePersistence;
import nl.dfbackend.git.util.DbConnector;

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
    }

    /**
     * @author Bram de Jong
     * @param userId
     * @param totalTrips
     * @param licensePlate
     * @param vehicleName
     * @param vehicleType
     * @return boolean
     * @throws SQLException 
     */
    public boolean addVehicleByUser(int userId, int totalTrips, String licensePlate, String vehicleName, String vehicleType) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.createVehicleByUser(licensePlate, userId, vehicleName, vehicleType, totalTrips);

        source.close();

        return true;
    }

    /**
     * @author Bram de Jong
     * @throws SQLException 
     */
    public boolean alterVehicleByUser(int userId, int totalTrips, String licensePlate, String vehicleName, String vehicleType) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.updateVehicleByUser(licensePlate, userId, vehicleName, vehicleType, totalTrips);

        source.close();
        
        return true;
    }

    /**
     * @author Bram de Jong
     * @param licenseplate
     * @return fetchedVehicle
     * @throws SQLException 
     */
    public VehicleModel fetchVehicle(String licenseplate) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        VehicleModel fetchedVehicle = vehicleDAO.findByLicensePlate(licenseplate);

        source.close();

        return fetchedVehicle;
    }

    /**
     * @author Bram de Jong
     * @return fetchedVehicles
     * @throws SQLException 
     */
    public List<VehicleModel> fetchAllVehicles() throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        List<VehicleModel> fetchedVehicles = vehicleDAO.findAll();

        source.close();

        return fetchedVehicles;
    }

    /**
     * @author Bram de Jong
     * @param licensePlate
     * @return boolean
     * @throws SQLException 
     */
    public boolean deleteVehicle(String licensePlate) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        vehicleDAO.remove(licensePlate);

        source.close();

        return true;
    }

	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allUniqueLicenseplates
	 * @throws SQLException 
	 */
	public List<String> fetchAllUniqueLicenseplates(int userid) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
		List<String> fetchedUniqueLicenseplates = vehicleDAO.findAllUniqueLicenseplates(userid);
		
        source.close();

		return fetchedUniqueLicenseplates;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param userid
	 * @return allVehiclesRegisteredByUser
	 */
	public List<VehicleModel> fetchAllVehiclesRegisteredByUser(int userid) throws SQLException {
    	PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		vehicleDAO = dbi.open(VehiclePersistence.class);
        List<VehicleModel> allVehiclesRegisteredByUser = vehicleDAO.findRegisteredByUser(userid);

        source.close();

        return allVehiclesRegisteredByUser;
    }
}