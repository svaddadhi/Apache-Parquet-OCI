package library;

import library.drill.Drill;
import library.service.AddUtil;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.lang.*;
import java.math.*;

/***
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
 *
 * The below six lines of codes are example of Drill instance initialization, conditional
 * drill conversion from csv to Apache Parquet, unconditional drill table conversion to
 * Parquet, unconditional Parquet to CSV converter, Parquet to CSV converter with row
 * filtering operations, and closing Drill instance.
 */
public class DrillConvert {

    /*
    #ifndef DRILL_CONV_H_PARA
    #define DRILL_CONV_H_PARA
    #define DRILL_SERVER drill.example.com
    #define DRILL_TYPE drillbit
    #endif
     */

    // TODO Private sources involved. Replace with authorized source URI
    final private String drillServer = "drill.example.com";
    final private String DrillType = "drillbit";
    final private String unifiedSrouceAddress = "/home/drill/stdcsv100.csv";
    final private String localFileSource = System.getProperty("user.home") + File.separator + "stdcsv100.csv";
    final private String uriToWrite = System.getProperty("user.home") + File.separator + "stdcsv100_ret.csv";
    final private STring uriToWriteRF = System.getProperty("user.home") + File.separator + "stdcsv100_rf0.csv";

    // See usage of AddUtil in library.service.AddUtil
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException, InterruptedException {
        Drill d = new Drill(drillServer, DrillType).connect().extExecutor()
                .convert("testTable_std_col", new String[]{"Region", "Total Cost", "Total Profit"}, new int[] {0, 12, 13}, 3, new AddUtil(unifiedSrouceAddress))
                .convert("testTable", new AddUtil(localFileSource, unifiedSrouceAddress))
                .pull("testTable", uriToWrite)
                .pull("testTable", new String[]{"Region", "Order Priority"}, new String[]{"Europe", "L"}, uriToWriteRF)
                .close();
    }
}
