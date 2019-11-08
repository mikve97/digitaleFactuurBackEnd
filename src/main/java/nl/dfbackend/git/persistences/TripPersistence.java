package nl.dfbackend.git.persistences;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import nl.dfbackend.git.models.TripModel;
import nl.dfbackend.git.mappers.TripMapper;

/**
 * @author Oussama Fahchouch
 */
@RegisterMapper(TripMapper.class)
public interface TripPersistence {
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlQuery("select * from trips where id = :id")
	TripModel findById(@Bind("id") int id);
	
	
	/**
	 * @author Oussama Fahchouch
	 */
	@SqlQuery("select * from trips")
	List<TripModel> findAll();
	
	//find trips per user
	@SqlQuery("select count(*) from trips where userid = 1")
	int findTripsPerUserID();
	
	//find trips per user that contain a project
	@SqlQuery("select count(id) from trips WHERE userid = 1 and projectid <> 0")
	int findTripsPerUserIDWithProject();
	


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
	@SqlUpdate("DELETE FROM trips WHERE ID = :id")
	void remove(@Bind("id") int id);
	
	void close();
}
