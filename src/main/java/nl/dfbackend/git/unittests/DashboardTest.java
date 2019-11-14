package nl.dfbackend.git.unittests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;


import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.services.ProjectService;
import nl.dfbackend.git.services.TripService;

/**
 * @author Fifi
 *
 */
public class DashboardTest {
	
	
	@Test
	public void totalTripsTest() throws SQLException{
		int userid = 4;
		TripService t1 = new TripService();
		int expectedResult = 2;
		int result = t1.fetchTripsPerUser(userid);
		assertEquals(expectedResult,result);		
	}
	
	@Test
	public void test() throws SQLException {
		ProjectService p1 = new ProjectService();
		List<ProjectModel> expectedResult = Arrays.asList(new ProjectModel[4]);
		List<ProjectModel> result = p1.getProjectsFromApi("79554e2460ae336c07c3eb0208adbb4cc4af184c17b51e0a2373cc0f9bba87b5" , "56840");
		
		assertEquals(expectedResult.size(),result.size());
	}
}
