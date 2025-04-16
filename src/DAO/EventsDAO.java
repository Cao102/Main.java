package DAO;

import Model.Events;
import connectDatabase.DatabaseConnect;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventsDAO {

    // Thêm sự kiện
    public void add(Events event) {
        String sql = "INSERT INTO Events (event_id, event_name, event_date, location) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getId());
            statement.setString(2, event.getEventName());
            statement.setTimestamp(3, Timestamp.valueOf(event.getEventDate()));
            statement.setString(4, event.getLocation());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm sự kiện: " + e.getMessage());
        }
    }

    // Cập nhật sự kiện
    public void update(Events event) {
        String sql = "UPDATE Events SET event_name = ?, event_date = ?, location = ? WHERE event_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, event.getEventName());
            statement.setTimestamp(2, Timestamp.valueOf(event.getEventDate()));
            statement.setString(3, event.getLocation());
            statement.setString(4, event.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật sự kiện: " + e.getMessage());
        }
    }

    // Xóa sự kiện
    public void delete(String eventId) {
        String sql = "DELETE FROM Events WHERE event_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa sự kiện: " + e.getMessage());
        }
    }


    // Lấy tất cả sự kiện
    public List<Events> getAll() {
        List<Events> events = new ArrayList<>();
        String sql = "SELECT * FROM Events ORDER BY event_id";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("event_id");
                String name = rs.getString("event_name");
                LocalDateTime date = rs.getTimestamp("event_date").toLocalDateTime();
                String location = rs.getString("location");
                events.add(new Events(id, name, date, location));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy xuất danh sách sự kiện: " + e.getMessage());
        }
        return events;
    }

    // Tìm sự kiện theo ngày
    public List<Events> searchByDate(LocalDate date) {
        List<Events> events = new ArrayList<>();
        String sql = "SELECT * FROM Events WHERE DATE(event_date) = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, Date.valueOf(date));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String id = rs.getString("event_id");
                String name = rs.getString("event_name");
                LocalDateTime dateTime = rs.getTimestamp("event_date").toLocalDateTime();
                String location = rs.getString("location");
                events.add(new Events(id, name, dateTime, location));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tìm kiếm sự kiện theo ngày: " + e.getMessage());
        }
        return events;
    }


    // Kiểm tra sự kiện có tồn tại
    public boolean isEventExist(String eventId) {
        String sql = "SELECT COUNT(*) FROM Events WHERE event_id = ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, eventId);
            ResultSet rs = statement.executeQuery();
            return rs.next() && rs.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra sự tồn tại sự kiện: " + e.getMessage());
        }
    }
}
