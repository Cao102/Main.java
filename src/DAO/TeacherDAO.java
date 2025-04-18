package DAO;

import Model.Teacher;
import util.DatabaseConnect;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    public void add(Teacher teacher) {
        String sql = "INSERT INTO teachers (teacher_id, name, email, phone, address, years_of_experience, base_salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teacher.getId());
            statement.setString(2, teacher.getName());
            statement.setString(3, teacher.getEmail());
            statement.setString(4, teacher.getPhone());
            statement.setString(5, teacher.getAddress());
            statement.setInt(6, teacher.getYearsOfExperience());
            statement.setBigDecimal(7, teacher.getBaseSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm giáo viên" + e.getMessage());
        }
    }

    public List<Teacher> getAll() {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "select * from teachers";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String teacher_id = resultSet.getString("teacher_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String address = resultSet.getString("address");
                int years_of_experience = resultSet.getInt("years_of_experience");
                BigDecimal base_salary = resultSet.getBigDecimal("base_salary");
                BigDecimal salary = resultSet.getBigDecimal("salary");
                teacherList.add(new Teacher(teacher_id, name, email, phone, address, years_of_experience, base_salary, salary));
            }
            return teacherList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi hiện thị thông tin giáo viên" + e.getMessage());
        }
    }

    public void update(Teacher object) {
        String sql = """
                UPDATE teachers
                SET name = ?, email = ?, phone = ?, address = ?, years_of_experience = ?, base_salary = ?\s
                WHERE teacher_id = ?;
               \s""";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, object.getName());
            statement.setString(2, object.getEmail());
            statement.setString(3, object.getPhone());
            statement.setString(4, object.getAddress());
            statement.setInt(5, object.getYearsOfExperience());
            statement.setBigDecimal(6, object.getBaseSalary());
            statement.setString(7, object.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi sửa thông tin giáo viên " + e.getMessage());
        }
    }

    public void delete(String id) {
        String sql = "DELETE FROM teachers WHERE teacher_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xoá giáo viên" + e.getMessage());
        }
    }

    public List<Teacher> search(String name_column, String attribute) {
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT * FROM Teachers WHERE " + name_column + " LIKE ?";

        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + attribute + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String teacher_id = resultSet.getString("teacher_id");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");
                    String address = resultSet.getString("address");
                    int years_of_experience = resultSet.getInt("years_of_experience");
                    BigDecimal base_salary = resultSet.getBigDecimal("base_salary");
                    BigDecimal salary = resultSet.getBigDecimal("salary");
                    teacherList.add(new Teacher(teacher_id, name, email, phone, address, years_of_experience, base_salary, salary));
                }
            }
            return teacherList;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất dữ liệu: " + e.getMessage());
        }
    }
}
