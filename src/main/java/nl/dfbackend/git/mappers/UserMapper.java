package nl.dfbackend.git.mappers;

import nl.dfbackend.git.api.User;
import nl.dfbackend.git.models.TripModel;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    @Override
    public User map(int i, ResultSet r, StatementContext statementContext) throws SQLException {
        return new User(
                r.getInt("user_id"),
                r.getString("username"),
                r.getString("password")
        );
    }
}
