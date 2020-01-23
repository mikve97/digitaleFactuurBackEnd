package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.List;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.auth.AuthenticationException;
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
    private AuthenticationService authenticationService;

    /**
     * @author Bram de Jong
     * @throws SQLException 
     */
    public VehicleService() throws SQLException {
    	DbConnector.getInstance();
        dbi = DbConnector.getDBI();
        this.authenticationService = new AuthenticationService();
    }

    /**
     * @author Bram de Jong
     * @param userId
     * @param licensePlate
     * @param vehicleName
     * @param vehicleType
     * @param tokenHeaderParam 
     * @return boolean
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public boolean addVehicleByUser(int userId,String licensePlate, String vehicleName, String vehicleType,  String vehicleBody, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            vehicleDAO.createVehicleByUser(licensePlate, userId, vehicleName, vehicleType, vehicleBody);

            vehicleDAO.close();

            return true;
		} else {
			return false;
		}
		
    }

//    /**
//     * @author Bram de Jong
//     * @throws SQLException 
//     */
//    public boolean alterVehicleByUser(String licensePlate, int userId, String vehicleName, String vehicleType, String fuel, String vehiclebody) throws SQLException {
//    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
//    		vehicleDAO = dbi.open(VehiclePersistence.class);
//            vehicleDAO.updateVehicleByUser(licensePlate, userId, vehicleName, vehicleType, fuel, vehiclebody);
//
//            vehicleDAO.close();
//            
//            return true;
//		} else {
//			return false;
//		}
//		
//    }

    /**
     * @author Ali Rezaa Ghariebiyan
     * @param vehicle_id
     * @param tokenHeaderParam 
     * @return fetchedVehicle
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public VehicleModel fetchVehicle(int vehicle_id, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
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
     * @param tokenHeaderParam 
     * @return fetchedVehicle
     * @throws SQLException
     * @throws AuthenticationException 
     */
    public VehicleModel fetchVehicleByLicensePlate(String licenseplate, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            VehicleModel fetchedVehicle = vehicleDAO.findByVehicleLicenseplate(licenseplate);

            vehicleDAO.close();

            return fetchedVehicle;
		} else {
			return null;
		}
        
    }
    
	/**
	 * @author Fifi Halley
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public void onDeleteVehicle(int[] vehiclesToDelete, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			vehicleDAO = dbi.open(VehiclePersistence.class);
			
			for(int id : vehiclesToDelete) {
				vehicleDAO.removeById(id);
			}
			
			vehicleDAO.close();
		}
	}

    /**
     * @author Bram de Jong
     * @param tokenHeaderParam 
     * @return fetchedVehicles
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public List<VehicleModel> fetchAllVehicles(String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
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
     * @param tokenHeaderParam 
     * @return boolean
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public boolean deleteVehicle(String licensePlate, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            vehicleDAO.remove(licensePlate);

            vehicleDAO.close();

            return true;
		} else {
			return false;
		}
		
    }

	public boolean deleteVehicleById(int vehicleID, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			vehicleDAO = dbi.open(VehiclePersistence.class);
			vehicleDAO.removeById(vehicleID);

			vehicleDAO.close();

			return true;
		} else {
			return false;
		}

	}

	/**
	 * @author Oussama Fahchouch
	 * @param tokenHeaderParam 
	 * @return List<String> allUniqueLicenseplates
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public List<String> fetchAllUniqueLicenseplates(int userid, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
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
	 * @param tokenHeaderParam 
	 * @return allVehiclesRegisteredByUser
	 * @throws AuthenticationException 
	 */
	public List<VehicleModel> fetchAllVehiclesRegisteredByUser(int userid, String tokenHeaderParam) throws SQLException, AuthenticationException {
    	if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
    		vehicleDAO = dbi.open(VehiclePersistence.class);
            List<VehicleModel> allVehiclesRegisteredByUser = vehicleDAO.findRegisteredByUser(userid);

            vehicleDAO.close();

            return allVehiclesRegisteredByUser;
		} else {
			return null;
		}
    }
}