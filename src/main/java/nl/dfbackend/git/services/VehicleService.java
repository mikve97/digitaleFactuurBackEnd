package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import nl.dfbackend.git.models.VehicleModel;
import nl.dfbackend.git.persistences.TripPersistence;
import nl.dfbackend.git.persistences.VehiclePersistence;
import nl.dfbackend.git.util.DbConnector;

/**
 * @author Bram de Jong
 */
public class VehicleService {
    private DBI dbi;
    private VehiclePersistence vehicleDAO;
    private AuthorisationService authorisationService;

    /**
     * @author Bram de Jong
     * @throws SQLException 
     */
    public VehicleService() throws SQLException {
    	DbConnector.getInstance();
        dbi = DbConnector.getDBI();
        this.authorisationService = new AuthorisationService();
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
    public boolean addVehicleByUser(int userId,String licensePlate, String vehicleName, String vehicleType,  String vehicleBody) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            vehicleDAO.createVehicleByUser(licensePlate, userId, vehicleName, vehicleType, vehicleBody);

            vehicleDAO.close();

            return true;
		} else {
			return false;
		}
		
    }

    /**
     * @author Bram de Jong
     * @throws SQLException 
     */
    public boolean alterVehicleByUser(String licensePlate, int userId, String vehicleName, String vehicleType, String fuel, String vehiclebody) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            vehicleDAO.updateVehicleByUser(licensePlate, userId, vehicleName, vehicleType, fuel, vehiclebody);

            vehicleDAO.close();
            
            return true;
		} else {
			return false;
		}
		
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     * @param vehicle_id
     * @return fetchedVehicle
     * @throws SQLException 
     */
    public VehicleModel fetchVehicle(int vehicle_id) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            VehicleModel fetchedVehicle = vehicleDAO.findByVehicleId(vehicle_id);

            vehicleDAO.close();

            return fetchedVehicle;
		} else {
			return null;
		}
		
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     * @param licenseplate
     * @return fetchedVehicle
     * @throws SQLException
     */
    public VehicleModel fetchVehicleByLicensePlate(String licenseplate) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            VehicleModel fetchedVehicle = vehicleDAO.findByVehicleLicenseplate(licenseplate);

            vehicleDAO.close();

            return fetchedVehicle;
		} else {
			return null;
		}
        
    }

    /**
     * @author Bram de Jong
     * @return fetchedVehicles
     * @throws SQLException 
     */
    public List<VehicleModel> fetchAllVehicles() throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            List<VehicleModel> fetchedVehicles = vehicleDAO.findAll();

            vehicleDAO.close();

            return fetchedVehicles;
		} else {
			return null;
		}
		
    }

    /**
     * @author Bram de Jong
     * @param licensePlate
     * @return boolean
     * @throws SQLException 
     */
    public boolean deleteVehicle(String licensePlate) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            vehicleDAO.remove(licensePlate);

            vehicleDAO.close();

            return true;
		} else {
			return false;
		}
		
    }

	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allUniqueLicenseplates
	 * @throws SQLException 
	 */
	public List<String> fetchAllUniqueLicenseplates(int userid) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
    		List<String> fetchedUniqueLicenseplates = vehicleDAO.findAllUniqueLicenseplates(userid);
    		
    		vehicleDAO.close();

    		return fetchedUniqueLicenseplates;
		} else {
			return null;
		}
		
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @param userid
	 * @return allVehiclesRegisteredByUser
	 */
	public List<VehicleModel> fetchAllVehiclesRegisteredByUser(int userid) throws SQLException {
    	if (this.authorisationService.decodeJWToken(this.authorisationService.encodeJWToken("test_user"))) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            List<VehicleModel> allVehiclesRegisteredByUser = vehicleDAO.findRegisteredByUser(userid);

            vehicleDAO.close();

            return allVehiclesRegisteredByUser;
		} else {
			return null;
		}
    }
}