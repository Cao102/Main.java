package View;

import Model.Score;
import java.util.List;

public class ViewScore implements ObjectView<Score>{
    private final Input input = new Input();

    public int menuObject() {
        System.out.print("""
        
        ╔════════════════════════════════════════╗
        ║              QUẢN LÝ ĐIỂM              ║
        ╠════════════════════════════════════════╣
        ║ 1.  Thêm Điểm                          ║
        ║ 2.  Hiển Thị Danh Sách Điểm            ║
        ║ 3.  Chỉnh Sửa Điểm                     ║
        ║ 4.  Xóa Điểm                           ║
        ║ 5.  Quay lại giao diện                 ║
        ╚════════════════════════════════════════╝
        """);
        return input.inputInt("Nhập lựa chọn");
    }

    public Score addObject() {
        System.out.println("\nNhập thông tin điểm:");
        int student_id = input.inputInt("Nhập Id sinh viên");
        int course_id = input.inputInt("Nhập ID khóa học");
        float sroce = input.inputFloat("Nhập điểm");
        return new Score(student_id, course_id, sroce);
    }

    public void getAllObject(List<Score> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("Không có điểm nào.\n");
            return;
        }
        System.out.print("""
        
        ╔═════════════════════════════════════════════════════╗
        ║                 DANH SÁCH ĐƠN ĐIỂM                  ║
        ╠════════════╦═══════════════╦══════════════╦═════════╣
        ║   ID ĐƠN   ║ Tên Sinh Viên ║ Tên Khóa Học ║  ĐIỂM   ║
        ╠════════════╬═══════════════╬══════════════╬═════════╣
        """);

        for (Score object : objectList) {
            System.out.println(object);
        }
        System.out.println("╚════════════╩═══════════════╩══════════════╩═════════╝");
    }

    public Score updateObject() {
        System.out.println("\nCập nhật thông tin điểm:");
        int id = input.inputInt("Nhập id điểm cần chỉnh sửa");
        int student_id = input.inputInt("Nhập id sinh viên mới");
        int course_id = input.inputInt("Nhập id khóa học mới: ");
        float score = input.inputFloat("Nhập điểm mới");
        return new Score(id, student_id, course_id, score);
    }

    public int deleteObject() {
        return input.inputInt("\nNhập ID đơn điểm cần xóa");
    }

}
