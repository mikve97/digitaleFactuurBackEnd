package nl.dfbackend.git.persistences;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import nl.dfbackend.git.mappers.TripMapper;
import nl.dfbackend.git.models.TripModel;

/**
 * @author Oussama Fahchouch
 */
@RegisterMapper(TripMapper.class)
public interface TripPersistence {
	/**
	 * @author Oussama Fahchouch
	 * @return TripModel trip
	 */
	@SqlQuery("SELECT * FROM trips WHERE id = :id")
	TripModel findById(@Bind("id") int id);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<TripModel> trips
	 */
	@SqlQuery("SELECT * FROM trips WHERE userid = :userid")
	List<TripModel> findByUserId(@Bind("userid") int userid);
	
	/**
	 * @author Fifi Halley & Wietse Nicolaas
	 * @return  List<TripModel> trips
	 */
	@SqlQuery("SELECT * FROM trips where licenseplate = :licensePlate")
	List<TripModel> findTripsByLicensePlate(@Bind("licensePlate") String licensePlate);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<TripModel> trips
	 */
	@SqlQuery("SELECT * FROM trips")
	List<TripModel> findAll();
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlUpdate("INSERT INTO trips (projectid, userid, licenseplate, startlocation, endlocation, startkilometergauge, endkilometergauge)\n" + "VALUES (0, :userId, :licensePlate, :startLocation, :endLocation, :startKilometergauge, :endKilometergauge);")
	void createTripByUser(@Bind("userId") int userId,
			@Bind("licensePlate") String licensePlate, @Bind("startLocation") String startLocation,
			@Bind("endLocation") String endLocation, @Bind("startKilometergauge") double startKilometergauge,
			@Bind("endKilometergauge") double endKilometergauge);
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlUpdate("INSERT INTO trips (projectid, userid, licenseplate, startlocation, endlocation, startkilometergauge, endkilometergauge, drivenkm)\n" + "VALUES (:projectId, :userId, :licensePlate, :startLocation, :endLocation, :startKilometergauge, :endKilometergauge, :drivenKm);")
	void createTripForProject(@Bind("projectId") int projectId, @Bind("userId") int userId,
			@Bind("licensePlate") String licensePlate, @Bind("startLocation") String startLocation,
			@Bind("endLocation") String endLocation, @Bind("startKilometergauge") double startKilometergauge,
			@Bind("endKilometergauge") double endKilometergauge, @Bind("drivenKm") float drivenKm);

	/**
	 * @author Ali Rezaa Ghariebiyan
	 */

	@SqlUpdate("UPDATE trips\nSET projectid =:projectId, userid = :userId, licenseplate = :licensePlate, startlocation = :startLocation, endlocation = :endLocation, startkilometergauge = :startKilometergauge, endkilometergauge = :endKilometergauge, drivenkm = :drivenKm\nWHERE id = :tripId")
	void updateTripForProject(@Bind("tripId") int tripId,
							  @Bind("projectId") int projectId,
							  @Bind("userId") int userId,
							  @Bind("licensePlate") String licensePlate,
							  @Bind("startLocation") String startLocation,
							  @Bind("endLocation") String endLocation,
							  @Bind("startKilometergauge") double startKilometergauge,
							  @Bind("endKilometergauge") double endKilometergauge,
							  @Bind("drivenKm") float drivenKm
							  );
	
	/**
	 * @author Oussama Fahchouch
	 */

	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjectIds
	 */
	@SqlQuery("SELECT DISTINCT projectid FROM trips WHERE userid = :userid AND projectid != 0")
	List<Integer> findAllUniqueProjects(@Bind("userid") int userid);
	
	/**
	 * @author Oussama Fahchouch
	 * @return int lastTripId
	 */
	@SqlQuery("SELECT * FROM trips ORDER BY id DESC LIMIT 1")
	TripModel findLastTrip();
	

	
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlUpdate("DELETE FROM trips WHERE id = :id")
	void remove(@Bind("id") int id);
	
	/**
	 * @author Mike van Es
	 */
	@SqlQuery("SELECT * FROM trips WHERE projectid IS NOT NULL ORDER BY projectid")
	List<TripModel> getAllTripsWithProject();

	/**
	 * @author Mike van Es
	 */
	@SqlQuery("SELECT * FROM trips WHERE projectid = :pid ")
	List<TripModel> getAllTripsByProject(@Bind("pid") int pid);

	/**
	 * @author Mike van Es
	 * @return TripModel
	 */
	@SqlQuery("SELECT * FROM trips WHERE licenseplate = :licensePlate ORDER BY ID DESC LIMIT 1")
	TripModel findLastTripByLicensePlate(@Bind("licensePlate") String licensePlate);

	/**
	 * @author Fifi Halley & Wietse Nicolaas
	 * @return  List<TripModel> trips
	 */
	@SqlQuery("SELECT * FROM trips where licenseplate = :licensePlate")
	List<TripModel> findTripsByLicensePlate(@Bind("licensePlate") String licensePlate);


	void close();
	
	/**
	 * @author Fifi
	 *
	 */
	@SqlQuery("SELECT COUNT(*) FROM trips WHERE userid = :userid")
	int findTripsPerUserID(@Bind("userid") int userid);
	
}
