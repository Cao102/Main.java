package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events implements TableConvertible{
    private String id;
    private String eventName;
    private LocalDateTime eventDate;
    private String location;

    public Events(String id, String eventName, LocalDateTime eventDate, String location) {
        this.id = id;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getLocation() {
        return location;
    }

//    @Override
//    public String toString() {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//        return String.format("║ %-4s ║ %-30s ║ %-18s ║ %-20s ║", id, eventName, eventDate.format(dateTimeFormatter), location);
//    }
    public String[] toRow() {
        return new String[] {
                id, eventName, eventDate.toString(), location
        };
    }
}
