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
	 * @author Bram de Jong
	 * @param licenseplate
	 */
	@SqlQuery("select * from vehicle where licenseplate = :licenseplate")
	VehicleModel findByLicensePlate(@Bind("licenseplate") String licenseplate);

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
	@SqlUpdate("INSERT INTO vehicle (licenseplate, userid, vehiclename, vehicletype, totaltrips, fuel, vehiclebody)\nVALUES (:licensePlate, :userId, :vehicleName, :vehicleType, :totalTrips, :fuel, :vehicleBody);")
	void createVehicleByUser(@Bind("licensePlate") String licensePlate,
							 @Bind("userId") int userId,
							 @Bind("vehicleName") String vehicleName,
							 @Bind("vehicleType") String vehicleType,
							 @Bind("totalTrips") int totalTrips,
							 @Bind("fuel") String fuel,
							 @Bind("vehicleBody") String vehicleBody);

	/**
	 * @author Bram de Jong
	 */
	@SqlUpdate("UPDATE vehicle\nSET userid = :userId, vehiclename = :vehicleName, vehicletype = :vehicleType, totaltrips = :totalTrips\nWHERE licenseplate = :licensePlate")
	void updateVehicleByUser(@Bind("licensePlate") String licensePlate,
							 @Bind("userId") int userId, @Bind("vehicleName") String vehicleName,
							 @Bind("vehicleType") String vehicleType, @Bind("totalTrips") int totalTrips);

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