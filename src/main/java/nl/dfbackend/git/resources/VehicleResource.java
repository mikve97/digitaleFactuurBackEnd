package nl.dfbackend.git.resources;


import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.AuthenticationException;
import nl.dfbackend.git.models.ProjectModel;
import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.models.VehicleModel;
import nl.dfbackend.git.services.VehicleService;

/**
 * @author Bram de Jong
 */
@Path("/vehicles")
public class VehicleResource {
    private VehicleService vehicleService;

    public VehicleResource() throws SQLException {
        this.vehicleService = new VehicleService();
    }

    /**
     * @author Bram de Jong
     * @throws AuthenticationException 
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehicleModel> getAllVehicles(@HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        return vehicleService.fetchAllVehicles(TokenHeaderParam);
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     * @throws AuthenticationException 
     */
    @Path("/vehicle/id/{vehicle_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleModel getVehicle(@PathParam("vehicle_id") int vehicle_id, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        return vehicleService.fetchVehicle(vehicle_id, TokenHeaderParam);
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     * @throws AuthenticationException 
     */
    @Path("/vehicle/{licenseplate}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleModel getVehicleByLicenseplate(@PathParam("licenseplate") String licenseplate, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        return vehicleService.fetchVehicleByLicensePlate(licenseplate, TokenHeaderParam);
    }

    /**
     * @author Bram de Jong
     */
//    @Path("/vehicle/alter/for-user/{userId}/{licensePlate}/{vehicleName}/{vehicleType}")
//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public boolean postAlterVehicleByUser(@PathParam("userId") int userId,
//                                          @PathParam("licensePlate")String licensePlate,@PathParam("vehicleName") String vehicleName,@PathParam("vehicleType") String vehicleType) throws SQLException {
//
//        vehicleService.alterVehicleByUser(userId, licensePlate, vehicleName, vehicleType);
//
//        return true;
//    }

    /**
     * @author Bram de Jong
     * @throws AuthenticationException 
     */
    @Path("/vehicle/add/for-user/{userId}/{licensePlate}/{vehicleName}/{vehicleType}/{vehicleBody}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postVehicleByUser(@PathParam("userId") int userId,
                                     @PathParam("licensePlate")String licensePlate,@PathParam("vehicleName") String vehicleName,@PathParam("vehicleType") String vehicleType,
                                     @PathParam("vehicleBody") String vehicleBody
                                     , @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        vehicleService.addVehicleByUser(userId, licensePlate, vehicleName, vehicleType, vehicleBody, TokenHeaderParam);

        return true;
    }

    /**
     * @author Bram de Jong
     * @param licensePlate
     * @return boolean
     * @throws SQLException 
     * @throws AuthenticationException 
     */
    @Path("/delete/{licensePlate}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteVehicleByUser (@PathParam("licensePlate") String licensePlate, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        vehicleService.deleteVehicle(licensePlate, TokenHeaderParam);
        return true;
    }
    
	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allUniqueLicenseplates which were registered by a certain user
	 * @throws AuthenticationException 
	 */
	@Path("/fetch/unique-licenseplates/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAllUniqueLicenseplates(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
		return vehicleService.fetchAllUniqueLicenseplates(userid, TokenHeaderParam);
	}
	
	/**
     * @author Oussama Fahchouch
	 * @throws AuthenticationException 
     */
	@Path("/user/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehicleModel> getAllVehiclesMadeByUser(@PathParam("userid") int userid, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        return vehicleService.fetchAllVehiclesRegisteredByUser(userid, TokenHeaderParam);
    }

    /**
     * @author Fifi Halley
     */
    @Path("/delete/{vehicleId}")
    @DELETE
    public void getDel(@PathParam("vehicleId") int vehicleId, @HeaderParam("Token") String TokenHeaderParam) throws SQLException, AuthenticationException {
        vehicleService.deleteVehicleById(vehicleId, TokenHeaderParam);
    }


}