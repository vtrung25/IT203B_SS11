package Ex_04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientSearch {

    private static final String URL = "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static String sanitizeInput(String input) {
        if (input == null) return null;

        input = input.replace("'", "");
        input = input.replace("--", "");
        input = input.replace(";", "");

        return input;
    }

    public static void searchPatient(String patientName) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();

            String safeName = sanitizeInput(patientName);

            String sql = "SELECT * FROM Patients WHERE full_name = '" + safeName + "'";

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Tên: " + rs.getString("full_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public static void main(String[] args) {

        searchPatient("' OR '1'='1");

        searchPatient("Nguyen Van A");
    }
}

