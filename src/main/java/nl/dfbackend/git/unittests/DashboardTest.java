package nl.dfbackend.git.unittests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import nl.dfbackend.git.services.TripService;

/**
 * @author Fifi
 *
 */
public class DashboardTest {
	@Test
	public void totalProjectsTest() throws SQLException {
		int userid = 4;
		TripService t1 = new TripService();
		int expectedResult = 1;
		int result = t1.fetchTripsPerUserWithProject(userid);
		assertEquals(expectedResult,result);
		
	}
	
	@Test
	public void totalTripsTest() throws SQLException{
		int userid = 4;
		TripService t1 = new TripService();
		int expectedResult = 2;
		int result = t1.fetchTripsPerUser(userid);
		assertEquals(expectedResult,result);		
	}
}
