package DAO;

import Model.Library;
import Model.BorrowedBook;
import connectDatabase.DatabaseConnect;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagementDAO {

    // 1. Thêm sách mới vào thư viện
    public void addBook(Library book) {
        String sql = "INSERT INTO Library (book_id, book_name, author, quantity) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getBookId());
            statement.setString(2, book.getBookName());
            statement.setString(3, book.getAuthor());
            statement.setInt(4, book.getQuantity());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm sách: " + e.getMessage());
        }
    }

    // 2. Hiển thị tất cả sách
    public List<Library> getAllBooks() {
        List<Library> books = new ArrayList<>();
        String sql = "SELECT * FROM Library";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                books.add(new Library(
                        rs.getString("book_Id"),
                        rs.getString("book_Name"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy danh sách sách: " + e.getMessage());
        }
        return books;
    }

    // 3. Cập nhật sách
    public void updateBook(Library book) {
        String sql = "UPDATE Library SET book_name = ?, author = ?, quantity = ? WHERE book_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getBookName());
            statement.setString(2, book.getAuthor());
            statement.setInt(3, book.getQuantity());
            statement.setString(4, book.getBookId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật sách: " + e.getMessage());
        }
    }

    // 4. Xóa sách
    public void deleteBook(String bookId) {
        String sql = "DELETE FROM Library WHERE book_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa sách: " + e.getMessage());
        }
    }

    // 5. tìm kiếm theo tên
    public List<Library> searchByBookName(String keyword) {
        List<Library> result = new ArrayList<>();
        String sql = "SELECT * FROM Library WHERE book_name LIKE ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result.add(new Library(
                        rs.getString("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tìm sách: " + e.getMessage());
        }
        return result;
    }

    // 6. thêm phiếu mượn sách
    public void borrowBook(BorrowedBook book) {
        String sql = "INSERT INTO BorrowedBook (student_id, book_id, borrow_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getStudentId());
            stmt.setString(2, book.getBookId());
            stmt.setDate(3, Date.valueOf(book.getBorrowDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi mượn sách: " + e.getMessage());
        }
    }

    // 7. Trả sách
    public void returnBook(String studentId, String bookId) {
        String sql = "UPDATE BorrowedBook SET return_date = ? WHERE student_id = ? AND book_id = ? AND return_date IS NULL";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setString(2, studentId);
            stmt.setString(3, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi trả sách: " + e.getMessage());
        }
    }

    // 8. Hiển thị tất cả phiếu mượn
    public List<BorrowedBook> getAllBorrowedBooks() {
        List<BorrowedBook> list = new ArrayList<>();
        String sql = "SELECT * FROM BorrowedBook";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                BorrowedBook book = new BorrowedBook(
                        rs.getString("student_id"),
                        rs.getString("book_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
                );
                list.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy danh sách mượn: " + e.getMessage());
        }
        return list;
    }

    // 9. Tìm mượn theo ID sinh viên
    public List<BorrowedBook> findByStudentId(String studentId) {
        List<BorrowedBook> list = new ArrayList<>();
        String sql = "SELECT * FROM BorrowedBook WHERE student_id = ?";
        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                BorrowedBook book = new BorrowedBook(
                        rs.getString("student_id"),
                        rs.getString("book_id"),
                        rs.getDate("borrow_date").toLocalDate(),
                        rs.getDate("return_date") != null ? rs.getDate("return_date").toLocalDate() : null
                );
                list.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tìm mượn theo sinh viên: " + e.getMessage());
        }
        return list;
    }

    // 10. Hiển thị sách khả dụng
    public List<Library> getAvailableBooks() {
        List<Library> available = new ArrayList<>();
        String sql = "SELECT * FROM Library WHERE quantity > 0";
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                available.add(new Library(
                        rs.getString("book_id"),
                        rs.getString("book_name"),
                        rs.getString("author"),
                        rs.getInt("quantity")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi lấy sách khả dụng: " + e.getMessage());
        }
        return available;
    }

    // Giảm số lượng sách khi mượn
    public boolean decreaseQuantity(String bookId) {
        String sql = "UPDATE Library SET quantity = quantity - 1 WHERE book_id = ? AND quantity > 0";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookId);
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // true nếu có sách để giảm
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi giảm số lượng sách: " + e.getMessage());
        }
    }


    // Tăng số lượng sách khi trả
    public void increaseQuantity(String bookId) {
        String sql = "UPDATE Library SET quantity = quantity + 1 WHERE book_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tăng số lượng sách: " + e.getMessage());
        }
    }

    // Kiểm tra sự tồn tại của student_id trong bảng students
    public boolean isStudentExist(String studentId) {
        String sql = "SELECT COUNT(*) FROM students WHERE student_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sinh viên: " + e.getMessage());
        }
    }

    // Kiểm tra sự tồn tại của bookId trong bảng Library
    public boolean isBookIdExist(String bookId) {
        String sql = "SELECT COUNT(*) FROM Library WHERE book_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra mã sách: " + e.getMessage());
        }
    }

    // Kiểm tra sinh viên có mượn quyển sách đấy không
    public boolean isBookBorrowedByStudent(String studentId, String bookId) {
        String sql = "SELECT COUNT(*) FROM BorrowedBook WHERE student_id = ? AND book_id = ? AND return_date IS NULL";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, studentId);
            statement.setString(2, bookId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next() && resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra tình trạng mượn sách: " + e.getMessage());
        }
    }
}
