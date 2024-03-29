package nl.dfbackend.git.services;

import java.sql.SQLException;
import java.util.Optional;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import nl.dfbackend.git.models.CredentialModel;
import nl.dfbackend.git.models.UserModel;
import nl.dfbackend.git.persistences.UserPersistence;
import nl.dfbackend.git.util.DbConnector;

/**
 * @author Oussama Fahchouch
 */
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
	public Optional<UserModel> authenticate(String jwtoken) throws AuthenticationException {
		try {
			UserModel user = null;
			
			if(this.authorisationService.decodeJWToken(jwtoken)) {
				userDAO = dbi.open(UserPersistence.class);
		        
		        user = userDAO.getUserByUsername(this.authorisationService.retrieveUsernameFromJWToken(jwtoken));
		        
		        userDAO.close();
			}
			
			return Optional.of(user);
		} catch (Exception e) {
			throw new AuthenticationException("The user is not authenticated.");
		}
	}
	
	/**
	 * @param credential
	 * @return Optional<UserModel>
	 * @throws SQLException
	 */
	public Optional<UserModel> authenticateUser(CredentialModel credential) throws SQLException {
        userDAO = dbi.open(UserPersistence.class);
        
        UserModel user = userDAO.getUserByUsernameAndPassword(credential.getUsername(), credential.getPassword());
		 
		user.setAuthToken(authorisationService.encodeJWToken(user.getUsername()));
		
        userDAO.close();
        
        return Optional.of(user);
    }
}
