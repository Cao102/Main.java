package DAO;

import Model.Student;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {
    public void add(Student student){
        String sql = "INSERT INTO students (name, dob, email, phone, class_id) VALUES (?, ?, ?, ?, ?)";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, student.getName());
            statement.setString(2, student.getDob());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setInt(5, student.getClass_id());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi thêm sinh viên " + e.getMessage());
        }
    }
    public List<Student> getAll(){
        List<Student> studentsList = new ArrayList<>();
        String sql = "select * from students";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                int student_id = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                String dob = resultSet.getString("dob");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                int class_id = resultSet.getInt("class_id");
                studentsList.add(new Student(student_id, name, dob, email, phone, class_id));
            }
            return studentsList;
        } catch (SQLException e){
            throw new RuntimeException("Lỗi hiển thị thông tin sinh viên" + e.getMessage());
        }
    }
    public void update(Student student){
        String sql = """
                UPDATE students
                SET name = ?, dob = ?, email = ?, phone = ?, class_id = ?
                WHERE student_id = ?;""";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getDob());
            statement.setString(4, student.getPhone());
            statement.setInt(5, student.getClass_id());
            statement.setInt(6, student.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi sửa sinh viên " + e.getMessage());
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM students WHERE student_id = ?;";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi xoá sinh viên" + e.getMessage());
        }
    }
}
