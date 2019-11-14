package nl.dfbackend.git.unittests;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.services.TripService;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

/**
 * @author Oussama Fahchouch
 */
public class TripTest {
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	@Test
	public void fetchATrip() throws SQLException {
		TripService tripService = new TripService();
		TripModel trip = new TripModel(45, 96718, 3, "ZZ-BB-11", "Amsterdam", "Rotterdam", 0, 0);
		
		try {
			assertEquals(tripService.fetchTrip(45).getTripId(), trip.getTripId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws SQLException 
	 */
	@Test
	public void addATripByUser() throws SQLException {
		TripService tripService = new TripService();
		TripModel trip = tripService.fetchLastTrip();
		
		try {
			tripService.addTripByUser(2, "HH-VC-12", "Utrecht", "Den-Haag", 0, 0);
			System.out.println(tripService.fetchLastTrip().getTripId());
			System.out.println(trip.getTripId());
			assertEquals(tripService.fetchLastTrip().getTripId(), trip.getTripId() + 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
