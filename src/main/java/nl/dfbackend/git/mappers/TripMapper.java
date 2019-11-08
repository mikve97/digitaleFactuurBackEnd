package nl.dfbackend.git.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import nl.dfbackend.git.models.TripModel;

/**
 * @author Oussama Fahchouch
 */
public class TripMapper implements ResultSetMapper<TripModel> {
	
	/**
	 * @author Oussama Fahchouch
	 */
	@Override
	public TripModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		return new TripModel(
				r.getInt("id"), 
				r.getInt("projectid"),
				r.getInt("userid"),
				r.getString("licenseplate"),
				r.getString("startlocation"),
				r.getString("endlocation"),
				r.getDouble("startkilometergauge"),
				r.getDouble("endkilometergauge"),
				r.getFloat("startLat"),
				r.getFloat("endLat"),
				r.getFloat("startLong"),
				r.getFloat("endLong")

				);
	}

}
