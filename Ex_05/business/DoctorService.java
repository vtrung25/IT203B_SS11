package Ex_05.business;
import Ex_05.dao.DoctorDAO;
import Ex_05.model.Doctor;

import java.util.List;

public class DoctorService {
    private DoctorDAO dao = new DoctorDAO();

    public void showAllDoctors() {
        List<Doctor> list = dao.getAllDoctors();

        if (list.isEmpty()) {
            System.out.println("Không có bác sĩ.");
            return;
        }

        for (Doctor d : list) {
            System.out.println(
                    d.getId() + " | " +
                            d.getName() + " | " +
                            d.getSpecialty()
            );
        }
    }

    public void addDoctor(Doctor d) {
        if (d.getId().isEmpty() || d.getName().isEmpty()) {
            System.out.println("Dữ liệu không hợp lệ!");
            return;
        }

        if (dao.addDoctor(d)) {
            System.out.println("Thêm thành công!");
        } else {
            System.out.println("Thêm thất bại!");
        }
    }

    public void statistics() {
        dao.countBySpecialty();
    }
}

