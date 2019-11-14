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
		int lastTripIdInDB = 75;
		TripModel trip = new TripModel(lastTripIdInDB + 1, 0, 1, "HH-VC-12", "Utrecht", "Den-Haag", 0, 0);
		
		try {
			tripService.addTripByUser(1, "HH-VC-12", "Utrecht", "Den-Haag", 0, 0);
			assertEquals(tripService.fetchTrip(lastTripIdInDB + 1).getTripId(), trip.getTripId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
