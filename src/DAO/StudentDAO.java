package DAO;

import Model.Student;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements DAO<Student> {
    public void add(Student student){
        String sql = "INSERT INTO students (name, dob, email, phone) VALUES (?, ?, ?, ?)";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, student.getName());
            statement.setDate(2, student.getDob());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
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
                Date dob = resultSet.getDate("dob");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                studentsList.add(new Student(student_id, name, dob, email, phone));
            }
            return studentsList;
        } catch (SQLException e){
            throw new RuntimeException("Lỗi hiển thị thông tin sinh viên" + e.getMessage());
        }
    }
    public void update(Student student){
        String sql = """
                UPDATE students
                SET name = ?, dob = ?, email = ?, phone = ?
                WHERE student_id = ?;""";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, student.getName());
            statement.setDate(2, student.getDob());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPhone());
            statement.setInt(5, student.getId());
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
