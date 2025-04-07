package View;

import Model.Teacher;

import java.math.BigDecimal;
import java.util.List;

public class ViewTeacher{
    private final Input input = new Input();
    private boolean checkEmpty(String s, String message){
        if(s.isEmpty()){
            System.out.println("Vui lòng không để " + message + " trống");
            return true;
        }
        return false;
    }
    public String getID(){
        String teacher_id;
        while (true){
            teacher_id = input.inputString("Nhập ID GV");
            if(checkEmpty(teacher_id, "Mã ID")){
                System.out.println("Nhập lại ID chỉ bao gồm số");
                continue;
            }
            break;
        }
        return teacher_id;
    }
    public String getName(){
        String name;
        while(true){
            name = input.inputString("Nhập tên GV");
            if(name.isEmpty()){
                System.out.println("Không được để trên trắng");
                continue;
            }
            if(name.length() >= 100){
                System.out.println("Tên không được quá 100 ký tự");
                continue;
            }
            break;
        }
        return name;
    }
    public String getEmail(){
        String email;
        while (true) {
            email = input.inputString("Nhập email GV (@gmail.com)");
            if(checkEmpty(email, "email")){
                continue;
            }
            if (!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
                System.out.println("Email không hợp lệ! Vui lòng nhập lại với đuôi @gmail.com.");
                continue;
            }
            break;
        }
        return email;
    }
    public String getPhone(){
        String phone;
        while (true) {
            phone = input.inputString("Nhập SĐT GV (10 số)");
            if(checkEmpty(phone, "phone")){
                continue;
            }
            if (!phone.matches("^\\d{10}$")) {
                System.out.println("Số điện thoại không hợp lệ! Vui lòng nhập lại (10 chữ số).");
                continue;
            }
            break;
        }
        return phone;
    }
    public String getAddress(){
        String address;
        while (true){
            address = input.inputString("Nhập địa chị GV");
            if(checkEmpty(address, "address")){
                continue;
            }
            if(address.length() >= 255){
                System.out.println("Vui lòng không nhấp quá 255 ký tự");
                continue;
            }
            break;
        }
        return address;
    }
    public int getYOE (){
        int years_of_experience;
        while(true){
            String line = input.inputString("Nhập năm kinh nghiệm");
            if(!line.matches("\\d+")){
                System.out.println("Đầu vào chỉ là số");
                continue;
            }
            years_of_experience = Integer.parseInt(line);
            break;
        }
        return years_of_experience;
    }
    public BigDecimal getBaseSalary(){
        BigDecimal bigDecimal;
        while(true){
            String line = input.inputString("Nhập Mức Lương Cơ Bản Của GV");
            if(!line.matches("\\d++")){
                System.out.println("Nhập lại với Input chỉ bao gồm số");
                continue;
            }
            try {
                bigDecimal = new BigDecimal(line);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Giá trị quá lớn hoặc không hợp lệ, vui lòng nhập lại.");
            }
        }
        return bigDecimal;
    }
    public int menuObject(){
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ GIÁO VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm giáo viên                      ║
            ║ 2. Hiển thị thông tin giáo viên        ║
            ║ 3. Chỉnh sửa thông tin giáo viên       ║
            ║ 4. Xóa thông tin giáo viên             ║
            ║ 5. Tìm kiếm thông tin giáo viên        ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }
    public void addObject(){
        System.out.println("Nhập thông tin mới:");
    }
    public void updateObject(){
        System.out.println("Nhập thông tin cần chỉnh sửa:");
    }
    public void deleteObject(){
        System.out.println("Nhập ID cần xoá:");
    }
    public void getAllObject(List<Teacher> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nHiện không có khóa học nào.\n");
            return;
        }
        System.out.print("""
                      
                      ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                      ║                                                   DANH SÁCH GIÁO VIÊN                                                   ║
                      ╠══════╦════════════════════╦═════════════════════════╦════════════╦═══════════════════╦══════╦════════════╦══════════════╣
                      ║  ID  ║          TÊN       ║          EMAIL          ║    SĐT     ║       Địa chỉ     ║ SNCT ║  Lương CB  ║     Lương    ║
                      ╠══════╬════════════════════╬═════════════════════════╬════════════╬═══════════════════╬══════╬════════════╬══════════════╣
                      """);

        for (Teacher object : objectList) {
            System.out.println(object);
        }
        System.out.println("""
                      ╚══════╩════════════════════╩═════════════════════════╩════════════╩═══════════════════╩══════╩════════════╩══════════════╝
                      """);
    }

    public int viewSearch () {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           Tìm Kiếm Thông Tin           ║
            ╠════════════════════════════════════════╣
            ║ 1. Tìm kiếm theo MGV                   ║
            ║ 2. Tìm kiếm theo Tên GV                ║
            ║ 3. Tìm kiếm theo email                 ║
            ║ 4. Tìm kiếm theo SĐT                   ║
            ║ 5. Tìm kiếm theo Đc                    ║
            ║ 6. Tìm kiếm theo SNCT                  ║
            ║ 7. Tìm kiếm theo Lương CB              ║
            ║ 8. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn");
    }
    public void checkID(){
        System.out.println("ID chưa tồn lại. Vui lòng nhập lại");
    }
    public void checkEmail(){
        System.out.println("Email đã tồn tại. Vui lòng nhập lạ");
    }
}

