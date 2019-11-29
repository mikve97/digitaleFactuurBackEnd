package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.services.TripService;

/**
 * @author Oussama Fahchouch
 */
@Path("/trips")
public class TripResource {
	private TripService tripService;
	
	public TripResource() throws SQLException {
		this.tripService = new TripService();
	}

	/**
	 * @author Oussama Fahchouch
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TripModel> getAllTrips() throws SQLException {
		return tripService.fetchAllTrips();
	}
	
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/trip/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TripModel getTrip(@PathParam("id") int id) throws SQLException {
		return tripService.fetchTrip(id);
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/trip/add/for-project/{projectId}/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postTripForProject(@PathParam("projectId") int projectId,@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,@PathParam("endKilometergauge") double endKilometergauge) throws SQLException {
		
		tripService.addTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/trip/add/for-user/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postTripByUser(@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,@PathParam("endKilometergauge") double endKilometergauge) throws SQLException {
		
		tripService.addTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/delete/{id}")
	@DELETE
	public void getDel(@PathParam("id") int id) throws SQLException {
		tripService.deleteTrip(id);
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/fetch/unique-projectids/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getAllUniqueProjectIds(@PathParam("userid") int userid) throws SQLException {
		return tripService.fetchAllUniqueProjectIds(userid);
	}
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Path("/user/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TripModel> getTripsMadeByUser(@PathParam("userid") int userid) throws SQLException {
		return tripService.fetchAllTripsByUser(userid);
	}
	

	/**
	 * @author Fifi
	 *
	 */
	@Path("/amount-of-trips/user/{userid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int readTripsMadeByUser(@PathParam("userid") int userid) throws SQLException {
		int tripsModelListPerUseruserId = tripService.fetchTripsPerUser(userid);
		return tripsModelListPerUseruserId;
	}

	
	
}