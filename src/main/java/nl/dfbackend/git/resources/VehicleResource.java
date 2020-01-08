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
     * @author Bram de Jong
     */
    @Path("/vehicle/{licensePlate}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public VehicleModel getVehicle(@PathParam("licensePlate") String licensePlate) throws SQLException {
        return vehicleService.fetchVehicle(licensePlate);
    }

    /**
     * @author Bram de Jong
     */
    @Path("/vehicle/alter/for-user/{userId}/{totalTrips}/{licensePlate}/{vehicleName}/{vehicleType}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postAlterVehicleByUser(@PathParam("userId") int userId,
                                          @PathParam("licensePlate")String licensePlate,@PathParam("vehicleName") String vehicleName,@PathParam("vehicleType") String vehicleType,
                                          @PathParam("totalTrips") int totalTrips) throws SQLException {

        vehicleService.alterVehicleByUser(userId, totalTrips, licensePlate, vehicleName, vehicleType);

        return true;
    }

    /**
     * @author Bram de Jong
     */
    @Path("/vehicle/add/for-user/{userId}/{totalTrips}/{licensePlate}/{vehicleName}/{vehicleType}/{vehicleBody}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public boolean postVehicleByUser(@PathParam("userId") int userId,
                                     @PathParam("licensePlate")String licensePlate,@PathParam("vehicleName") String vehicleName,@PathParam("vehicleType") String vehicleType,
                                     @PathParam("totalTrips") int totalTrips, @PathParam("vehicleBody") String vehicleBody) throws SQLException {
        vehicleService.addVehicleByUser(userId, totalTrips, licensePlate, vehicleName, vehicleType, vehicleBody);

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