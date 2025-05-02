
package View;

import Model.Subject;
import util.TableUtils;

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
                ║ 0. Quay lại                                            ║
                ╚════════════════════════════════════════════════════════╝
                """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public String inputSubjectId() {
//        while (true) {
//            String subjectId = input.inputString("Nhập mã môn học (VD: SUB001)");
//            if (subjectId.startsWith("SUB")) {
//                return subjectId;
//            }
//            System.out.println("Lỗi! Mã môn học phải bắt đầu bằng 'SUB'. Vui lòng nhập lại");
//        }
        String Id;
        while(true){
            Id= input.inputString("Nhập mã môn học (VD: SUB001)");
            if(Id.length()>10 ){
                System.out.println("ID phải có độ dài nhỏ hơn 10 ! Hãy nhập lại");
                continue;
            }
            if(!Id.startsWith("SUB")){
                System.out.println("Lỗi! Mã môn học phải bắt đầu bằng 'SUB'. Vui lòng nhập lại");
                continue;
            }
            break;
        }
        return Id;
    }

    public String inputSubjectName() {
        String name;
        while(true){
            name= input.inputString("Nhập tên môn học");
            if(name.length()>100) {
                System.out.println("Tên môn học phải có độ dài nhỏ hơn 100 ! Hãy nhập lại");
                continue;
            }
            break;
        }
        //return input.inputString("Nhập tên môn học");
        return name;
    }

    public String inputSubjectDescription() {
        return input.inputString("Nhập mô tả môn học");
    }

    public String inputTeacherId() {
        return input.inputString("Nhập mã giảng viên (VD:T004)");
    }

    public void displaySubjects(List<Subject> subjects) {
        if (subjects.isEmpty()) {
            System.out.println("Không có môn học nào.");
            return;
        }

        String[] headers = {"Mã Môn Học", "Tên Môn Học", "Mô Tả"};
        System.out.println("\nDANH SÁCH MÔN HỌC:");
        TableUtils.printTable(subjects, headers);
    }

    public void displaySubject(Subject subject) {
        String name = subject.getName() != null ? subject.getName() : "Không có tên";
        String desc = subject.getDescription() != null ? subject.getDescription() : "Không có mô tả";

        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║               THÔNG TIN MÔN HỌC                    ║");
        System.out.println("╠════════════════════════════════════════════════════╣");
        System.out.printf("║ %-15s : %-33s║%n", "Mã môn học", subject.getSubjectId());
        System.out.printf("║ %-15s : %-33s║%n", "Tên môn học", name);
        System.out.printf("║ %-15s : %-33s║%n", "Mô tả", desc);
        System.out.println("╚════════════════════════════════════════════════════╝");
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
    public String inputSubjectNameWithDefault(String oldName) {
        String name = input.inputString("Nhập tên môn học [" + oldName + "]");
        return name.isEmpty() ? oldName : name;
    }

    public String inputSubjectDescriptionWithDefault(String oldDescription) {
        String description = input.inputString("Nhập mô tả môn học [" + oldDescription + "]");
        return description.isEmpty() ? oldDescription : description;
    }


    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }
}