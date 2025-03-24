package DAO;

import Model.Course;
import util.DatabaseConnect;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements DAO<Course>{
    // Thêm khóa học
    public void add(Course course) {
        String sql = "INSERT INTO courses (course_name, credits) VALUES (?, ?);";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm khoá học: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Lấy danh sách tất cả khóa học
    public List<Course> getAll() {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT * FROM courses;";
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("course_id");
                String nameCourse = resultSet.getString("course_name");
                int credit = resultSet.getInt("credits");
                courseList.add(new Course(id, nameCourse, credit));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách khoá học: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return courseList;
    }

    // Cập nhật thông tin khóa học
    public void update(Course course) {
        String sql = "UPDATE courses SET course_name = ?, credits = ? WHERE course_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, course.getName());
            statement.setInt(2, course.getCredit());
            statement.setInt(3, course.getId()); // Sửa lỗi index sai
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi chỉnh sửa thông tin khoá học: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Xóa khóa học
    public void delete(int courseId) {
        String sql = "DELETE FROM courses WHERE course_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, courseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi xoá khoá học: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // Lấy khóa học theo ID
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM courses WHERE course_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nameCourse = resultSet.getString("course_name");
                    int credit = resultSet.getInt("credits");
                    return new Course(id, nameCourse, credit);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy khoá học theo ID: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }
}
