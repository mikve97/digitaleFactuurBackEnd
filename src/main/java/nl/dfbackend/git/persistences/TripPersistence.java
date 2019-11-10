package nl.dfbackend.git.persistences;

import java.util.ArrayList;
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
	@SqlQuery("select * from trips where id = :id")
	TripModel findById(@Bind("id") int id);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<TripModel> trips
	 */
	@SqlQuery("select * from trips where userid = :userid")
	List<TripModel> findByUserId(@Bind("userid") int userid);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<TripModel> trips
	 */
	@SqlQuery("select * from trips")
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
	@SqlUpdate("INSERT INTO trips (projectid, userid, licenseplate, startlocation, endlocation, startkilometergauge, endkilometergauge)\n" + "VALUES (:projectId, :userId, :licensePlate, :startLocation, :endLocation, :startKilometergauge, :endKilometergauge);")
	void createTripForProject(@Bind("projectId") int projectId, @Bind("userId") int userId,
			@Bind("licensePlate") String licensePlate, @Bind("startLocation") String startLocation,
			@Bind("endLocation") String endLocation, @Bind("startKilometergauge") double startKilometergauge,
			@Bind("endKilometergauge") double endKilometergauge);
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlUpdate("UPDATE vehicle \n" + "   SET totaltrips = totaltrips + 1\n" + "WHERE licenseplate = :licenseplate;")
	void incrementAmountOfTripsMadeWithVehicle(@Bind("licenseplate") String licenseplate);
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlUpdate("DELETE FROM trips WHERE id = :id")
	void remove(@Bind("id") int id);
	
	/**
	 * @author Oussama Fahchouch
	 * @return List<Integer> uniqueProjectIds
	 */
	@SqlQuery("SELECT DISTINCT projectid FROM trips;")
	List<Integer> findAllUniqueProjects();

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
	
	void close();
	
	//find trips per user
	/**
	 * @author Fifi
	 *
	 */
	@SqlQuery("select count(*) from trips where userid = :userid")
	int findTripsPerUserID(@Bind("userid") int userid);
	
	//find trips per user that contain a project
	/**
	 * @author Fifi
	 *
	 */
	@SqlQuery("select count(id) from trips WHERE userid = :userid and projectid  <> 0")
	int findTripsPerUserIDWithProject(@Bind("userid") int userid);	
}