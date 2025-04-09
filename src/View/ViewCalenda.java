package View;

import Controller.CalendaController;
import Model.Calenda;

import java.util.Scanner;

public class ViewCalenda {
    public static void main(String[] args) {
        CalendaController controller = new CalendaController();
        Scanner sc = new Scanner(System.in);

        System.out.println("Xin mời lựa chọn:");
        System.out.println("1 : Thêm lịch ");
        System.out.println("2 : Cập nhật lịch ");
        int n = Integer.parseInt(sc.nextLine());

        if (n == 1) {
            System.out.println("Nhập ID lịch:");
            String id = sc.nextLine();
            System.out.println("Nhập mã phòng học:");
            String classroom_id = sc.nextLine();
            System.out.println("Nhập mã môn học:");
            String subject_id = sc.nextLine();
            System.out.println("Nhập mã giảng viên:");
            String teacher_id = sc.nextLine();
            System.out.println("Nhập thời gian học (ví dụ: 2025-05-01 08:00:00):");
            String schedule_time = sc.nextLine();

            Calenda c = new Calenda(id, classroom_id, subject_id, teacher_id, schedule_time);
            controller.addCalendaController(c);
        }
        else if (n == 2) {
            System.out.println("Nhập ID lịch cần cập nhật:");
            String id = sc.nextLine();
            System.out.println("Nhập mã phòng học mới:");
            String classroom_id = sc.nextLine();
            System.out.println("Nhập mã môn học mới:");
            String subject_id = sc.nextLine();
            System.out.println("Nhập mã giảng viên mới:");
            String teacher_id = sc.nextLine();
            System.out.println("Nhập thời gian học mới (ví dụ: 2025-05-01 08:00:00):");
            String schedule_time = sc.nextLine();

            Calenda c = new Calenda(id, classroom_id, subject_id, teacher_id, schedule_time);
            controller.updateCalendaController(c);
        }
        else if(n==3){
            System.out.println("Nhập ID lịch:");
            String id = sc.nextLine();
            controller.removeCalendaController(id);
        }
    }
}
