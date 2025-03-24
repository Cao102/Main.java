package DAO;

import Model.Class;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements DAO<Class>{
    public void add(Class clazz){
        String sql = "INSERT INTO classes (class_id, class_name, teacher_id) VALUES (?, ?, ?)";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, clazz.getClass_id());
            statement.setString(2, clazz.getClass_name());
            statement.setInt(3, clazz.getTeacher_id());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi thêm lớp" + e.getMessage());
        }
    }
    public List<Class> getAll(){
        List<Class> classList = new ArrayList<>();
        String sql = "select * from classes";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                int class_id = resultSet.getInt("class_id");
                String class_name = resultSet.getString("class_name");
                int teacher_id = resultSet.getInt("teacher_id");
                classList.add(new Class(class_id, class_name, teacher_id));
            }
            return classList;
        } catch (SQLException e){
            throw new RuntimeException("Lỗi hiển thị thông tin lớp" + e.getMessage());
        }
    }
    public void update(Class clazz){
        String sql = """
                UPDATE classes
                SET class_name = ?, teacher_id = ?
                WHERE class_id = ?;""";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, clazz.getClass_name());
            statement.setInt(2, clazz.getTeacher_id());
            statement.setInt(3, clazz.getClass_id());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi sửa lớp học " + e.getMessage());
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM classes WHERE class_id = ?;";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi xoá lớp" + e.getMessage());
        }
    }
}
