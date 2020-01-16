package nl.dfbackend.git.resources;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.AuthenticationException;
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
	 * @throws AuthenticationException 
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<TripModel> getAllTrips(@HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchAllTrips(TokenHeaderParam);
	}
	
	@POST
	@Path("/testingPOST")
	@Consumes(MediaType.APPLICATION_JSON)
	public void testingBody(List<String> entity, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		System.out.println("body list param: " + entity);
		System.out.println("header token param: " + TokenHeaderParam);
		TripModel trip = tripService.fetchTrip(248, TokenHeaderParam);
		System.out.println("Token works, trip is riden with licenseplate: " + trip.getLicensePlate());
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/trip/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public TripModel getTrip(@PathParam("id") int id, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchTrip(id, TokenHeaderParam);
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/trip/add/for-project/{projectId}/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}/{drivenKm}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postTripForProject(@PathParam("projectId") int projectId,@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,
			@PathParam("endKilometergauge") double endKilometergauge, @PathParam("drivenKm") float drivenKm, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		
		tripService.addTripForProject(projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm, TokenHeaderParam);
		
		return true;
	}

	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @throws AuthenticationException 
	 */
	@Path("/trip/update/for-project/{tripId}/{projectId}/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}/{drivenKm}")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean putTripForProject(@PathParam("tripId") int tripId,@PathParam("projectId") int projectId,@PathParam("userId") int userId,
									  @PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
									  @PathParam("startKilometergauge") double startKilometergauge,
									  @PathParam("endKilometergauge") double endKilometergauge, @PathParam("drivenKm") float drivenKm
									  , @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {

		tripService.updateTripForProject(tripId, projectId, userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, drivenKm, TokenHeaderParam);

		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/trip/add/for-user/{userId}/{licensePlate}/{startLocation}/{endLocation}/{startKilometergauge}/{endKilometergauge}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postTripByUser(@PathParam("userId") int userId,
			@PathParam("licensePlate")String licensePlate,@PathParam("startLocation") String startLocation,@PathParam("endLocation") String endLocation,
			@PathParam("startKilometergauge") double startKilometergauge,@PathParam("endKilometergauge") double endKilometergauge
			, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		
		tripService.addTripByUser(userId, licensePlate, startLocation, endLocation, startKilometergauge, endKilometergauge, TokenHeaderParam);
		
		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/delete/{id}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	public void getDel(@PathParam("id") int id, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		tripService.deleteTrip(id, TokenHeaderParam);
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/delete")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void onDel(int[] tripsToDelete, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		tripService.onDeleteTrip(tripsToDelete, TokenHeaderParam);
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/fetch/unique-projectids/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Integer> getAllUniqueProjectIds(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchAllUniqueProjectIds(userid, TokenHeaderParam);
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/user/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<TripModel> getTripsMadeByUser(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchAllTripsByUser(userid, TokenHeaderParam);
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/fetch/unique-projectids-and-trips-amount/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Integer> getAllUniqueProjectIdsAndTripsAmount(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchTripsAndProjectsPerUser(userid, TokenHeaderParam);
	}
	
	/**
	 * @author Fifi
	 * @throws AuthenticationException 
	 *
	 */
	@Path("/amount-of-trips/user/{userid}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public int readTripsMadeByUser(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		int tripsModelListPerUseruserId = tripService.fetchTripsPerUser(userid, TokenHeaderParam);
		return tripsModelListPerUseruserId;
	}

	/**
	 * @author Mike van Es
	 * @throws AuthenticationException 
	 */
	@Path("/getByLicensePlate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLastKnownTripFromVehicle(@QueryParam("licensePlate") String licensePlate, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		TripModel tripModel = tripService.getTripByLicensePlate(licensePlate, TokenHeaderParam);
		if(tripModel != null) {
			return Response.ok(tripModel).build();
		}else{
			return Response.ok(null).build();
		}
	}

}