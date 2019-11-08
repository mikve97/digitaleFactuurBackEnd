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
	 *
	 *
	 */
	
	public List<TripModel> fetchAllTrips() {
		tripDAO = dbi.open(TripPersistence.class);
		List<TripModel> fetchedTrips = tripDAO.findAll();
		tripDAO.close();
		
		return fetchedTrips;
	}
	
	/**
	 * @author Fifi
	 *
	 */
	
	public int fetchTripsPerUserWithProject(){
		tripDAO = dbi.open(TripPersistence.class);
		int fetchedTripsPerUserWithProject = tripDAO.findTripsPerUserIDWithProject();
		tripDAO.close();
		return fetchedTripsPerUserWithProject;
	}
	
	/**
	 * @author Fifi
	 *
	 */
	
	public int fetchTripsPerUser(){
		tripDAO = dbi.open(TripPersistence.class);
		int fetchedTripsPerUser = tripDAO.findTripsPerUserID();
		tripDAO.close();
		return fetchedTripsPerUser;
	}
}
