package Controller;

import DAO.EventsDAO;
import Model.Events;
import View.ViewEvents;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EventsController {

    private final EventsDAO eventsDAO;
    private final ViewEvents viewEvents;

    public EventsController() {
        this.eventsDAO = new EventsDAO();
        this.viewEvents = new ViewEvents();
    }

    public void startEvents() {
        while (true) {
            int choice = viewEvents.menuEvents();
            switch (choice) {
                case 1:
                    addEvent();
                    break;
                case 2:
                    updateEvent();
                    break;
                case 3:
                    deleteEvent();
                    break;
                case 4:
                    displayEventsDetails();
                    break;
                case 5:
                    searchEventsByDate();
                    break;
                case 6:
                    return; // Thoát khỏi menu sự kiện
                default:
                    viewEvents.errorChoose();
            }
        }
    }

    private void addEvent() {
        while (true){
            String eventId = viewEvents.inputEventId();
            if (eventsDAO.isEventExist(eventId)) {
                boolean update = viewEvents.confirmUpdateEvent();  // Hỏi người dùng có muốn cập nhật
                if (update) {
                    String name = viewEvents.inputEventName();
                    LocalDateTime dateTime = viewEvents.inputEventDateTime();
                    String location = viewEvents.inputLocation();
                    Events event = new Events(eventId, name, dateTime, location);
                    eventsDAO.update(event);
                    viewEvents.notifyEventUpdated();
                }else{
                    continue;
                }
            } else {
                String name = viewEvents.inputEventName();
                LocalDateTime dateTime = viewEvents.inputEventDateTime();
                String location = viewEvents.inputLocation();
                Events event = new Events(eventId, name, dateTime, location);
                eventsDAO.add(event);
                viewEvents.notifyEventAdded();
            }
            break;
        }
    }

    private void updateEvent() {
        while (true){
            String eventId = viewEvents.inputEventId();
            if (!eventsDAO.isEventExist(eventId)) {
                viewEvents.showEventNotExist();
                continue;
            }else{
                String name = viewEvents.inputEventName();
                LocalDateTime dateTime = viewEvents.inputEventDateTime();
                String location = viewEvents.inputLocation();
                Events event = new Events(eventId, name, dateTime, location);
                eventsDAO.update(event);
                viewEvents.notifyEventUpdated();
            }
            break;
        }
    }

    private void deleteEvent() {
        while (true){
            String eventId = viewEvents.inputEventId();
            if (!eventsDAO.isEventExist(eventId)) {
                viewEvents.showEventNotExist();
                continue;
            }else{
                eventsDAO.delete(eventId);
                viewEvents.notifyEventDeleted();
            }
            break;
        }
    }

    private void displayEventsDetails() {
        List<Events> events = eventsDAO.getAll();
        viewEvents.displayEvents(events);
    }

    private void searchEventsByDate() {
        while (true){
            LocalDate date = viewEvents.inputEventDate();
            List<Events> events = eventsDAO.searchByDate(date);
            if (events.isEmpty()) {
                viewEvents.showNoEventsFound();
                continue;
            } else {
                viewEvents.displayEvents(events);
            }
            break;
        }
    }
}
