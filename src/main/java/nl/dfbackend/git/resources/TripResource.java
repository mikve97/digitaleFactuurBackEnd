package nl.dfbackend.git.resources;


import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.services.TripService;

@Path("/trips")
public class TripResource {
	private TripService tripService;
	
	public TripResource() {
		this.tripService = new TripService();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TripModel> getAllTrips() throws SQLException {
		return tripService.fetchAllTrips();
	}
	
	@Path("/trip/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public TripModel getTrip(@PathParam("id") int id) throws SQLException {
		return tripService.fetchTrip(id);
	}
	
	@Path("/trip/add/for-project/{projectId}/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postTripForProject(@PathParam("projectId") int projectId,@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,@PathParam("endKilometergauge") double endKilometergauge) throws SQLException {
		
		tripService.addTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		
		return true;
	}
	
	@Path("/trip/add/for-user/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public boolean postTripByUser(@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,@PathParam("endKilometergauge") double endKilometergauge) throws SQLException {
		
		tripService.addTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge);
		
		return true;
	}
}