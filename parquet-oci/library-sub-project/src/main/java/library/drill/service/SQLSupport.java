package library.drill.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * This class should is not involved in the final product delivery.
 */

public class SQLSupport {

    public static boolean connValidator(Connection c) {
        try {
            if (c != null && !c.isClosed()) return true;
            else return false;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static byte creatBase(String base, Connection c) {
        if (connValidator(c) == false) return 0;
        String sql = "create database if not exists `" + base + "` DEFAULT CHARACTER SET utf8;";
        try {
            c.createStatement().executeUpdate(sql);
            return 1;
        } catch (SQLException e) {
            return 0;
        }
    }

    public static int countLine(String base, String tableName, Connection c) {
        if (connValidator(c) == false) return -1;
        String sql = "select count(*) as rowCount from `" + base + "`.`" + tableName + "`;";
        return countLine(sql, c);
    }

    public static int countLine(String sql, Connection c) {
        try {
            ResultSet rset = c.createStatement().executeQuery(sql);
            rset.next();
            int rtn = rset.getInt("rowCount");
            return rtn;
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean isTableExist(String base, String tableName, Connection c) {
        if (countLine(base, tableName, c) >= 0) return true;
        else return false;
    }

    public static boolean isSchemaExists(String name, Connection c) {
        if (connValidator(c) == false) return false;
        String sql = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME='" + name + "';";
        return countLine(sql, c) > 0;
    }

    public static byte makeSchema (String name, Connection c) {
        String sql = "CREATE SCHEMA `" + name + "` DEFAULT CHARACTER SET ascii ;";
        return executeUpdate(sql, c);
    }

    public static byte executeUpdate (String sql, Connection c) {
        if (sql.equals(";")) return 1;
        // System.out.println(sql);
        try {
            c.createStatement().executeUpdate(sql);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
