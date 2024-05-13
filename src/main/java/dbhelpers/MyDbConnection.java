/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 
 */
public class MyDbConnection {
    private static final String dbUrl = "jdbc:mysql://scifi.cad08olfvula.us-east-1.rds.amazonaws.com/";
    private static final String dbName = "scif_library";
    private static final String dbUser = "mist4630";
    private static final String dbPwd  = "Fa23M4630";
    
    private static Connection connection = null;
    private MyDbConnection(){}
    
    public static Connection getConnection(){
        if (connection != null){
            return connection;
        }
        
        String url = dbUrl + dbName;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, dbUser, dbPwd); 
        } catch (ClassNotFoundException | SQLException e){
            // NOTE: Errors that occur here will show in the Console, but will not
            // stop the web app from running. These errors could be handled better.
            e.printStackTrace();
        }
        
        if (connection == null){
            throw new RuntimeException("Error connecting to database! :(");
        }
        return connection;
    }    
}