package nl.dfbackend.git.persistences;

import nl.dfbackend.git.api.User;
import nl.dfbackend.git.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

@RegisterMapper(UserMapper.class)
public interface LoginDAO {
    @SqlQuery("SELECT * FROM df_user WHERE username = :username ")
    User getUserByUsername(@Bind("username") String username);

    void close();
}
