package nl.dfbackend.git.auth;



import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import nl.dfbackend.git.core.User;


public class HelloAuthenticator implements Authenticator<BasicCredentials, User> {
	private String password;
	
	public HelloAuthenticator(String password) {
		this.password = password;
	}
	
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) {
		if(password.equals(credentials.getPassword())) {
			return Optional.of(new User());
		} else {
			return Optional.absent();
		}
	}
  
}