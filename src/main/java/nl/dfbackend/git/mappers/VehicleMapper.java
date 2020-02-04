package nl.dfbackend.git.mappers;

import nl.dfbackend.git.models.VehicleModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bram de Jong
 */
public class VehicleMapper implements ResultSetMapper<VehicleModel> {

    /**
     * @author Bram de Jong
     */
    @Override
    public VehicleModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new VehicleModel(
                r.getInt("vehicle_id"),
                r.getInt("userid"),
                r.getString("licenseplate"),
                r.getString("vehiclename"),
                r.getString("vehicletype"),
                r.getString("vehicleBody")
        );
    }
}