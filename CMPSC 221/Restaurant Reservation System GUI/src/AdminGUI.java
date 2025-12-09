import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class AdminGUI extends JFrame {
    private DatabaseManager db;
    private JComboBox<DatabaseManager.RestaurantEntry> cbSelectP1, cbSelectP2;
    private JTextArea txtInfoP1, txtInfoP2;

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("[H:mm][HH:mm]");

    private JTextField txtNewName;
    private JTextField txtEditName, txtEditLoc, txtEditOpen, txtEditClose, txtCap, txtQty;

    private static final Border PADDING = BorderFactory.createEmptyBorder(10, 10, 10, 10);

    public AdminGUI(DatabaseManager db) {
        this.db = db;
        setTitle("System Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cbSelectP1 = new JComboBox<>();
        cbSelectP2 = new JComboBox<>();

        JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(PADDING);

        JPanel p1 = new JPanel(new BorderLayout(5, 5));
        p1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel p1Top = new JPanel(new GridLayout(5, 1, 5, 5));

        refreshDropdowns();
        cbSelectP1.addActionListener(e -> updateInfoBox(1));

        txtInfoP1 = new JTextArea();
        txtInfoP1.setEditable(false);

        txtNewName = new JTextField("New Restaurant Name");
        JButton btnAdd = new JButton("Add Restaurant");
        btnAdd.addActionListener(e -> {
            if(!txtNewName.getText().trim().isEmpty()){
                db.addRestaurant(txtNewName.getText(), "Default Location");
                refreshDropdowns();
                JOptionPane.showMessageDialog(this, "Added! Go to 'Edit Details' tab to add Tables and set Hours.");
            }
        });

        JButton btnDel = new JButton("Delete Selected Restaurant");
        btnDel.addActionListener(e -> {
            DatabaseManager.RestaurantEntry entry = (DatabaseManager.RestaurantEntry) cbSelectP1.getSelectedItem();
            if(entry != null) {
                db.deleteRestaurant(entry.id);
                refreshDropdowns();
                txtInfoP1.setText("");
            }
        });

        p1Top.add(new JLabel("Select Restaurant to View/Delete:"));
        p1Top.add(cbSelectP1);
        p1Top.add(btnDel);
        p1Top.add(txtNewName);
        p1Top.add(btnAdd);

        p1.add(p1Top, BorderLayout.NORTH);
        p1.add(new JScrollPane(txtInfoP1), BorderLayout.CENTER);


        JPanel p2 = new JPanel(new BorderLayout(5, 5));
        p2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel p2Form = new JPanel(new GridLayout(7, 2, 5, 5));

        cbSelectP2.addActionListener(e -> loadEditFields());

        txtEditName = new JTextField();
        txtEditLoc = new JTextField();
        txtEditOpen = new JTextField();
        txtEditClose = new JTextField();
        txtCap = new JTextField();
        txtQty = new JTextField();

        JButton btnSave = new JButton("Save Changes");
        btnSave.addActionListener(e -> {
            DatabaseManager.RestaurantEntry entry = (DatabaseManager.RestaurantEntry) cbSelectP2.getSelectedItem();
            if(entry != null) {
                int restId = entry.id;

                try {
                    LocalTime open = LocalTime.parse(txtEditOpen.getText(), TIME_FMT);
                    LocalTime close = LocalTime.parse(txtEditClose.getText(), TIME_FMT);
                    long minutesOpen = ChronoUnit.MINUTES.between(open, close);

                    if (close.isBefore(open) || minutesOpen < 45) {
                        JOptionPane.showMessageDialog(this, "Error: Must be open >= 45 mins. Close must be after Open.");
                        return;
                    }
                } catch (DateTimeParseException dtpe) {
                    JOptionPane.showMessageDialog(this, "Error: Invalid Time Format. Use HH:MM");
                    return;
                }

                if(!txtCap.getText().isEmpty() && !txtQty.getText().isEmpty()) {
                    try {
                        int c = Integer.parseInt(txtCap.getText());
                        int q = Integer.parseInt(txtQty.getText());
                        if (c <= 0 || q <= 0) {
                            JOptionPane.showMessageDialog(this, "Error: Capacity/Quantity must be > 0.");
                            return;
                        }
                        db.updateTableConfig(restId, c, q);
                    } catch(NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Capacity/Quantity must be numbers");
                        return;
                    }
                }

                db.updateRestaurantDetails(restId, txtEditName.getText(), txtEditLoc.getText(), txtEditOpen.getText(), txtEditClose.getText());

                refreshDropdowns();
                cbSelectP2.setSelectedItem(entry);
                updateInfoBox(2);
                JOptionPane.showMessageDialog(this, "Saved!");
            }
        });

        JPanel p2ButtonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        p2ButtonWrapper.add(btnSave);
        p2ButtonWrapper.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        p2Form.add(new JLabel("Select Restaurant:")); p2Form.add(cbSelectP2);
        p2Form.add(new JLabel("Name:")); p2Form.add(txtEditName);
        p2Form.add(new JLabel("Location:")); p2Form.add(txtEditLoc);
        p2Form.add(new JLabel("Open Time (HH:MM):")); p2Form.add(txtEditOpen);
        p2Form.add(new JLabel("Close Time (HH:MM):")); p2Form.add(txtEditClose);
        p2Form.add(new JLabel("Table Capacity:")); p2Form.add(txtCap);
        p2Form.add(new JLabel("Quantity to Set:")); p2Form.add(txtQty);

        txtInfoP2 = new JTextArea();
        txtInfoP2.setEditable(false);

        JPanel p2Top = new JPanel(new BorderLayout());
        p2Top.add(p2Form, BorderLayout.NORTH);
        p2Top.add(p2ButtonWrapper, BorderLayout.CENTER);

        p2.add(p2Top, BorderLayout.NORTH);
        p2.add(new JScrollPane(txtInfoP2), BorderLayout.CENTER);

        tabs.addTab("Manage Restaurants", p1);
        tabs.addTab("Edit Details", p2);

        add(tabs);
        setVisible(true);
    }

    private void refreshDropdowns() {
        cbSelectP1.removeAllItems();
        cbSelectP2.removeAllItems();
        for(DatabaseManager.RestaurantEntry e : db.getAllRestaurants()) {
            cbSelectP1.addItem(e);
            cbSelectP2.addItem(e);
        }
    }

    private void updateInfoBox(int panel) {
        DatabaseManager.RestaurantEntry entry = (panel == 1) ? (DatabaseManager.RestaurantEntry) cbSelectP1.getSelectedItem() : (DatabaseManager.RestaurantEntry) cbSelectP2.getSelectedItem();
        if(entry == null) return;
        String info = db.getRestaurantInfo(entry.id);
        if(panel == 1) txtInfoP1.setText(info);
        else txtInfoP2.setText(info);
    }

    private void loadEditFields() {
        updateInfoBox(2);

        DatabaseManager.RestaurantEntry entry = (DatabaseManager.RestaurantEntry) cbSelectP2.getSelectedItem();
        if (entry != null) {
            String[] details = db.getRestaurantDetailsForEdit(entry.id);
            if (details != null) {
                txtEditName.setText(details[0]);
                txtEditLoc.setText(details[1]);
                txtEditOpen.setText(details[2]);
                txtEditClose.setText(details[3]);

                txtCap.setText("");
                txtQty.setText("");
            }
        }
    }
}