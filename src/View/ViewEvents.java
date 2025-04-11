package View;

import Model.Events;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewEvents {
    private final Input input = new Input();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int menuEvents() {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║          QUẢN LÝ SỰ KIỆN TRƯỜNG        ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm sự kiện                        ║
            ║ 2. Cập nhật sự kiện                    ║
            ║ 3. Xóa sự kiện                         ║
            ║ 4. Hiển thị danh sách sự kiện          ║
            ║ 5. Tìm sự kiện theo ngày               ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }


    public String inputEventName() {
        return input.inputString("Nhập tên sự kiện");
    }

    public LocalDateTime inputEventDateTime() {
        while (true) {
            String dateTimeStr = input.inputString("Nhập ngày và giờ sự kiện (yyyy-MM-dd HH:mm)");
            try {
                return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
            } catch (Exception e) {
                System.out.println("Ngày giờ không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd HH:mm");
            }
        }
    }
    public LocalDate inputEventDate() {
        while (true) {
            String dateStr = input.inputString("Nhập ngày sự kiện (yyyy-MM-dd)");
            try {
                return LocalDate.parse(dateStr);
            } catch (Exception e) {
                System.out.println("Ngày không hợp lệ! Vui lòng nhập theo định dạng yyyy-MM-dd");
            }
        }
    }
    public String inputLocation() {
        return input.inputString("Nhập địa điểm tổ chức sự kiện");
    }

    public String inputEventId() {
        return input.inputString("Nhập ID sự kiện");
    }

    public void displayEvents(List<Events> events) {
        if (events.isEmpty()) {
            System.out.println("Không có sự kiện nào.");
            return;
        }

        System.out.print("""
        ╔══════╦══════╦════════════════════════════════╦════════════════════╦══════════════════════╗
        ║ STT  ║  ID  ║         Tên sự kiện            ║   Ngày và giờ      ║   Địa điểm tổ chức   ║
        ╠══════╬══════╬════════════════════════════════╬════════════════════╬══════════════════════╣
        """);

        int stt = 1;
        for (Events event : events) {
            System.out.println(String.format("║ %-4d ", stt++) + event);
        }
        System.out.println("""
        ╚══════╩══════╩════════════════════════════════╩════════════════════╩══════════════════════╝
        """);

    }

    public void notifyEventAdded() {
        System.out.println("Sự kiện đã được thêm thành công!");
    }

    public void notifyEventUpdated() {
        System.out.println("Sự kiện đã được cập nhật thành công!");
    }

    public void notifyEventDeleted() {
        System.out.println("Sự kiện đã được xóa thành công!");
    }

    public void showEventNotExist() {
        System.out.println("Không tìm thấy sự kiện với ID đã nhập.");
    }

    public void showNoEventsFound() {
        System.out.println("Không tìm thấy sự kiện với Ngày đã nhập.");
    }
    public boolean confirmUpdateEvent() {
        System.out.print("Sự kiện đã tồn tại. Bạn có muốn cập nhật thông tin sự kiện không? (Y/N) ");
        String choice = input.inputString("");
        return choice.equalsIgnoreCase("Y");
    }
}
