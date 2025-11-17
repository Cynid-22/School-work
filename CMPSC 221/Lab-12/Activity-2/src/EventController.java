public class EventController {
    private Event model;
    private EventView view;

    public EventController(Event model, EventView view) {
        this.model = model;
        this.view = view;
    }

    public void setEventId(String id) {
        model.setEventId(id);
    }

    public String getEventId() {
        return model.getEventId();
    }

    public void setEventName(String name) {
        model.setEventName(name);
    }

    public String getEventName() {
        return model.getEventName();
    }

    public void setEventLocation(String location) {
        model.setLocation(location);
    }

    public String getEventLocation() {
        return model.getLocation();
    }

    public void updateView() {
        view.printEventDetails(model.getEventId(), model.getEventName(), model.getLocation());
    }
}