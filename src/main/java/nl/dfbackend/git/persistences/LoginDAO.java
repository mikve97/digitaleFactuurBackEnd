package nl.dfbackend.git.persistences;

import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.mappers.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

/**
 * Here the query is executed to see if the user exists in the database
 *
 * @author Ali Rezaa Ghariebiyan
 * @version 08-11-2019
 */

@RegisterMapper(UserMapper.class)
public interface LoginDAO {
    @SqlQuery("SELECT * FROM df_user WHERE username = :username ")
    UserModel getUserByUsername(@Bind("username") String username);

    void close();
}
