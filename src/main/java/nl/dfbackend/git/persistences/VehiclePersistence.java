package nl.dfbackend.git.persistences;

import nl.dfbackend.git.mappers.VehicleMapper;
import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.models.VehicleModel;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

/**
 * @author Bram de Jong
 */
@RegisterMapper(VehicleMapper.class)
public interface VehiclePersistence {

	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @param vehicle_id
	 */
	@SqlQuery("select * from vehicle where vehicle_id = :vehicle_id")
	VehicleModel findByVehicleId(@Bind("vehicle_id") int vehicle_id);


	/**
	 * @author Ali Rezaa Ghariebiyan
	 * @param licenseplate
	 */
	@SqlQuery("select * from vehicle where licenseplate = :licenseplate")
	VehicleModel findByVehicleLicenseplate(@Bind("licenseplate") String licenseplate);

	/**
	 * @author Bram de Jong
	 */
	@SqlQuery("select * from vehicle")
	List<VehicleModel> findAll();

	/**
	 * @author Bram de Jong
	 * @param licensePlate
	 * @param userId
	 * @param vehicleName
	 * @param vehicleType
	 * @param totalTrips
	 */
	@SqlUpdate("INSERT INTO vehicle (licenseplate, userid, vehiclename, vehicletype, vehiclebody)\nVALUES (:licensePlate, :userId, :vehicleName, :vehicleType, :vehicleBody);")
	void createVehicleByUser(@Bind("licensePlate") String licensePlate,
							 @Bind("userId") int userId,
							 @Bind("vehicleName") String vehicleName,
							 @Bind("vehicleType") String vehicleType,
							 @Bind("vehicleBody") String vehicleBody);

	/**
	 * @author Bram de Jong
	 */
	@SqlUpdate("UPDATE vehicle\nSET licenseplate =:licensplate, userid = :userId, vehiclename = :vehicleName, vehicletype = :vehicleType, fuel = :fuel, vehiclebody = :vehiclebody\nWHERE vehicle_id = :vehicle_id")
	void updateVehicleByUser(@Bind("licensePlate") String licensePlate,
							 @Bind("userId") int userId,
							 @Bind("vehicleName") String vehicleName,
							 @Bind("vehicleType") String vehicleType,
							 @Bind("fuel") String fuel,
							 @Bind("vehiclebody") String vehiclebody);

	/**
	 * @author Bram de Jong
	 */
	@SqlUpdate("DELETE FROM vehicle WHERE licenseplate = :licensePlate")
	void remove(@Bind("licensePlate") String licensePlate);

	/**
	 * @author Oussama Fahchouch
	 * @return List<String> uniqueLicenseplates
	 */
	@SqlQuery("SELECT DISTINCT licenseplate FROM vehicle WHERE userid = :userid")
	List<String> findAllUniqueLicenseplates(@Bind("userid") int userid);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<String> allVehiclesRegisteredByUser
	 */
	@SqlQuery("SELECT * FROM vehicle WHERE userid = :userid")
	List<VehicleModel> findRegisteredByUser(@Bind("userid") int userid);

	void close();




}