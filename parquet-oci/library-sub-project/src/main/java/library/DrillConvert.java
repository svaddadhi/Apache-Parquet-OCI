package library;

import java.sql.*;
import java.lang.*;

public class DrillConvert {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException{

        // load the JDBC driver
        Class.forName("org.apache.drill.jdbc.Driver");

        // Connect the drill using drill path
        Connection connection = DriverManager.getConnection("jdbc:drill: = localhost:2181/drill/drillbits1");

        // Query drill
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from cp.`employee.json` LIMIT 3");

        // Fetch and show the result
        while(rs.next()){
            System.out.println("Name: " + rs.getString(2));
        }
    }
}
