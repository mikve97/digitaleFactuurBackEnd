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
	@Path("/trip/add/for-project")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postTripForProjectNew(TripModel trip, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {		
		tripService.addTripForProject(trip, TokenHeaderParam);

		return true;
	}

	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @throws AuthenticationException 
	 */
	@Path("/trip/update/for-project")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean putTripForProject(TripModel trip, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {

		tripService.updateTripForProject(trip, TokenHeaderParam);

		return true;
	}
	
	/**
	 * @author Oussama Fahchouch
	 * @throws AuthenticationException 
	 */
	@Path("/trip/add/for-user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean postTripByUser(TripModel trip, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		
		tripService.addTripByUser(trip, TokenHeaderParam);
		
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
	 * @author Fifi & Oussama Fahchouch
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
	 * @author Wietse Nicolaas & Fifi Halley
	 * @throws AuthenticationException
	 */
	@Path("/getallByLicensePlate")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<TripModel> getTripsByLicensePlate(@QueryParam("licensePlate") String licensePlate, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return tripService.fetchTripsByLicensePlate(licensePlate, TokenHeaderParam);
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