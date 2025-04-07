package DAO;

import Model.Classroom;
import util.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomDAO{
    public void add(Classroom clazz){
        String sql = "INSERT INTO Classrooms (classroom_id, name, capacity) VALUES (?, ?, ?)";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, clazz.getClassroom_id());
            statement.setString(2, clazz.getName());
            statement.setInt(3, clazz.getCapacity());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi thêm lớp" + e.getMessage());
        }
    }
    public List<Classroom> getAll(){
        List<Classroom> classroomList = new ArrayList<>();
        String sql = "select * from Classrooms";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                int class_id = resultSet.getInt("classroom_id");
                String class_name = resultSet.getString("name");
                int capacity = resultSet.getInt("capacity");
                classroomList.add(new Classroom(class_id, class_name, capacity));
            }
            return classroomList;
        } catch (SQLException e){
            throw new RuntimeException("Lỗi hiển thị thông tin lớp" + e.getMessage());
        }
    }
    public void update(Classroom clazz){
        String sql = """
                UPDATE Classrooms
                SET name = ?, capacity = ?
                WHERE classroom_id = ?;""";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, clazz.getName());
            statement.setInt(2, clazz.getCapacity());
            statement.setInt(3, clazz.getClassroom_id());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi sửa lớp học " + e.getMessage());
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM Classrooms WHERE classroom_id = ?;";
        try(Connection connection = DatabaseConnect.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException("Lỗi xoá lớp" + e.getMessage());
        }
    }
}
