package View;

import Controller.CalendaController;
import Model.Calenda;

import java.util.Scanner;

public class ViewCalenda {
    public static void main(String[] args) {
        CalendaController controller = new CalendaController();
        Scanner sc = new Scanner(System.in);
            System.out.println("========= QUẢN LÝ LỊCH HỌC =========");
            System.out.println("1. Thêm lịch học");
            System.out.println("2. Cập nhật lịch học");
            System.out.println("3. Xoá lịch học");
            System.out.println("4. Xem lịch học theo phòng");
            System.out.println("5. Xem lịch học theo giảng viên (sắp làm)");
            System.out.println("0. Thoát");
            System.out.println("====================================");

            System.out.print("Chọn chức năng: ");
        int n = Integer.parseInt(sc.nextLine());
            switch (n) {
                case 1:
                    System.out.println("--- Thêm Lịch Học ---");
                    System.out.print("Nhập ID lịch: ");
                    String id = sc.nextLine();
                    System.out.print("Nhập mã phòng học: ");
                    String classroom_id = sc.nextLine();
                    System.out.print("Nhập mã môn học: ");
                    String subject_id = sc.nextLine();
                    System.out.print("Nhập mã giảng viên: ");
                    String teacher_id = sc.nextLine();
                    System.out.print("Nhập thời gian học (VD: 2025-05-01 08:00:00): ");
                    String schedule_time = sc.nextLine();

                    Calenda c1 = new Calenda(id, classroom_id, subject_id, teacher_id, schedule_time);
                    controller.addCalendaController(c1);
                    break;

                case 2:
                    System.out.println("--- Cập Nhật Lịch Học ---");
                    System.out.print("Nhập ID lịch cần cập nhật: ");
                    String updateId = sc.nextLine();
                    System.out.print("Nhập mã phòng học mới: ");
                    String newClassroom = sc.nextLine();
                    System.out.print("Nhập mã môn học mới: ");
                    String newSubject = sc.nextLine();
                    System.out.print("Nhập mã giảng viên mới: ");
                    String newTeacher = sc.nextLine();
                    System.out.print("Nhập thời gian học mới: ");
                    String newTime = sc.nextLine();

                    Calenda c2 = new Calenda(updateId, newClassroom, newSubject, newTeacher, newTime);
                    controller.updateCalendaController(c2);
                    break;

                case 3:
                    System.out.println("--- Xoá Lịch Học ---");
                    System.out.print("Nhập ID lịch cần xoá: ");
                    String deleteId = sc.nextLine();
                    controller.removeCalendaController(deleteId);
                    break;

                case 4:
                    System.out.println("--- Xem Lịch Theo Phòng ---");
                    System.out.print("Nhập mã phòng: ");
                    String roomId = sc.nextLine();
                    controller.showCalendabyClassRoomIDController(roomId);
                    break;

                case 5:
                    System.out.println("--- Xem Lịch Theo Giảng Viên ---");

                    break;

                case 0:
                    System.out.println("Đã thoát chương trình.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }

            System.out.println("====================================");

    }
}
