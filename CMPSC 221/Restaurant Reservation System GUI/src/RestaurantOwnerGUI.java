import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class RestaurantOwnerGUI extends JFrame {
    private DatabaseManager db;
    private JComboBox<DatabaseManager.RestaurantEntry> cbRest;
    private JTextArea txtList;
    private JTextArea txtInfo;
    private JTextField txtWalkInTime, txtWalkInCount;

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("[H:mm][HH:mm]");
    private static final Border PADDING = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public RestaurantOwnerGUI(DatabaseManager db) {
        this.db = db;
        setTitle("Restaurant Owner View");
        setSize(500, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        ((JPanel)this.getContentPane()).setBorder(PADDING);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Added gap

        cbRest = new JComboBox<>();
        loadRestaurants();
        cbRest.addActionListener(e -> refreshList());

        JButton btnRefresh = new JButton("Refresh List");
        btnRefresh.addActionListener(e -> loadRestaurants());

        top.add(new JLabel("Select Restaurant:"));
        top.add(cbRest);
        top.add(btnRefresh);

        add(top, BorderLayout.NORTH);

        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setBorder(BorderFactory.createTitledBorder("Restaurant Details"));

        txtList = new JTextArea();
        txtList.setEditable(false);
        txtList.setBorder(BorderFactory.createTitledBorder("Daily Reservations"));

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(txtInfo),
                new JScrollPane(txtList));
        splitPane.setDividerLocation(150);
        splitPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(splitPane, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 10));

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Walk-in Time (HH:MM):"));
        txtWalkInTime = new JTextField();
        inputPanel.add(txtWalkInTime);

        inputPanel.add(new JLabel("Headcount:"));
        txtWalkInCount = new JTextField();
        inputPanel.add(txtWalkInCount);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnCheck = new JButton("Check Walk-in");
        btnCheck.setPreferredSize(new Dimension(150, 30));
        btnPanel.add(btnCheck);

        bottom.add(inputPanel, BorderLayout.CENTER);
        bottom.add(btnPanel, BorderLayout.SOUTH);

        btnCheck.addActionListener(e -> {
            DatabaseManager.RestaurantEntry entry = (DatabaseManager.RestaurantEntry) cbRest.getSelectedItem();
            if (entry == null) return;
            try {
                int count = Integer.parseInt(txtWalkInCount.getText());
                String timeStr = txtWalkInTime.getText();
                LocalTime walkInTime = LocalTime.parse(timeStr, TIME_FMT);

                String[] hours = db.getRestaurantHours(entry.id);
                LocalTime closeTime = LocalTime.MAX;

                if (hours != null) {
                    LocalTime open = LocalTime.parse(hours[0], TIME_FMT);
                    closeTime = LocalTime.parse(hours[1], TIME_FMT);

                    if (walkInTime.isBefore(open) || walkInTime.isAfter(closeTime)) {
                        JOptionPane.showMessageDialog(this, "Refused: Restaurant is closed. Hours: " + open + " - " + closeTime);
                        return;
                    }
                }

                if(!db.checkTableExists(entry.id, count)) {
                    JOptionPane.showMessageDialog(this, "Refused: No table large enough exists.");
                    return;
                }

                if (isOverbooked(entry.id, count, walkInTime, closeTime)) {
                    JOptionPane.showMessageDialog(this, "Refused: No tables available (conflict).");
                    return;
                }

                String custName = JOptionPane.showInputDialog("Table Available. Enter Customer Name:");
                if(custName != null && !custName.isEmpty()) {
                    LocalTime endTime = walkInTime.plusHours(2);
                    if(endTime.isAfter(closeTime)) endTime = closeTime;

                    db.addReservation(entry.id, custName, "WALK-IN", count, walkInTime.toString(), endTime.toString());
                    refreshList();
                }

            } catch (DateTimeParseException dtpe) {
                JOptionPane.showMessageDialog(this, "Invalid Time Format (HH:MM or H:MM)");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
                ex.printStackTrace();
            }
        });

        add(bottom, BorderLayout.SOUTH);
        setVisible(true);
    }

    private boolean isOverbooked(int restId, int headcount, LocalTime reqStart, LocalTime closeTime) {
        int targetCap = db.getBestFitCapacity(restId, headcount);
        int totalTables = db.getSpecificTableCount(restId, headcount);

        LocalTime reqEnd = reqStart.plusHours(2);
        if (reqEnd.isAfter(closeTime)) reqEnd = closeTime;

        LocalTime reqBufferEnd = reqEnd;
        if (reqEnd.isBefore(closeTime)) reqBufferEnd = reqEnd.plusMinutes(15);

        List<String[]> resList = db.getAllReservations(restId);
        int conflictCount = 0;

        for(String[] res : resList) {
            try {
                int rCount = Integer.parseInt(res[2]);
                if (db.getBestFitCapacity(restId, rCount) == targetCap) {
                    LocalTime resStart = LocalTime.parse(res[0], TIME_FMT);
                    LocalTime resEnd = LocalTime.parse(res[1], TIME_FMT);

                    LocalTime resBufferEnd = resEnd;
                    if (resEnd.isBefore(closeTime)) resBufferEnd = resEnd.plusMinutes(15);

                    if (reqStart.isBefore(resBufferEnd) && resStart.isBefore(reqBufferEnd)) {
                        conflictCount++;
                    }
                }
            } catch (Exception e) { /* Ignore */ }
        }

        return conflictCount >= totalTables;
    }

    private void loadRestaurants() {
        cbRest.removeAllItems();
        for(DatabaseManager.RestaurantEntry e : db.getAllRestaurants()) {
            cbRest.addItem(e);
        }
    }

    private void refreshList() {
        DatabaseManager.RestaurantEntry entry = (DatabaseManager.RestaurantEntry) cbRest.getSelectedItem();
        if(entry != null) {
            txtInfo.setText(db.getRestaurantInfo(entry.id));
            txtList.setText(db.getReservationsForRestaurant(entry.id));
        } else {
            txtInfo.setText("");
            txtList.setText("");
        }
    }
}