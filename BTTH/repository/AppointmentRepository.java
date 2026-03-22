package BTTH.repository;
import BTTH.database.DatabaseConnection;
import BTTH.model.Appointment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    // Thêm
    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointments (patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());

            ps.executeUpdate();
            System.out.println("Thêm thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật
    public void updateAppointment(Appointment appointment) {
        String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, appointment.getPatientName());
            ps.setDate(2, appointment.getAppointmentDate());
            ps.setString(3, appointment.getDoctorName());
            ps.setString(4, appointment.getStatus());
            ps.setInt(5, appointment.getId());

            ps.executeUpdate();
            System.out.println("Cập nhật thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy theo ID
    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM appointments WHERE id=?";
        Appointment appointment = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointment;
    }

    // Lấy tất cả
    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Appointment ap = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
                list.add(ap);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
