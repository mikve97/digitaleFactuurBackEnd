package nl.dfbackend.git;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class MainConfiguration extends Configuration {
	@NotEmpty
	private String password;
	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();
	
	@JsonProperty
	public String getPassword() {
		return password;
	}
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}
}
