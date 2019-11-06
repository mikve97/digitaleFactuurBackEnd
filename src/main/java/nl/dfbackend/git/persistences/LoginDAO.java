package nl.dfbackend.git.persistences;

import nl.dfbackend.git.api.Credential;
import nl.dfbackend.git.api.User;
import nl.dfbackend.git.models.TripModel;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;

public interface LoginDAO {

    @SqlQuery("select * from trips where id = :id")
    TripModel findById(@Bind("id") int id);

    @SqlQuery("")
    User findBy;

    @SqlQuery("SELECT * FROM user WHERE username = :username")
    User getUserByUsername(@Bind("username") String username);
}