package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nl.dfbackend.git.persistences.VehiclePersistence;
import org.skife.jdbi.v2.DBI;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.persistences.TripPersistence;
import nl.dfbackend.git.util.DbConnector;

/**
 * @author Oussama Fahchouch
 */
public class TripService {
	private DBI dbi;
	private TripPersistence tripDAO;
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public TripService() throws SQLException {
		DbConnector.getInstance();
		dbi = DbConnector.getDBI();
	}

	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public boolean addTripByUser(int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) throws SQLException {
		
		tripDAO = dbi.open(TripPersistence.class);
		tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		
		tripDAO.close();
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public boolean addTripForProject(int projectId, int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge, float drivenKm) throws SQLException {
		
		tripDAO = dbi.open(TripPersistence.class);

		tripDAO.createTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		
		tripDAO.close();

		return true;
	}

	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @throws SQLException
	 */
	public boolean updateTripForProject(int tripId, int projectId, int userId, String licensePlate, String startLocation,
									 String endLocation, double startKilometergauge, double endKilometergauge, float drivenKm) throws SQLException {

		tripDAO = dbi.open(TripPersistence.class);

		tripDAO.updateTripForProject(tripId, projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);

		tripDAO.close();

		return true;
	}

	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return TripModel fetchedTrip
	 */
	public TripModel fetchTrip(int id) throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		TripModel fetchedTrip = tripDAO.findById(id);
		
		tripDAO.close();

		return fetchedTrip;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return List<TripModel> fetchedTrips 
	 */
	public List<TripModel> fetchAllTrips() throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		List<TripModel> fetchedTrips = tripDAO.findAll();
		
		tripDAO.close();

		return fetchedTrips;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public List<TripModel> fetchAllTripsByUser(int userId) throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		List<TripModel> fetchedTrips = tripDAO.findByUserId(userId);
		
		tripDAO.close();

		return fetchedTrips;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public void deleteTrip(int id) throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		tripDAO.remove(id);

		tripDAO.close();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjects
	 * @throws SQLException 
	 */
	public List<Integer> fetchAllUniqueProjectIds(int userid) throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects(userid);
		
		tripDAO.close();

		return fetchedUniqueProjectIds;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return TripModel fetchedTrip
	 */
	public TripModel fetchLastTrip() throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

		TripModel fetchedTrip = tripDAO.findLastTrip();
		
		tripDAO.close();

		return fetchedTrip;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException
	 * @return List<integer> listWithCountTripsPerUserAndProjects
	 */
	public List<Integer> fetchTripsAndProjectsPerUser(int userid) throws SQLException {
		List<Integer> fetchTripsAndProjectsPerUser = new ArrayList();
		
		tripDAO = dbi.open(TripPersistence.class);

		int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);
		List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects(userid);
		int fetchedAmountProjectsPerUser = fetchedUniqueProjectIds.size();
		
		fetchTripsAndProjectsPerUser.add(fetchedTripsPerUser);
		fetchTripsAndProjectsPerUser.add(fetchedAmountProjectsPerUser);
		
		tripDAO.close();

		return fetchTripsAndProjectsPerUser;
	}

    /**
     * @author Mike van Es
     * @throws SQLException 
     */
    public List<TripModel> fetchAllTripsWithProject() throws SQLException {
    	tripDAO = dbi.open(TripPersistence.class);

        List<TripModel> fetchedTrips = tripDAO.getAllTripsWithProject();
        
        tripDAO.close();
        
        return fetchedTrips;
    }

    /**
     * @author Mike van Es
     * @throws SQLException 
     */
    public List<TripModel> fetchAllTripsByProject(int pid) throws SQLException {
		tripDAO = dbi.open(TripPersistence.class);

        List<TripModel> fetchedTrips = tripDAO.getAllTripsByProject(pid);

        tripDAO.close();

        return fetchedTrips;
    }
    
	/**
	 * @author Fifi
	 *@return int
	 * @throws SQLException 
	 */
	public int fetchTripsPerUser(int userid) throws SQLException{
		tripDAO = dbi.open(TripPersistence.class);

		int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);

		tripDAO.close();

		return fetchedTripsPerUser;
	}

	public TripModel getTripByLicensePlate(String licensePlate){
		tripDAO = dbi.open(TripPersistence.class);

		TripModel lastKnownTrip = tripDAO.findLastTripByLicensePlate(licensePlate);

		tripDAO.close();

		return lastKnownTrip;
	};

}