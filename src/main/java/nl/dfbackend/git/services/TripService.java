package nl.dfbackend.git.services;

import java.util.List;

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
	 */
	public TripService() {
		DbConnector.getInstance();
		this.dbi = DbConnector.getDBI();
	}

	/**
	 * @author Oussama Fahchouch
	 */
	public boolean addTripByUser(int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) {
		
		tripDAO = dbi.open(TripPersistence.class);
		tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		tripDAO.close();
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public boolean addTripForProject(int projectId, int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) {
		
		tripDAO = dbi.open(TripPersistence.class);
		tripDAO.createTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		tripDAO.close();
		
		return true;
	}

	/**
	 * @author Oussama Fahchouch
	 */
	public TripModel fetchTrip(int id) {
		tripDAO = dbi.open(TripPersistence.class);
		TripModel fetchedTrip = tripDAO.findById(id);
		tripDAO.close();
		
		return fetchedTrip;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public List<TripModel> fetchAllTrips() {
		tripDAO = dbi.open(TripPersistence.class);
		List<TripModel> fetchedTrips = tripDAO.findAll();
		tripDAO.close();
		
		return fetchedTrips;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	public List<TripModel> fetchAllTripsByUser(int userId) {
		tripDAO = dbi.open(TripPersistence.class);
		List<TripModel> fetchedTrips = tripDAO.findByUserId(userId);
		tripDAO.close();
		
		return fetchedTrips;
	}

	
	/**
	 * @author Oussama Fahchouch
	 */
	public void deleteTrip(int id) {
		tripDAO = dbi.open(TripPersistence.class);
		tripDAO.remove(id);
		tripDAO.close();	
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjects
	 */
	public List<Integer> fetchAllUniqueProjectIds() {
		tripDAO = dbi.open(TripPersistence.class);
		List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects();
		tripDAO.close();
		
		return fetchedUniqueProjectIds;
	}


    /**
     * @author Mike van Es
     */
    public List<TripModel> fetchAllTripsWithProject() {
        tripDAO = dbi.open(TripPersistence.class);
        List<TripModel> fetchedTrips = tripDAO.getAllTripsWithProject();
        tripDAO.close();

        return fetchedTrips;
    }

    /**
     * @author Mike van Es
     */
    public List<TripModel> fetchAllTripsByProject(int pid) {

        tripDAO = dbi.open(TripPersistence.class);
        List<TripModel> fetchedTrips = tripDAO.getAllTripsByProject(pid);
        tripDAO.close();

        return fetchedTrips;
    }
    
    /**
	 * @author Fifi
	 * @return int
	 *
	 */
	
	public int fetchTripsPerUserWithProject(int userid){
		tripDAO = dbi.open(TripPersistence.class);
		int fetchedTripsPerUserWithProject = tripDAO.findTripsPerUserIDWithProject(userid);
		tripDAO.close();
		return fetchedTripsPerUserWithProject;
	}
	
	/**
	 * @author Fifi
	 *@return int
	 */
	public int fetchTripsPerUser(int userid){
		tripDAO = dbi.open(TripPersistence.class);
		int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);
		tripDAO.close();
		return fetchedTripsPerUser;
	}
}