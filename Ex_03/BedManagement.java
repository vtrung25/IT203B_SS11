package Ex_03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BedManagement {

    private static final String URL = "jdbc:mysql://192.168.1.10:3306/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static void updateBedStatus(String inputId) {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();

            String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = '" + inputId + "'";

            int rowsAffected = stmt.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Đã cập nhật giường bệnh thành công!");
            } else {
                System.out.println("Mã giường không tồn tại hoặc không có thay đổi!");
            }

        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu!");
            e.printStackTrace();
        } finally {
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

    public static void main(String[] args) {
        updateBedStatus("Bed_001");
        updateBedStatus("Bed_999");
    }
}