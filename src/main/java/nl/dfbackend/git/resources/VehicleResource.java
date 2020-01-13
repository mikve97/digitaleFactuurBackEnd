package nl.dfbackend.git.resources;


import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehicleModel> getAllVehicles() throws SQLException {
        return vehicleService.fetchAllVehicles();
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     */
    @Path("/vehicle/id/{vehicle_id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleModel getVehicle(@PathParam("vehicle_id") int vehicle_id) throws SQLException {
        return vehicleService.fetchVehicle(vehicle_id);
    }

    /**
     * @author Ali Rezaa Ghariebiyan
     */
    @Path("/vehicle/{licenseplate}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleModel getVehicleByLicenseplate(@PathParam("licenseplate") String licenseplate) throws SQLException {
        return vehicleService.fetchVehicleByLicensePlate(licenseplate);
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
     */
    @Path("/vehicle/add/for-user/{userId}/{licensePlate}/{vehicleName}/{vehicleType}/{vehicleBody}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postVehicleByUser(@PathParam("userId") int userId,
                                     @PathParam("licensePlate")String licensePlate,@PathParam("vehicleName") String vehicleName,@PathParam("vehicleType") String vehicleType,
                                     @PathParam("vehicleBody") String vehicleBody) throws SQLException {
        vehicleService.addVehicleByUser(userId, licensePlate, vehicleName, vehicleType, vehicleBody);

        return true;
    }

    /**
     * @author Bram de Jong
     * @param licensePlate
     * @return boolean
     * @throws SQLException 
     */
    @Path("/delete/{licensePlate}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteVehicleByUser (@PathParam("licensePlate") String licensePlate) throws SQLException {
        vehicleService.deleteVehicle(licensePlate);
        return true;
    }
    
	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allUniqueLicenseplates which were registered by a certain user
	 */
	@Path("/fetch/unique-licenseplates/{userid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<String> getAllUniqueLicenseplates(@PathParam("userid") int userid) throws SQLException {
		return vehicleService.fetchAllUniqueLicenseplates(userid);
	}
	
	/**
     * @author Oussama Fahchouch
     */
	@Path("/user/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<VehicleModel> getAllVehiclesMadeByUser(@PathParam("userid") int userid) throws SQLException {
        return vehicleService.fetchAllVehiclesRegisteredByUser(userid);
    }


}