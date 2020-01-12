package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.persistences.UserPersistence;
import nl.dfbackend.git.util.DbConnector;

public class AuthenticationService implements Authenticator<String, UserModel> {
	private AuthorisationService authorisationService;
    private DBI dbi;
    private UserPersistence userDAO;
	
	public AuthenticationService() throws SQLException {
		this.authorisationService = new AuthorisationService();
		DbConnector.getInstance();
		dbi = DbConnector.getDBI();
	}

	@Override
	public Optional<UserModel> authenticate(String credentials) throws AuthenticationException {
		try {
			// Authenticate jwtoken inplaats van usercheck
			
			UserModel user = new UserModel(null, credentials, credentials);
			return Optional.of(user);
		} catch (Exception e) {
			throw new AuthenticationException("The user is unauthorised.");
		}
	}
	
	public Optional<UserModel> authenticateUser(CredentialModel credential) throws SQLException {
        userDAO = dbi.open(UserPersistence.class);
        
        UserModel user = userDAO.getUserByUsername(credential.getUsername());
        user.setAuthToken(authorisationService.encodeJWToken(user.getUsername()));

        userDAO.close();
        
        return Optional.of(user);
    }


}
