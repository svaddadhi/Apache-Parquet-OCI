package library;

import library.drill.Drill;
import library.service.AddUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.lang.*;
import java.math.*;

public class DrillConvert {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
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

        /**
         * The below six lines of codes are example of Drill instance initialization, conditional
         * drill conversion from csv to Apache Parquet, unconditional drill table conversion to
         * Parquet, unconditional Parquet to CSV converter, Parquet to CSV converter with row
         * filtering operations, and closing Drill instance.
         */

        Drill d = new Drill("drill.yg-home.site", "drillbit").connect().extExecutor()
                .convert("testTable_std_col", new String[]{"Region", "Country", "Total Profit"}, 3, new AddUtil("/home/drill/stdcsv100.csv"))
                .convert("testTable", new AddUtil(System.getProperty("user.home") + File.separator + "stdcsv100.csv", "/home/drill/stdcsv100.csv"))
                .pull("testTable", System.getProperty("user.home") + File.separator + "stdcsv100_ret.csv")
                .pull("testTable", new String[]{"Region", "Order Priority"}, new String[]{"Europe", "L"}, System.getProperty("user.home") + File.separator + "stdcsv100_rf0.csv")
                .close();
    }
}
