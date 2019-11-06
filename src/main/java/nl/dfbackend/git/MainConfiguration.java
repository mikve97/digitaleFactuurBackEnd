package nl.dfbackend.git;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

/**
 * @author Oussama Fahchouch
 */
public class MainConfiguration extends Configuration {
	@Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();
    
	@JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

}
