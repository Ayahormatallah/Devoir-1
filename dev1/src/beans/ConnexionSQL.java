package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class ConnexionSQL implements AutoCloseable{ 
	private Connection con;
    private Statement stmt;

    public ConnexionSQL(String url, String user, String password) {
        try {
           Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Statement getStmt() {
        try {
            return con.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void close() {
        try {
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	 }