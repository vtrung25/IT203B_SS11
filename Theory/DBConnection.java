package Theory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Session11?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASSWORD = "123456789";

    public static Connection openConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try (Connection connection = openConnection()) {

            if (connection == null) {
                System.out.println("Kết nối thất bại!");
                return;
            }
            System.out.println("Kết nối thành công! : Đinh Quang Huy");

            Statement stm = connection.createStatement();

            // 1. CREATE TABLE
            String createTable = """
                CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50),
                password VARCHAR(50)
            )""";
            stm.execute(createTable);

            // 2. INSERT (Thêm dữ liệu)
            String insert = "INSERT INTO users(username, password) VALUES ('huy', '123'), ('hà', '123')";
            stm.executeUpdate(insert);

            // 3. UPDATE (Sửa dữ liệu)
            String update = "UPDATE users SET password = '999' WHERE username = 'huy'";
            stm.executeUpdate(update);

            // 4. SELECT (Lấy dữ liệu)
            String select = "SELECT * FROM users";
            ResultSet rs = stm.executeQuery(select);

            System.out.println("Danh sách users:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                System.out.println(id + " - " + username + " - " + password);
            }

            // 5. DELETE (Xóa dữ liệu)
            String delete = "DELETE FROM users WHERE username = 'huy'";
            stm.executeUpdate(delete);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
