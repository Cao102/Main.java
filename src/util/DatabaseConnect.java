package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
    public static Connection getConnection() {
        try {
            String URL = "jdbc:mysql://localhost:3306/StudentManagementSystem";
            String USER = "root";
            String PASSWORD = "root"; // Đổi mật khẩu theo database của bạn
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kết nối CSDL: " + e.getMessage());
        }
    }
}
