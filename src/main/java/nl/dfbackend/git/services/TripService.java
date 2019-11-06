package nl.dfbackend.git.services;

import java.util.List;

import org.skife.jdbi.v2.DBI;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.persistences.TripDAO;
import nl.dfbackend.git.util.DbConnector;

public class TripService {
	private DBI dbi;
	private TripDAO tripDAO;
	
	public TripService() {
		DbConnector.getInstance();
		this.dbi = DbConnector.getDBI();
	}

	public boolean addTripByUser(int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) {
		
		tripDAO = dbi.open(TripDAO.class);
		tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.close();
		
		return true;
	}
	
	public boolean addTripForProject(int projectId, int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) {
		
		tripDAO = dbi.open(TripDAO.class);
		tripDAO.createTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.close();
		
		return true;
	}

	public TripModel fetchTrip(int id) {
		tripDAO = dbi.open(TripDAO.class);
		TripModel fetchedTrip = tripDAO.findById(id);
		tripDAO.close();
		
		return fetchedTrip;
	}
	
	public List<TripModel> fetchAllTrips() {
		tripDAO = dbi.open(TripDAO.class);
		List<TripModel> fetchedTrips = tripDAO.findAll();
		tripDAO.close();
		
		return fetchedTrips;
	}
}
