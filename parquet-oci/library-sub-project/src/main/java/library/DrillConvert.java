package library;

import library.drill.Drill;
import library.service.AddUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.lang.*;
import java.math.*;

public class DrillConvert {

    public static void main( String[] args ) throws SQLException, ClassNotFoundException{

        /*// load the JDBC driver
        Class.forName("org.apache.drill.jdbc.Driver");

        // Connect the drill using drill path
        Connection connection = DriverManager.getConnection("jdbc:drill:drillbit=drill.yg-home.site");

        // Query drill
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery("SELECT * from dfs.`home/phvle/file_to_convert.parquet` LIMIT 3");

        // Fetch and show the result
        while(rs.next()){
            System.out.println("Name: " + rs.getString(2));
        }*/
        /**
         * The second parameter required for the FULL table conversion
         * and the last parameter required for the partial conversion
         * are the file address utility object. When using partial
         * conversion, use AddUtil::new(char * fn), where fn is the
         * character pointer, a.k.a. string, for the file name. If
         * AddUtil::new(char * efn, char * dfn) is called in partial
         * conversion function, the efn will be recorded but will not
         * be ever used. For the unconditional conversion, use
         * AddUtil::new(char * fn) if the csv file address on the
         * execution server is exactly identical with the one on the
         * data server. Otherwise, use
         * AddUtil::new(char * efn, char * dfn) for full conversion.
         * efn is to specify the csv copy address on the execution
         * machine, and dfn is to specify the file address resides on
         * the data server.
         *
         * Here is the calling prototype:
         *
         * For conditional table creation, use:
         * Drill::convert(char *, char **, unsigned int, AddUtil::new(char *));
         *
         * For unconditional identical-address-conversion, use:
         * Drill::convert(char *, AddUtil::new(char *));
         *
         * For unconditional general address table creation, use:
         * Drill::convert(char *, AddUtil::new(char *, char *));
         *
         * Attention: The first line of the executive copy and the
         * data copy must be definitely identical. Otherwise an
         * error may occur, and the program may be crashed.
         */
        Drill d = new Drill("drill.yg-home.site", "drillbit").connect().extExecutor();
        //d.convert("testTable", new String[] {"PolicyID"}, 1, new AddUtil("/home/drill/FL_insurance_sample.csv"));
        //d.convert("testTable", new AddUtil(System.getProperty("user.home") + File.separator + "stdcsv100.csv", "/home/drill/stdcsv100.csv"));
        try {
            d.read("testTable", System.getProperty("user.home") + File.separator + "stdcsv100_ret.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
