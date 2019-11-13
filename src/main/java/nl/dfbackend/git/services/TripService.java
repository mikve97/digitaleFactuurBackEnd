package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.List;

import org.postgresql.ds.PGPoolingDataSource;
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
	}

	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public boolean addTripByUser(int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);
		tripDAO.createTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		
		source.close();
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public boolean addTripForProject(int projectId, int userId, String licensePlate, String startLocation, 
			String endLocation, double startKilometergauge, double endKilometergauge) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		tripDAO.createTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		tripDAO.incrementAmountOfTripsMadeWithVehicle(licensePlate);
		
		source.close();

		return true;
	}

	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public TripModel fetchTrip(int id) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		TripModel fetchedTrip = tripDAO.findById(id);
		
		source.close();

		return fetchedTrip;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public List<TripModel> fetchAllTrips() throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		List<TripModel> fetchedTrips = tripDAO.findAll();
		
		source.close();

		return fetchedTrips;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public List<TripModel> fetchAllTripsByUser(int userId) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		List<TripModel> fetchedTrips = tripDAO.findByUserId(userId);
		
		source.close();

		return fetchedTrips;
	}

	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	public void deleteTrip(int id) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		tripDAO.remove(id);

		source.close();
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjects
	 * @throws SQLException 
	 */
	public List<Integer> fetchAllUniqueProjectIds(int userid) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		List<Integer> fetchedUniqueProjectIds = tripDAO.findAllUniqueProjects(userid);
		
		source.close();

		return fetchedUniqueProjectIds;
	}


    /**
     * @author Mike van Es
     * @throws SQLException 
     */
    public List<TripModel> fetchAllTripsWithProject() throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

        List<TripModel> fetchedTrips = tripDAO.getAllTripsWithProject();
        
        source.close();
        
        return fetchedTrips;
    }

    /**
     * @author Mike van Es
     * @throws SQLException 
     */
    public List<TripModel> fetchAllTripsByProject(int pid) throws SQLException {
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

        List<TripModel> fetchedTrips = tripDAO.getAllTripsByProject(pid);

        source.close();

        return fetchedTrips;
    }
    
    /**
	 * @author Fifi
	 * @return int
     * @throws SQLException 
	 *
	 */
	public int fetchTripsPerUserWithProject(int userid) throws SQLException{
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		int fetchedTripsPerUserWithProject = tripDAO.findTripsPerUserIDWithProject(userid);

		source.close();

		return fetchedTripsPerUserWithProject;
	}
	
	/**
	 * @author Fifi
	 *@return int
	 * @throws SQLException 
	 */
	public int fetchTripsPerUser(int userid) throws SQLException{
		PGPoolingDataSource source = DbConnector.getSource();
		dbi = DbConnector.getDBI(source);
		
		tripDAO = dbi.open(TripPersistence.class);

		int fetchedTripsPerUser = tripDAO.findTripsPerUserID(userid);

		source.close();

		return fetchedTripsPerUser;
	}
}