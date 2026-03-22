package Ex_05.dao;
import Ex_05.context.DBContext;
import Ex_05.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public List<Doctor> getAllDoctors() {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT * FROM Doctors";

        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty")
                );
                list.add(d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean addDoctor(Doctor d) {
        String sql = "INSERT INTO Doctors VALUES (?, ?, ?)";

        try (Connection conn = DBContext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialty());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi: " + e.getMessage());
        }

        return false;
    }

    public void countBySpecialty() {
        String sql = "SELECT specialty, COUNT(*) AS total FROM Doctors GROUP BY specialty";

        try (Connection conn = DBContext.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("specialty") + " : " +
                                rs.getInt("total")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

