package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.dfbackend.git.persistences.VehiclePersistence;
import org.skife.jdbi.v2.DBI;

import io.dropwizard.auth.AuthenticationException;
import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.persistences.TripPersistence;
import nl.dfbackend.git.util.DbConnector;

/**
 * @author Oussama Fahchouch
 */
public class TripService {
	private DBI dbi;
	private TripPersistence tripDAO;
	private AuthenticationService authenticationService;
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public TripService() throws SQLException {
		DbConnector.getInstance();
		dbi = DbConnector.getDBI();
		this.authenticationService = new AuthenticationService();
	}

	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public boolean addTripByUser(int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);
			tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
//			tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
			
			tripDAO.close();
			
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public boolean addTripForProject(int projectId, int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge, float drivenKm, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			tripDAO.createTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm);
//			tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
			
			tripDAO.close();

			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @throws SQLException
	 * @throws AuthenticationException 
	 */
	public boolean updateTripForProject(int tripId, int projectId, int userId, String licensePlate, String startLocation,
									 String endLocation, double startKilometergauge, double endKilometergauge, float drivenKm, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			tripDAO.updateTripForProject(tripId, projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm);
//			tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);

			tripDAO.close();

			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author Oussama Fahchouch
	 * @param tokenHeaderParam 
	 * @throws SQLException
	 * @return TripModel fetchedTrip
	 * @throws AuthenticationException 
	 */
	public TripModel fetchTrip(int id, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			TripModel fetchedTrip = tripDAO.findById(id);
			
			tripDAO.close();

			return fetchedTrip;
		} else {
			return null;
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return List<TripModel> fetchedTrips 
	 * @throws AuthenticationException 
	 */
	public List<TripModel> fetchAllTrips(String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);
			

			List<TripModel> fetchedTrips = tripDAO.findAll();
			System.out.println("--Test--" + fetchedTrips);
			
			tripDAO.close();

			return fetchedTrips;
		} else {
			return null;
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public List<TripModel> fetchAllTripsByUser(int userId, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			List<TripModel> fetchedTrips = tripDAO.findByUserId(userId);
			
			tripDAO.close();

			return fetchedTrips;
		} else {
			return null;
		}
	}
	
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public void deleteTrip(int id, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			tripDAO.remove(id);

			tripDAO.close();
		}
	}
	
	/**
	 * @author Fifi Halley
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public void onDeleteTrip(int[] tripsToDelete, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);
			
			for(int id : tripsToDelete) {
				tripDAO.remove(id);
			}
			
			tripDAO.close();
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjects
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public List<Integer> fetchAllUniqueProjectIds(int userid, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects(userid);
			
			tripDAO.close();

			return fetchedUniqueProjectIds;
		} else {
			return null;
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return TripModel fetchedTrip
	 * @throws AuthenticationException 
	 */
	public TripModel fetchLastTrip(String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			TripModel fetchedTrip = tripDAO.findLastTrip();
			
			tripDAO.close();

			return fetchedTrip;
		} else {
			return null;
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return List<integer> listWithCountTripsPerUserAndProjects
	 * @throws AuthenticationException 
	 */
	public List<Integer> fetchTripsAndProjectsPerUser(int userid, String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			List<Integer> fetchTripsAndProjectsPerUser = new ArrayList();
			
			tripDAO = dbi.open(TripPersistence.class);
			System.out.println("userid to use: " + userid);
			int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);
			List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects(userid);
			
			int fetchedAmountProjectsPerUser = fetchedUniqueProjectIds.size();
			
			fetchTripsAndProjectsPerUser.add(fetchedTripsPerUser);
			fetchTripsAndProjectsPerUser.add(fetchedAmountProjectsPerUser);
			
			tripDAO.close();

			return fetchTripsAndProjectsPerUser;
		} else {
			return null;
		}
	}

    /**
     * @author Mike van Es
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public List<TripModel> fetchAllTripsWithProject(String tokenHeaderParam) throws SQLException, AuthenticationException {
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

	        List<TripModel> fetchedTrips = tripDAO.getAllTripsWithProject();
	        
	        tripDAO.close();
	        
	        return fetchedTrips;
		} else {
			return null;
		}
    }

    /**
     * @author Mike van Es
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    public List<TripModel> fetchAllTripsByProject(int pid) throws SQLException, AuthenticationException {
    	tripDAO = dbi.open(TripPersistence.class);

        List<TripModel> fetchedTrips = tripDAO.getAllTripsByProject(pid);

        tripDAO.close();

        return fetchedTrips;
    }
    
	/**
	 * @author Fifi
	 *@return int
	 * @throws SQLException 
	 * @throws AuthenticationException 
	 */
	public int fetchTripsPerUser(int userid, String tokenHeaderParam) throws SQLException, AuthenticationException{
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);

			tripDAO.close();

			return fetchedTripsPerUser;
		} else {
			return 0;
		}
	}

	public TripModel getTripByLicensePlate(String licensePlate, String tokenHeaderParam) throws AuthenticationException{
		if (this.authenticationService.authenticate(tokenHeaderParam).isPresent()) {
			tripDAO = dbi.open(TripPersistence.class);

			TripModel lastKnownTrip = tripDAO.findLastTripByLicensePlate(licensePlate);

			tripDAO.close();

			return lastKnownTrip;
		} else {
			return null;
		}
	};

}
