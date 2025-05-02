package View;

import Model.Library;
import Model.BorrowedBook;
import util.TableUtils;

import java.util.List;

public class ViewLibraryManagement {
    private final Input input = new Input();
    private boolean checkEmpty(String s) {
        if (s.isEmpty()) {
            System.out.println("Vui lòng không để trống");
            return true;
        }
        return false;
    }
    public int menuLibraryManagement() {
        System.out.print("""
        
        ╔════════════════════════════════════════════════╗
        ║               QUẢN LÝ THƯ VIỆN                 ║
        ╠════════════════════════════════════════════════╣
        ║ 1. Thêm sách mới vào thư viện                  ║
        ║ 2. Hiển thị danh sách sách                     ║
        ║ 3. Cập nhật thông tin sách                     ║
        ║ 4. Xóa sách khỏi thư viện                      ║
        ║ 5. Tìm kiếm sách theo từ khóa                  ║
        ║ 6. Thêm phiếu mượn sách                        ║
        ║ 7. Trả sách                                    ║
        ║ 8. Hiển thị danh sách mượn sách                ║
        ║ 9. Tìm kiếm thông tin mượn theo ID sinh viên   ║
        ║ 10. Hiển thị danh sách sách khả dụng           ║
        ║ 0. Quay lại                                    ║
        ╚════════════════════════════════════════════════╝
        """);
        return input.inputInt("Nhập lựa chọn của bạn");
    }

    public String inputBookId() {
        while (true){
            String bookId = input.inputString("Nhập mã sách");
            if (checkEmpty(bookId)){
                continue;
            }
            if(bookId.length()<=10){
                return bookId;
            }
            else{
                System.out.println("Vui lòng không nhập Id quá 10 ký tự");
            }
        }
    }

    public Library inputBookDetails(String bookId) {
        String bookName;
        while (true){
            bookName = input.inputString("Nhập tên sách");
            if (checkEmpty(bookName)){
                continue;
            }
            if(bookName.length()<=100){
                break;
            }
            else{
                System.out.println("Vui lòng không nhập tên sách quá 100 ký tự");
            }
        }
        String bookAuthor;
        while (true){
            bookAuthor = input.inputString("Nhập tên tác giả");
            if (checkEmpty(bookAuthor)){
                continue;
            }
            if(bookAuthor.length()<=100){
                break;
            }
            else{
                System.out.println("Vui lòng không nhập tên tác giả quá 100 ký tự");
            }
        }
        int quantity;
        while (true) {
            String line = input.inputString("Nhập số lượng sách");
            if (checkEmpty(line)) {
                continue;
            }
            quantity = Integer.parseInt(line);
            if (quantity <= 0) {
                System.out.println("Số lượng sách phải lớn hơn không. Vui lòng nhập lại.");
            } else {
                break;
            }
        }

        return new Library(bookId, bookName, bookAuthor, quantity);
    }

    public String inputStudentId() {
        while (true){
            String studentId = input.inputString("Nhập ID sinh viên");
            if(studentId.length()<=10){
                return studentId;
            }
            else{
                System.out.println("Vui lòng không nhập Id quá 10 ký tự");
            }
        }
    }
    public String inputKeyWord() {
        return input.inputString("Nhập từ khóa");
    }
    public void displayAllBooks(List<Library> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("Không có sách nào trong thư viện.");
            return;
        }
        String[] headers = {"Mã Sách", "Tên Sách", "Tác Giả", "Số Lượng"};
        System.out.println("\nDANH SÁCH SÁCH TRONG THƯ VIỆN:");
        TableUtils.printTable(objectList, headers);
//        System.out.print("""
//            ╔══════╦════════════╦═══════════════════════════╦══════════════════════╦══════════╗
//            ║ STT  ║  Mã sách   ║         Tên sách          ║        Tác giả       ║ Số lượng ║
//            ╠══════╬════════════╬═══════════════════════════╬══════════════════════╬══════════╣
//            """);
//
//        int stt = 1;
//        for (Library book : bookList) {
//            System.out.println(String.format("║ %-4d ", stt++) + book);
//        }
//        System.out.println("""
//            ╚══════╩════════════╩═══════════════════════════╩══════════════════════╩══════════╝
//            """);
    }
    public void displayAvailableBooks(List<Library> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("Không có sách khả dụng trong thư viện.");
            return;
        }
        String[] headers = {"Mã Sách", "Tên Sách", "Tác Giả", "Số Lượng"};
        System.out.println("\nDANH SÁCH SÁCH KHẢ DỤNG:");
        TableUtils.printTable(objectList, headers);
