//package View;
//
//import Model.Subject;
//
//import java.util.List;
//
//public class ViewSubject {
//    private final Input input = new Input();
//
//    public int menuSubject() {
//        System.out.print("""
//
//        ╔════════════════════════════════════════════════════════╗
//        ║                  QUẢN LÝ MÔN HỌC                       ║
//        ╠════════════════════════════════════════════════════════╣
//        ║ 1. Thêm môn học                                        ║
//        ║ 2. Cập nhật môn học                                    ║
//        ║ 3. Xóa môn học                                         ║
//        ║ 4. Hiển thị danh sách môn học                          ║
//        ║ 5. Xem thông tin môn học theo ID                       ║
//        ║ 6. Xem danh sách môn học theo giảng viên               ║
//        ║ 7. Quay lại                                            ║
//        ╚════════════════════════════════════════════════════════╝
//        """);
//        return input.inputInt("Nhập lựa chọn của bạn");
//    }
//
//    public String inputSubjectId() {
//        return input.inputString("Nhập mã môn học");
//    }
//
//    public String inputSubjectName() {
//        return input.inputString("Nhập tên môn học");
//    }
//
//    public String inputSubjectDescription() {
//        return input.inputString("Nhập mô tả môn học");
//    }
//
//    public String inputTeacherId() {
//        return input.inputString("Nhập mã giảng viên");
//    }
//
//    public void displaySubjects(List<Subject> subjects) {
//        if (subjects.isEmpty()) {
//            System.out.println("Không có môn học nào.");
//            return;
//        }
//
//        System.out.print("""
//        ╔══════╦════════╦══════════════════════════════════════╦══════════════════════════════════════════════╗
//        ║ STT  ║  ID    ║           Tên môn học                ║                Mô tả                         ║
//        ╠══════╬════════╬══════════════════════════════════════╬══════════════════════════════════════════════╣
//        """);
//
//        int stt = 1;
//        for (Subject subject : subjects) {
//            System.out.println(String.format("║ %-4d ║ %-6s ║ %-34s ║ %-42s ║",
//                    stt++, subject.getSubjectId(), subject.getName(), subject.getDescription()));
//        }
//        System.out.println("""
//        ╚══════╩════════╩══════════════════════════════════════╩══════════════════════════════════════════════╝
//        """);
//    }
//
//    public void displaySubject(Subject subject) {
//        System.out.println("""
//        ╔══════════════════════════════════════════╗
//        ║           THÔNG TIN MÔN HỌC              ║
//        ╠══════════════════════════════════════════╣
//        """);
//        System.out.println("Mã môn học    : " + subject.getSubjectId());
//        System.out.println("Tên môn học   : " + subject.getName());
//        System.out.println("Mô tả         : " + (subject.getDescription() != null ? subject.getDescription() : "Không có mô tả"));
//        System.out.println("""
//        ╚══════════════════════════════════════════╝
//        """);
//    }
//
//    public void notifySubjectAdded() {
//        System.out.println(" Môn học đã được thêm thành công!");
//    }
//
//    public void notifySubjectUpdated() {
//        System.out.println(" Môn học đã được cập nhật thành công!");
//    }
//
//    public void notifySubjectDeleted() {
//        System.out.println(" Môn học đã được xóa thành công!");
//    }
//
//    public void showSubjectNotExist() {
//        System.out.println(" Không tìm thấy môn học với ID đã nhập.");
//    }
//
//    public void showNoSubjectsForTeacher() {
//        System.out.println(" Không có môn học nào cho giảng viên này.");
//    }
//
//    public boolean confirmUpdateSubject() {
//        String choice = input.inputString("Môn học đã tồn tại. Bạn có muốn cập nhật không? (Y/N)");
//        return choice.equalsIgnoreCase("Y");
//    }
//
//    public void errorChoose() {
//        System.out.println(" Lựa chọn không hợp lệ! Vui lòng nhập lại.");
//    }
//}
package View;

import Model.Subject;

import java.util.List;

public class ViewSubject {
    private final Input input = new Input();

    public int menuSubject() {
        System.out.print("""
                
        ╔════════════════════════════════════════════════════════╗
        ║                  QUẢN LÝ MÔN HỌC                       ║
        ╠════════════════════════════════════════════════════════╣
        ║ 1. Thêm môn học                                        ║
        ║ 2. Cập nhật môn học                                    ║
        ║ 3. Xóa môn học                                         ║
        ║ 4. Hiển thị danh sách môn học                          ║
        ║ 5. Xem thông tin môn học theo ID                       ║
        ║ 6. Xem danh sách môn học theo giảng viên               ║
        ║ 7. Quay lại                                            ║
        ╚════════════════════════════════════════════════════════╝
        """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public String inputSubjectId() {
        return input.inputString("Nhập mã môn học (VD: SUB001)");
    }

    public String inputSubjectName() {
        return input.inputString("Nhập tên môn học");
    }

    public String inputSubjectDescription() {
        return input.inputString("Nhập mô tả môn học");
    }

    public String inputTeacherId() {
        return input.inputString("Nhập mã giảng viên (VD:T004) :");
    }

    public void displaySubjects(List<Subject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("Không có môn học nào.");
            return;
        }

        System.out.println("""
        ╔══════╦════════╦══════════════════════════════════════╦══════════════════════════════════════════════╗
        ║ STT  ║  ID    ║           Tên môn học                ║                Mô tả                         ║
        ╠══════╬════════╬══════════════════════════════════════╬══════════════════════════════════════════════╣""");

        int stt = 1;
        for (Subject subject : subjects) {
            System.out.printf("║ %-4d ║ %-6s ║ %-38s ║ %-40s ║%n",
                    stt++,
                    subject.getSubjectId(),
                    truncate(subject.getName(), 38),
                    truncate(subject.getDescription(), 42)
            );
        }

        System.out.println("╚══════╩════════╩══════════════════════════════════════╩══════════════════════════════════════════════╝");
    }

    // Hàm rút gọn chuỗi nếu dài quá để không làm lệch bảng
    private String truncate(String value, int maxLength) {
        if (value == null) return "";
        return value.length() <= maxLength ? value : value.substring(0, maxLength - 3) + "...";
    }

    public void displaySubject(Subject subject) {
        System.out.println("""
        ╔══════════════════════════════════════════╗
        ║           THÔNG TIN MÔN HỌC              ║
        ╠══════════════════════════════════════════╣
        """);
        System.out.println("Mã môn học    : " + subject.getSubjectId());
        System.out.println("Tên môn học   : " + subject.getName());
        System.out.println("Mô tả         : " + (subject.getDescription() != null ? subject.getDescription() : "Không có mô tả"));
        System.out.println("""
        ╚══════════════════════════════════════════╝
        """);
    }

    public void notifySubjectAdded() {
        System.out.println("Môn học đã được thêm thành công!");
    }

    public void notifySubjectUpdated() {
        System.out.println("Môn học đã được cập nhật thành công!");
    }

    public void notifySubjectDeleted() {
        System.out.println("Môn học đã được xóa thành công!");
    }

    public void showSubjectNotExist() {
        System.out.println("Không tìm thấy môn học với ID đã nhập.");
    }

    public void showNoSubjectsForTeacher() {
        System.out.println("Không có môn học nào cho giảng viên này.");
    }

    public boolean confirmUpdateSubject() {
        String choice = input.inputString("Môn học đã tồn tại. Bạn có muốn cập nhật không? (Y/N)");
        return choice.equalsIgnoreCase("Y");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }
}
