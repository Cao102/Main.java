package View;

import Controller.ExamController;
import Model.Exam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ExamView {
    private final ExamController controller = new ExamController();
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void displayMenu() {
        while (true) {
            System.out.println("""
                \n--- Quản lý lịch thi ---
                1. Lập lịch thi
                2. Cập nhật lịch thi
                3. Hủy kỳ thi
                4. Xem lịch thi theo lớp
                5. Xem kết quả thi
                6. Thoát
            """);
            System.out.print("Chọn: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> scheduleExam();
                case 2 -> updateExam();
                case 3 -> deleteExam();
                case 4 -> viewExamsByClass();
                case 5 -> viewExamResults();
                case 6 -> System.exit(0);
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void scheduleExam() {
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();
        System.out.print("Nhập ID môn học: ");
        String subjectId = scanner.nextLine();
        LocalDateTime examDate = inputDateTime("Nhập ngày kiểm tra");

        Exam exam = new Exam(classId, subjectId, examDate);
        boolean result = controller.scheduleExam(exam);
        System.out.println(result ? "Đã lập lịch thi." : "Lập lịch thi thất bại.");
    }

    private void updateExam() {
        System.out.print("Nhập ID kỳ thi: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();
        System.out.print("Nhập ID môn học: ");
        String subjectId = scanner.nextLine();
        LocalDateTime examDate = inputDateTime("Nhập ngày thi");

        Exam exam = new Exam(id, classId, subjectId, examDate);
        boolean result = controller.updateExam(exam);
        System.out.println(result ? "Đã cập nhật." : "Cập nhật thất bại.");
    }

    private void deleteExam() {
        System.out.print("Nhập ID kỳ thi: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean result = controller.deleteExam(id);
        System.out.println(result ? "✅ Đã xóa kỳ thi." : "Xóa thất bại.");
    }

    private void viewExamsByClass() {
        System.out.print("Nhập ID lớp học: ");
        String classId = scanner.nextLine();
        List<Exam> exams = controller.getExamsByClass(classId);
        exams.forEach(e -> System.out.printf("ID: %d | Môn: %s | Ngày thi: %s%n",
                e.getId(), e.getSubjectId(), e.getExamDate().format(dateTimeFormatter)));
    }

    private void viewExamResults() {
        System.out.print("Nhập ID kỳ thi: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(controller.getExamResults(id));
    }

    private LocalDateTime inputDateTime(String message) {
        while (true) {
            System.out.print(message + " (yyyy-MM-dd HH:mm): ");
            String dateTimeStr = scanner.nextLine();
            try {
                return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println(" Ngày giờ không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd HH:mm");
            }
        }
    }

}
