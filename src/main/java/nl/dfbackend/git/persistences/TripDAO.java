package nl.dfbackend.git.persistences;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import nl.dfbackend.git.models.TripModel;

@RegisterMapper(TripMapper.class)
public interface TripDAO {	
	@SqlQuery("select * from trips where id = :id")
	TripModel findById(@Bind("id") int id);
	
	@SqlQuery("select * from trips")
	List<TripModel> findAll();
	
	@SqlUpdate("INSERT INTO trips (projectid, userid, licenseplate, startlocation, endlocation, startkilometergauge, endkilometergauge)\n" + "VALUES (0, :userId, :licensePlate, :startLocation, :endLocation, :startKilometergauge, :endKilometergauge);")
	void createTripByUser(@Bind("userId") int userId,
			@Bind("licensePlate") String licensePlate, @Bind("startLocation") String startLocation,
			@Bind("endLocation") String endLocation, @Bind("startKilometergauge") double startKilometergauge,
			@Bind("endKilometergauge") double endKilometergauge);
	
	
	@SqlUpdate("INSERT INTO trips (projectid, userid, licenseplate, startlocation, endlocation, startkilometergauge, endkilometergauge)\n" + "VALUES (:projectId, :userId, :licensePlate, :startLocation, :endLocation, :startKilometergauge, :endKilometergauge);")
	void createTripForProject(@Bind("projectId") int projectId, @Bind("userId") int userId,
			@Bind("licensePlate") String licensePlate, @Bind("startLocation") String startLocation,
			@Bind("endLocation") String endLocation, @Bind("startKilometergauge") double startKilometergauge,
			@Bind("endKilometergauge") double endKilometergauge);
	
	
	@SqlUpdate("DELETE FROM trips WHERE ID = :id")
	void remove(@Bind("id") int id);
	
	void close();
}
