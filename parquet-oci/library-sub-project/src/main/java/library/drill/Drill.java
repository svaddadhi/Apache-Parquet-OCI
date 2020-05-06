package library.drill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Drill {
    final private String host, protocol;
    private String  base, scheme;
    private Connection conn;
    private Statement st;

    public Connection getConn() {return this.conn;}
    public Statement getSt() {return this.st;}

    public Drill(String host, String prot) {
        this.host = host;
        this.protocol = prot;
    }

    public Drill connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.drill.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:drill:" + this.protocol + "=" + this.host);
        return this;
    }

    public Drill extExecutor() throws SQLException {
        this.st = this.conn == null ? null : this.conn.createStatement();
        return this;
    }

    public Drill setConnProp(String base, String scheme) {
        this.base = base;
        this.scheme = scheme;
        return this;
    }

    public Drill newTable(String title[], String type[], String prop[]) {
        return this;
    }
}
