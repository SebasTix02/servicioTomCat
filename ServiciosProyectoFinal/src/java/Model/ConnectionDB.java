package Model;

import java.sql.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ariel
 */
public class ConnectionDB {

    public Connection con;

    public ConnectionDB() {

        String url = "jdbc:mysql://localhost:3306/soaproyecto";
        //String url = "jdbc:mysql://node141891-env-9549953.jelastic.saveincloud.net:3306/soaproyecto";
        String user = "root";
        //String password = "qdSffY1QbA";
        String password = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("connection succesfully");
            } catch (SQLException e) {
                System.out.println("Error trying to load the database");
                e.printStackTrace();
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("Error connecting to the MYSQL database");
            ex.printStackTrace();
        } 
    }

    public Connection getConnection() {
        return con;
    }
}
