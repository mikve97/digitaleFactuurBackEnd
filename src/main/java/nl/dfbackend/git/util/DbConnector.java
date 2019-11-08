package nl.dfbackend.git.util;

import org.postgresql.ds.PGPoolingDataSource;
import org.skife.jdbi.v2.DBI;

/**
 * @author Oussama Fahchouch
 */
public class DbConnector {
	private final static String url = "manny.db.elephantsql.com:5432";
	private final static String dbName = "kvszgnmq";
	private final static String username = "kvszgnmq";
	private final static String password = "HLwhrVFmFDIO5LGAtLNPMghrHhQjEbdK";
	private static PGPoolingDataSource source = null;
	private static DbConnector singleInstance = null;
	
	/**
	 * @author Oussama Fahchouch
	 */
    public static DbConnector getInstance() 
    { 
        if (singleInstance == null) 
        	singleInstance = new DbConnector();
        	source  = new PGPoolingDataSource();
        	getDBI();

        return singleInstance; 
    } 
	
    /**
     * @author Oussama Fahchouch
     */
	public static DBI getDBI() {
		source.setServerName(url);
		source.setDatabaseName(dbName);
		source.setUser(username);
		source.setPassword(password);
		source.setMaxConnections(10);
		
		return new DBI(source);
	}
	
	
}
