package DAO;

import Model.Teacher;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO implements DAO<Teacher> {
    public void add(Teacher teacher){
        String sql = "INSERT INTO teachers (name, email, phone) VALUES (?, ?, ?)";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getEmail());
            statement.setString(3, teacher.getPhone());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi thêm giáo viên" + e.getMessage());
        }
    }
    public List<Teacher> getAll(){
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "select * from teachers";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                int teacher_id = resultSet.getInt("teacher_id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                teacherList.add(new Teacher(teacher_id, name, email, phone));
            }
            return teacherList;
        } catch (SQLException e){
            throw new RuntimeException("Lỗi hiện thị thông tin giáo viên" + e.getMessage());
        }
    }
    public void update(Teacher object){
        String sql = """
                UPDATE teachers
                SET name = ?, email = ?, phone = ?
                WHERE teacher_id = ?;""";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, object.getName());
            statement.setString(2, object.getEmail());
            statement.setString(3, object.getPhone());
            statement.setInt(4, object.getId());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi sửa thông tin giáo viên " + e.getMessage());
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM teachers WHERE teacher_id = ?;";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi xoá giáo viên" + e.getMessage());
        }
    }
}
