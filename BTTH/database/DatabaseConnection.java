package BTTH.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "MedicalAppointmentDB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 1. Kết nối MySQL (chưa chọn DB)
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 2. Tạo DB nếu chưa có
            Statement st = conn.createStatement();
            st.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);

            // 3. Kết nối lại vào DB vừa tạo
            conn = DriverManager.getConnection(URL + DB_NAME, USER, PASSWORD);

            // 4. Tạo bảng nếu chưa có
            st = conn.createStatement();
            String createTable = "CREATE TABLE IF NOT EXISTS appointments ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "patient_name VARCHAR(255), "
                    + "appointment_date DATE, "
                    + "doctor_name VARCHAR(255), "
                    + "status VARCHAR(50)"
                    + ")";
            st.executeUpdate(createTable);

            return conn;

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
