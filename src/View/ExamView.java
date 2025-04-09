package View;

import Controller.ExamController;
import Model.Exam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ExamView {
    private final ExamController controller = new ExamController();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        while (true) {
            System.out.println("""
                \n--- Exam Management ---
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
        System.out.print("Class ID: ");
        String classId = scanner.nextLine();
        System.out.print("Subject ID: ");
        String subjectId = scanner.nextLine();
        System.out.print("Exam date (yyyy-MM-ddTHH:mm): ");
        LocalDateTime examDate = LocalDateTime.parse(scanner.nextLine());

        Exam exam = new Exam(classId, subjectId, examDate);
        boolean result = controller.scheduleExam(exam);
        System.out.println(result ? "Đã lập lịch thi." : "Thất bại.");
    }

    private void updateExam() {
        System.out.print("Exam ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Class ID: ");
        String classId = scanner.nextLine();
        System.out.print("Subject ID: ");
        String subjectId = scanner.nextLine();
        System.out.print("Exam date (yyyy-MM-ddTHH:mm): ");
        LocalDateTime examDate = LocalDateTime.parse(scanner.nextLine());

        Exam exam = new Exam(id, classId, subjectId, examDate);
        boolean result = controller.updateExam(exam);
        System.out.println(result ? "Đã cập nhật." : "Thất bại.");
    }

    private void deleteExam() {
        System.out.print("Exam ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean result = controller.deleteExam(id);
        System.out.println(result ? "Đã xóa kỳ thi." : "Thất bại.");
    }

    private void viewExamsByClass() {
        System.out.print("Class ID: ");
        String classId = scanner.nextLine();
        List<Exam> exams = controller.getExamsByClass(classId);
        exams.forEach(e -> System.out.printf("ID: %d | Subject: %s | Date: %s%n",
                e.getId(), e.getSubjectId(), e.getExamDate()));
    }

    private void viewExamResults() {
        System.out.print("Exam ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(controller.getExamResults(id));
    }
}
