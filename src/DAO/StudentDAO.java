package DAO;

import Model.Student;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void add(Student student) {
        String sql = "INSERT INTO students (student_id, name, dob, gender, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getId());
            statement.setString(2, student.getName());
            statement.setDate(3, student.getDob());
            statement.setString(4, student.getGender());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm sinh viên " + e.getMessage());
        }
    }

    public List<Student> getAll() {
        List<Student> studentsList = new ArrayList<>();
        String sql = "select * from students";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String student_id = resultSet.getString("student_id");
                String name = resultSet.getString("name");
                Date dob = resultSet.getDate("dob");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                studentsList.add(new Student(student_id, name, dob, gender, email, phone, address));
            }
            return studentsList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiển thị thông tin sinh viên" + e.getMessage());
        }
    }

    public void update(Student student) {
        String sql = """
                UPDATE students
                SET name = ?, dob = ?, gender = ?, email = ?, phone = ?, address = ?
                WHERE student_id = ?;""";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getName());
            statement.setDate(2, student.getDob());
            statement.setString(3, student.getGender());
            statement.setString(4, student.getEmail());
            statement.setString(5, student.getPhone());
            statement.setString(6, student.getAddress());
            statement.setString(7, student.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi sửa sinh viên " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM students WHERE student_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xoá sinh viên " + e.getMessage());
        }
    }

    public List<Student> search(String name_column, String attribute) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Students WHERE " + name_column + " LIKE ?";

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + attribute + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String student_id = resultSet.getString("student_id");
                    String name = resultSet.getString("name");
                    Date dob = resultSet.getDate("dob");
                    String gender = resultSet.getString("gender");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    studentList.add(new Student(student_id, name, dob, gender, email, phone, address));
                }
            }
            return studentList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất dữ liệu: " + e.getMessage());
        }
    }
}
