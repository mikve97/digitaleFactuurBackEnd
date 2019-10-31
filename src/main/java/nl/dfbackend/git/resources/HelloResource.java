package nl.dfbackend.git.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.dropwizard.auth.Auth;
import nl.dfbackend.git.core.User;

@Path("/hello")
public class HelloResource {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting() {
		return "hello world";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/secured")
	public String getSecuredGreeting(@Auth User user) {
		return "hello secured world";
	}
}
