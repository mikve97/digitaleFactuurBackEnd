package nl.dfbackend.git.util;

import java.sql.SQLException;

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
	private static DbConnector singleInstance = null;

	/**
	 * @author Oussama Fahchouch
	 * @return DbConnector singleInstance
	 */
    public static DbConnector getInstance() 
    { 
        if (singleInstance == null) {
        	singleInstance = new DbConnector();
        }

        return singleInstance; 
    }

    /**
     * @author Oussama Fahchouch
     * @param PGPoolingDataSource source
     * @return DBI
     * @throws SQLException 
     */
	public static DBI getDBI(PGPoolingDataSource source) throws SQLException {
		source.getConnection();
		DBI dbi = new DBI(source);
		return dbi;
	}
	
	/**
     * @author Oussama Fahchouch
     * @return PGPoolingDataSource source
     */
	public static PGPoolingDataSource getSource() {
		PGPoolingDataSource source = new PGPoolingDataSource();

		source.setServerName(url);
		source.setDatabaseName(dbName);
		source.setUser(username);
		source.setPassword(password);
		source.setMaxConnections(5);
		
		return source;
	}
    
	/**
	 * @author Oussama Fahchouch
	 * @param PGPoolingDataSource source
	 */
	public static void closeSource(PGPoolingDataSource source) {
		source.close();
	}
}
