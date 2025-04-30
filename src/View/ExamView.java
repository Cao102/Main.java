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
                case 6 -> {
                    return;
                }
                default -> System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }

    private void scheduleExam() {
        String classroomId;
        String subjectId;

        while (true) {
            System.out.print("Nhập ID lớp học: ");
            classroomId = scanner.nextLine();
            if (controller.checkExits(classroomId, "Classrooms", "classroom_id")) {
                break;
            }
            System.out.println("Lớp học chưa tồn tại.");
        }

        while (true) {
            System.out.print("Nhập ID môn học: ");
            subjectId = scanner.nextLine();
            if (controller.checkExits(subjectId, "Subjects", "subject_id")) {
                break;
            }
            System.out.println("Môn học chưa tồn tại.");
        }

        LocalDateTime examDate = inputDateTime("Nhập ngày kiểm tra");
        Exam exam = new Exam(classroomId, subjectId, examDate);
        boolean result = controller.scheduleExam(exam);
        System.out.println(result ? "Đã lập lịch thi." : "Lập lịch thi thất bại.");
    }

    private void updateExam() {
        int id;
        String idStr;

        while (true) {
            System.out.print("Nhập ID kỳ thi: ");
            idStr = scanner.nextLine();
            if (controller.checkExits(idStr, "Exams", "id")) {
                id = Integer.parseInt(idStr);
                break;
            }
            System.out.println("Kỳ thi không tồn tại.");
        }

        String classroomId;
        String subjectId;

        while (true) {
            System.out.print("Nhập ID lớp học: ");
            classroomId = scanner.nextLine();
            if (controller.checkExits(classroomId, "Classrooms", "classroom_id")) {
                break;
            }
            System.out.println("Lớp học chưa tồn tại.");
        }

        while (true) {
            System.out.print("Nhập ID môn học: ");
            subjectId = scanner.nextLine();
            if (controller.checkExits(subjectId, "Subjects", "subject_id")) {
                break;
            }
            System.out.println("Môn học chưa tồn tại.");
        }

        LocalDateTime examDate = inputDateTime("Nhập ngày thi");
        Exam exam = new Exam(id, classroomId, subjectId, examDate);
        boolean result = controller.updateExam(exam);
        System.out.println(result ? "Đã cập nhật." : "Cập nhật thất bại.");
    }

    private void deleteExam() {
        String idStr;
        int id;

        while (true) {
            System.out.print("Nhập ID kỳ thi: ");
            idStr = scanner.nextLine();
            if (controller.checkExits(idStr, "Exams", "id")) {
                id = Integer.parseInt(idStr);
                break;
            }
            System.out.println("Kỳ thi không tồn tại.");
        }

        boolean result = controller.deleteExam(id);
        System.out.println(result ? "Đã xóa kỳ thi." : "Xóa thất bại.");
    }

    private void viewExamsByClass() {
        String classroomId;
        while (true) {
            System.out.print("Nhập ID lớp học: ");
            classroomId = scanner.nextLine();
            if (controller.checkExits(classroomId, "Classrooms", "classroom_id")) {
                break;
            }
            System.out.println("Lớp học chưa tồn tại.");
        }

        List<Exam> exams = controller.getExamsByClass(classroomId);
        if (exams.isEmpty()) {
            System.out.println("Không có kỳ thi nào được lên lịch cho lớp này.");
        } else {
            exams.forEach(e -> System.out.printf("ID: %d | Môn: %s | Ngày thi: %s%n",
                    e.getId(), e.getSubjectId(), e.getExamDate().format(dateTimeFormatter)));
        }
    }

    private void viewExamResults() {
        String idStr;
        int id;

        while (true) {
            System.out.print("Nhập ID kỳ thi: ");
            idStr = scanner.nextLine();
            if (controller.checkExits(idStr, "Exams", "id")) {
                id = Integer.parseInt(idStr);
                break;
            }
            System.out.println("Kỳ thi không tồn tại.");
        }

        System.out.println(controller.getExamResults(id));
    }

    private LocalDateTime inputDateTime(String message) {
        while (true) {
            System.out.print(message + " (yyyy-MM-dd HH:mm): ");
            String dateTimeStr = scanner.nextLine();
            try {
                return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày giờ không hợp lệ! Vui lòng nhập đúng định dạng yyyy-MM-dd HH:mm");
            }
        }
    }
}
