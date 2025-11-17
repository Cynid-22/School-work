public class MVCEventDemo {
    public static void main(String[] args) {

        Event model = retrieveEventFromDatabase();
        EventView view = new EventView();
        EventController controller = new EventController(model, view);

        System.out.println("Initial event data:");
        controller.updateView();

        controller.setEventName("Spring Career Fair");
        controller.setEventLocation("Behrend-REC");

        System.out.println("Updated event data:");
        controller.updateView();
    }

    private static Event retrieveEventFromDatabase() {
        Event event = new Event();
        event.setEventId("E-001");
        event.setEventName("Fall Career Fair");
        event.setLocation("Junker Center");
        return event;
    }
}