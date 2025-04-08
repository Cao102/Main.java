package View;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import Model.Student;

public class ViewStudent{
    private final Input input = new Input();
    public void checkEmpty(String message){
        System.out.println("Vui lòng không để " + message + " trống");
    }
    private Date validateDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false); // Không cho phép nhập ngày sai

        try {
            java.util.Date utilDate = sdf.parse(dateStr); // Chuyển chuỗi thành java.util.Date
            return new java.sql.Date(utilDate.getTime()); // Chuyển thành java.sql.Date để lưu vào SQL
        } catch (ParseException e) {
            return null; // Nếu sai định dạng, trả về null
        }
    }
    public int menuObject() {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           QUẢN LÝ SINH VIÊN            ║
            ╠════════════════════════════════════════╣
            ║ 1. Thêm sinh viên                      ║
            ║ 2. Hiển thị thông tin sinh viên        ║
            ║ 3. Chỉnh sửa thông tin sinh viên       ║
            ║ 4. Xóa thông tin sinh viên             ║
            ║ 5. Tìm kiếm thông tin sinh viên        ║
            ║ 6. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }
    public void errorChoose(){
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }
    public String getID(){
        return input.inputString("Nhập ID SV");
    }
    public String getName(){
        return input.inputString("Nhập tên SV");
    }
    public Date getDob(){
        Date dob;
        while(true){
            String dobStr = input.inputString("Nhập ngày sinh (yyyy-MM-dd)");
            dob = validateDate(dobStr);
            if (dobStr.isEmpty()){
                break;
            }
            if (dob == null) {
                System.out.println("Ngày sinh không hợp lệ! Vui lòng nhập lại theo định dạng yyyy-MM-dd.");
                continue;
            }
            break;
        }
        return dob;
    }
    public String getGender(){
        String gender;
        while(true){
            gender = input.inputString("Nhập giới tính SV");
            if (gender.isEmpty()) break;
            String checkGender = gender.toLowerCase();
            if(!(checkGender.equals("male") || checkGender.equals("female") || checkGender.equals("other"))){
                System.out.println("Nhập lại giới tính với đầu vào là Male, Female, Other");
                continue;
            }
            break;
        }
        return gender;
    }
    public String getEmail(){
        String email;
        while (true) {
            email = input.inputString("Nhập email sinh viên (@gmail.com)");
            if(email.isEmpty()) break;
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
            phone = input.inputString("Nhập SĐT sinh viên (10 số)");
            if (phone.isEmpty()) return "";
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
            address = input.inputString("Nhập địa chị SV");
            if(address.length() >= 255){
                System.out.println("Vui lòng không nhấp quá 255 ký tự");
                continue;
            }
            break;
        }
        return address;
    }
    public void addObject(){
        System.out.println("Nhập thông tin mới (hoặc Enter để quay lại):");
    }
    public void updateObject(){
        System.out.println("""
                                Nhập thông tin cần chỉnh sửa (hoặc Enter để quay lại)
                                Sau khi nhập ID thì Enter để giữ lại thông tin cũ)
                                """);
    }
    public void deleteObject(){
        System.out.println("Nhập ID cần xoá (hoặc Enter để quay lại)");
    }
    public void getAllObject(List<Student> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("\nKhông có sinh viên nào.\n");
            return;
        }
        System.out.print("""
                ╔═════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                ║                                                   DANH SÁCH SINH VIÊN                                           ║
                ╠══════╦════════════════╦══════════════╦═══════════╦═══════════════════════════╦══════════════╦═══════════════════╣
                ║  ID  ║      Tên       ║   Ngày sinh  ║ Giới Tính ║           Email           ║      SĐT     ║      Địa chỉ      ║
                ╠══════╬════════════════╬══════════════╬═══════════╬═══════════════════════════╬══════════════╬═══════════════════╣
                """);
        for (Student object : objectList) {
            System.out.println(object);
        }
        System.out.println("""
                ╚══════╩════════════════╩══════════════╩═══════════╩═══════════════════════════╩══════════════╩═══════════════════╝
                """);
    }

    public int viewSearch () {
        System.out.print("""
            
            ╔════════════════════════════════════════╗
            ║           Tìm Kiếm Thông Tin           ║
            ╠════════════════════════════════════════╣
            ║ 1. Tìm kiếm theo MSV                   ║
            ║ 2. Tìm kiếm theo Tên SV                ║
            ║ 3. Tìm kiếm theo ngày sinh             ║
            ║ 4. Tìm kiếm theo Giới tính             ║
            ║ 5. Tìm kiếm theo email                 ║
            ║ 6. Tìm kiếm theo SĐT                   ║
            ║ 7. Tìm kiếm theo Địa chỉ               ║
            ║ 8. Quay lại                            ║
            ╚════════════════════════════════════════╝
            """);
        return input.inputInt("Nhập lựa chọn");
    }
    public void checkID(){
        System.out.println("Không tồn tại SV có ID đã nhập");
    }
    public void checkEmail(){
        System.out.println("Email đã tồn tại. Vui lòng nhập lại");
    }
}
