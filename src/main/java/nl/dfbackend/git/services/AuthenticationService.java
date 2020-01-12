package nl.dfbackend.git.services;

import java.util.Optional;

import javax.ws.rs.core.Response;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import nl.dfbackend.git.models.UserModel;

public class AuthenticationService implements Authenticator<String, UserModel> {
	private AuthorisationService authorisationService;
	
	public AuthenticationService() {
		this.authorisationService = new AuthorisationService();
	}

	@Override
	public Optional<UserModel> authenticate(String credentials) throws AuthenticationException {
		try {
			// if user bestaat - check in db, maak jwtoken
			// else restrict toegang tot api
			
			// return de user met juiste gegevens wanneer deze gaat inloggen
			UserModel user = new UserModel(null, credentials, credentials);
			return Optional.of(user);
		} catch (Exception e) {
			throw new AuthenticationException("The user is unauthorised.");
		}
	}

}
