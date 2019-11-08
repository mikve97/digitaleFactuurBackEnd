package nl.dfbackend.git.mappers;

import nl.dfbackend.git.api.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * If credentials are given in JSON format from the front-end, through the given link /{username}/{password}
 * a response will be returned from the onLogin method.
 * The given parameters will be saved in the model 'Credential'.
 * This can be OK or UNAUTHORIZED.
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

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
