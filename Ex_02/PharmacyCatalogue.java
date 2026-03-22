package Ex_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PharmacyCatalogue {


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


    public static void main(String[] args) {
        printPharmacyCatalogue();
    }


    public static void printPharmacyCatalogue() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();

            String sql = "SELECT medicine_name, stock FROM Pharmacy";
            rs = stmt.executeQuery(sql);

            boolean hasData = false;

            System.out.println("===== DANH MỤC THUỐC =====");

            while (rs.next()) {
                hasData = true;

                String name = rs.getString("medicine_name");
                int stock = rs.getInt("stock");

                System.out.println("Thuốc: " + name + " | Tồn kho: " + stock);
            }

            if (!hasData) {
                System.out.println("Kho không có thuốc nào.");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn dữ liệu!");
            e.printStackTrace();
        } finally {

            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
