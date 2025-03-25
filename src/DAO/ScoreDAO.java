package DAO;

import Model.Score;
import util.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO implements DAO<Score>{
    public void add(Score object) {
        String sql = "INSERT INTO scores (student_id, course_id, score) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, object.getStudent_id());
            statement.setInt(2, object.getCourse_id());
            statement.setFloat(3, object.getScore());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi thêm điểm: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Score> getAll() {
        List<Score> objectList = new ArrayList<>();
        String sql = """
                SELECT * FROM scores;
                """;
        try (Connection connection = DatabaseConnect.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("score_id");
                int studentId = resultSet.getInt("student_id");
                int courseId = resultSet.getInt("course_id");
                float score = resultSet.getFloat("score");
                objectList.add(new Score(id, studentId, courseId, score));
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách điểm: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return objectList;
    }

    public void update(Score object) {
        String sql = "UPDATE scores \n" +
                "SET student_id = ?, course_id = ?, score = ? \n" +
                "WHERE score_id = ?;";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, object.getStudent_id());
            statement.setInt(2, object.getCourse_id());
            statement.setFloat(3, object.getScore());
            statement.setInt(4, object.getScore_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi chỉnh điểm: " + e.getMessage());
            throw new RuntimeException(e);





        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM scores WHERE score_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Lỗi khi xoá đơn điểm: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
