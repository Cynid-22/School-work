import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager db = new DatabaseManager();

        // 1. Launch Admin GUI
        SwingUtilities.invokeLater(() -> new AdminGUI(db));

        // 2. Launch Owner GUI
        SwingUtilities.invokeLater(() -> new RestaurantOwnerGUI(db));
        // 3. Launch Console Chat (Runs in main thread)
        CustomerTextSim chat = new CustomerTextSim(db);
        chat.startLoop();
    }
}