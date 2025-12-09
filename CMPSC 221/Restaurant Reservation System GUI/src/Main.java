import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        SwingUtilities.invokeLater(() -> new AdminGUI(db));
        SwingUtilities.invokeLater(() -> new RestaurantOwnerGUI(db));
        CustomerTextSim chat = new CustomerTextSim(db);
        chat.startLoop();
    }
}