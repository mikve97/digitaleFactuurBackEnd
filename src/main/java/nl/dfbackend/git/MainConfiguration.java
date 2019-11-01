package nl.dfbackend.git;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class MainConfiguration extends Configuration {
	@NotEmpty
	private String password;
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();
	
	@JsonProperty
	public String getPassword() {
		return password;
	}
}
