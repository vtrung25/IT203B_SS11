package Ex_05.presentation;
import Ex_05.business.DoctorService;
import Ex_05.model.Doctor;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();

        while (true) {
            System.out.println("\n===== RIKKEI CARE =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");
            System.out.print("Chọn: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    service.showAllDoctors();
                    break;

                case 2:
                    System.out.print("Nhập ID: ");
                    String id = sc.nextLine();

                    System.out.print("Nhập tên: ");
                    String name = sc.nextLine();

                    System.out.print("Nhập chuyên khoa: ");
                    String spec = sc.nextLine();

                    service.addDoctor(new Doctor(id, name, spec));
                    break;

                case 3:
                    service.statistics();
                    break;

                case 4:
                    System.out.println("Thoát...");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}