//        System.out.print("""
//            ╔══════╦════════════╦═══════════════════════════╦══════════════════════╦══════════╗
//            ║ STT  ║  Mã sách   ║         Tên sách          ║        Tác giả       ║ Số lượng ║
//            ╠══════╬════════════╬═══════════════════════════╬══════════════════════╬══════════╣
//            """);
//
//        int stt = 1;
//        for (Library book : availableBooks) {
//            System.out.println(String.format("║ %-4d ", stt++) + book);
//        }
//
//        System.out.println("""
//            ╚══════╩════════════╩═══════════════════════════╩══════════════════════╩══════════╝
//            """);
    }

    public void displayBorrowedBooks(List<BorrowedBook> objectList) {
        if (objectList.isEmpty()) {
            System.out.println("Không có sách nào đang được mượn hoặc đã trả");
            return;
        }
        String[] headers = {"MSV", "Mã Sách", "Ngày Mượn", "Ngày Trả"};
        System.out.println("\nDANH SÁCH MƯỢN:");
        TableUtils.printTable(objectList, headers);
//        System.out.print("""
//            ╔══════╦════════════════╦════════════╦══════════════╦══════════════╗
//            ║ STT  ║  Mã sinh viên  ║  Mã sách   ║  Ngày mượn   ║   Ngày trả   ║
//            ╠══════╬════════════════╬════════════╬══════════════╬══════════════╣
//            """);
//
//        int stt = 1;
//        for (BorrowedBook b : borrowedBooks) {
//            System.out.println(String.format("║ %-4d ", stt++) + b);
//        }
//
//        System.out.println("""
//            ╚══════╩════════════════╩════════════╩══════════════╩══════════════╝
//            """);
    }
    public void showBookByKeyWord(List<Library> objectList) {
        String[] headers = {"Mã Sách", "Tên Sách", "Tác Giả", "Số Lượng"};
        System.out.println("\nDANH SÁCH SÁCH:");
        TableUtils.printTable(objectList, headers);
//        System.out.println("Kết quả tìm kiếm sách:");
//        System.out.print("""
//            ╔══════╦════════════╦═══════════════════════════╦══════════════════════╦══════════╗
//            ║ STT  ║  Mã sách   ║         Tên sách          ║        Tác giả       ║ Số lượng ║
//            ╠══════╬════════════╬═══════════════════════════╬══════════════════════╬══════════╣
//            """);
//        int stt = 1;
//        for (Library book : books) {
//            System.out.println(String.format("║ %-4d ", stt++) + book);
//        }
//        System.out.println("""
//            ╚══════╩════════════╩═══════════════════════════╩══════════════════════╩══════════╝
//            """);
    }

    public void showBorrowedBooksByStudentId(List<BorrowedBook> objectList, String studentId) {
        if (objectList.isEmpty()) {
            System.out.println("Sinh viên có mã " + studentId + " không mượn quyển sách nào");
            return;
        }
        String[] headers = {"Mã SV", "Mã Sách", "Ngày Mượn", "Ngày Trả"};
        System.out.printf("\nDANH SÁCH SÁCH MÀ SV CÓ MÃ SV: %s:\n", studentId);
        TableUtils.printTable(objectList, headers);
//        System.out.println("Danh sách sách đã mượn của sinh viên có mã: " + studentId);
//        System.out.print("""
//            ╔══════╦════════════════╦════════════╦══════════════╦══════════════╗
//            ║ STT  ║  Mã sinh viên  ║  Mã sách   ║  Ngày mượn   ║   Ngày trả   ║
//            ╠══════╬════════════════╬════════════╬══════════════╬══════════════╣
//            """);
//        int stt = 1;
//        for (BorrowedBook b : borrowedBooks) {
//            System.out.println(String.format("║ %-4d ", stt++) + b);
//        }
//
//        System.out.println("""
//            ╚══════╩════════════════╩════════════╩══════════════╩══════════════╝
//            """);
    }
    public void errorChoose() {
        System.out.println("Lựa chọn không hợp lệ! Vui lòng nhập lại.");
    }

    public void notifyBookAdded() {
        System.out.println("Thêm sách thành công!");
    }

    public void notifyBookUpdated() {
        System.out.println("Cập nhật sách thành công!");
    }

    public void notifyBookDeleted() {
        System.out.println("Xóa sách thành công!");
    }

    public void showKeyWordNotFound() {
        System.out.println("Không tìm thấy sách với từ khóa đã nhập.");
    }
    public void showStudentNotExist(){
        System.out.println("Sinh viên không tồn tại trong hệ thống. Vui lòng kiểm tra lại ID");
    }
    public void showBookNotExist() {
        System.out.println("Sách không tồn tại trong hệ thống. Vui lòng kiểm tra lại ID.");
    }
    public boolean confirmUpdateBook() {
        System.out.print("Sách đã tồn tại. Bạn có muốn cập nhật thông tin sách không? (Y/N)");
        String choice = input.inputString("");
        return choice.equalsIgnoreCase("Y");
    }
    public void notifyOutOfStock(){
        System.out.println("Số lượng sách đã hết");
    }
    public void notifyBorrow(){
        System.out.println("Mượn sách thành công");
    }
    public void notifyReturn(){
        System.out.println("Trả sách thành công");
    }
    public void showBookNotBorrowed() {
        System.out.println("Sinh viên không có mượn sách này hoặc đã trả trước đó.");
    }
    public void notifyAlreadyBorrowed() {
        System.out.println("Sinh viên đã mượn cuốn sách này và chưa trả!");
    }

}
