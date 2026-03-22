package Ex_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {


    private static final String URL = "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
