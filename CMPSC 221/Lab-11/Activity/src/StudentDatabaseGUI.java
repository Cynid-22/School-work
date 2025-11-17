import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class StudentDatabaseGUI extends JFrame implements ActionListener, ItemListener {

    private static final String DB_NAME = "Students";
    private static final String CONNECTION_URL = "jdbc:derby:" + DB_NAME + ";create=true";
    private static final String TABLE_NAME = "StudentBio";
    private Connection conn;
    private Statement stm;

    private JLabel lblPsuId1, lblName1, lblGender1;
    private JTextField txtPsuId1, txtName1;
    private JComboBox<String> comboGender;

    private JButton btnCreateTable, btnSave, btnDelete, btnExit, btnLoadPsuIds, btnTotalStudents;

    private JComboBox<String> comboPsuIds;
    private JLabel lblPsuId2, lblName2, lblGender2, lblGenderDisplay;
    private JTextField txtName2, txtTotalStudents;

    public StudentDatabaseGUI() {
        setTitle("Student Database Management");
        setSize(620, 280);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        lblPsuId1 = new JLabel("PSU-ID");
        lblPsuId1.setBounds(20, 20, 80, 25);
        add(lblPsuId1);

        txtPsuId1 = new JTextField();
        txtPsuId1.setBounds(100, 20, 150, 25);
        add(txtPsuId1);

        lblName1 = new JLabel("NAME");
        lblName1.setBounds(20, 60, 80, 25);
        add(lblName1);

        txtName1 = new JTextField();
        txtName1.setBounds(100, 60, 150, 25);
        add(txtName1);

        lblGender1 = new JLabel("Gender");
        lblGender1.setBounds(20, 100, 80, 25);
        add(lblGender1);

        comboGender = new JComboBox<>(new String[]{"Male", "Female"});
        comboGender.setBounds(100, 100, 150, 25);
        add(comboGender);

        lblPsuId2 = new JLabel("PSU-ID");
        lblPsuId2.setBounds(280, 20, 80, 25);
        add(lblPsuId2);

        comboPsuIds = new JComboBox<>();
        comboPsuIds.setBounds(360, 20, 100, 25);
        add(comboPsuIds);

        btnLoadPsuIds = new JButton("Load PSU-IDs");
        btnLoadPsuIds.setBounds(470, 20, 120, 25);
        add(btnLoadPsuIds);

        lblName2 = new JLabel("NAME");
        lblName2.setBounds(280, 60, 80, 25);
        add(lblName2);

        txtName2 = new JTextField();
        txtName2.setBounds(360, 60, 230, 25);
        txtName2.setEditable(false);
        add(txtName2);

        lblGender2 = new JLabel("Gender");
        lblGender2.setBounds(280, 100, 80, 25);
        add(lblGender2);

        lblGenderDisplay = new JLabel("-");
        lblGenderDisplay.setBounds(360, 100, 230, 25);
        lblGenderDisplay.setBorder(BorderFactory.createEtchedBorder());
        add(lblGenderDisplay);

        btnCreateTable = new JButton("Create Table");
        btnCreateTable.setBounds(20, 160, 120, 25);
        add(btnCreateTable);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(20, 190, 120, 25);
        add(btnDelete);

        btnSave = new JButton("Save");
        btnSave.setBounds(150, 160, 120, 25);
        add(btnSave);

        btnExit = new JButton("Exit");
        btnExit.setBounds(150, 190, 120, 25);
        add(btnExit);

        btnTotalStudents = new JButton("Total Students");
        btnTotalStudents.setBounds(300, 160, 150, 55);
        add(btnTotalStudents);

        txtTotalStudents = new JTextField("0");
        txtTotalStudents.setBounds(460, 160, 130, 55);
        txtTotalStudents.setEditable(false);
        txtTotalStudents.setHorizontalAlignment(JTextField.CENTER);
        add(txtTotalStudents);

        btnCreateTable.addActionListener(this);
        btnSave.addActionListener(this);
        btnDelete.addActionListener(this);
        btnExit.addActionListener(this);
        btnLoadPsuIds.addActionListener(this);
        btnTotalStudents.addActionListener(this);

        comboPsuIds.addItemListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });

        createDbConnection();
    }

    private void createDbConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(CONNECTION_URL);
            stm = conn.createStatement();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this,
                    "Derby Driver not found. Please add derby.jar to your classpath.",
                    "Driver Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Database Connection Error: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == btnCreateTable) {
            createTable();
        } else if (source == btnSave) {
            saveStudent();
        } else if (source == btnDelete) {
            deleteStudent();
        } else if (source == btnExit) {
            exitApp();
        } else if (source == btnLoadPsuIds) {
            loadPsuIds();
        } else if (source == btnTotalStudents) {
            countStudents();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboPsuIds && e.getStateChange() == ItemEvent.SELECTED) {
            String selectedId = (String) e.getItem();
            if (selectedId != null && !selectedId.isEmpty()) {
                loadStudentData(selectedId);
            }
        }
    }

    private void createTable() {
        String createString = "CREATE TABLE " + TABLE_NAME + " (" +
                "PSU_ID VARCHAR(20) NOT NULL PRIMARY KEY, " +
                "Name VARCHAR(30), " +
                "Gender VARCHAR(10))";
        try {
            ResultSet rs = conn.getMetaData().getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Table '" + TABLE_NAME + "' already exists.");
            } else {
                stm.execute(createString);
                JOptionPane.showMessageDialog(this, "Table '" + TABLE_NAME + "' created successfully.");
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error creating table: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveStudent() {
        try {
            String psuId = txtPsuId1.getText();
            String name = txtName1.getText();
            String gender = (String) comboGender.getSelectedItem();

            if (psuId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PSU-ID cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PreparedStatement psstm = conn.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (PSU_ID, Name, Gender) VALUES (?, ?, ?)");
            psstm.setString(1, psuId);
            psstm.setString(2, name);
            psstm.setString(3, gender);
            psstm.executeUpdate();

            JOptionPane.showMessageDialog(this, "Student saved successfully.");
            psstm.close();

            txtPsuId1.setText("");
            txtName1.setText("");

            loadPsuIds();
            countStudents();

        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                JOptionPane.showMessageDialog(this, "Error: PSU-ID " + txtPsuId1.getText() + " already exists.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error saving student: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deleteStudent() {
        try {
            String psuId = txtPsuId1.getText();

            if (psuId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a PSU-ID to delete.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            PreparedStatement psstm = conn.prepareStatement(
                    "DELETE FROM " + TABLE_NAME + " WHERE PSU_ID = ?");
            psstm.setString(1, psuId);
            int rowsAffected = psstm.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record for PSU-ID " + psuId + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with PSU-ID " + psuId + ".");
            }
            psstm.close();

            txtPsuId1.setText("");
            loadPsuIds();
            countStudents();
            clearDisplayFields();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exitApp() {
        try {
            if (stm != null) stm.close();
            if (conn != null) {
                conn.close();
                try {
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                } catch (SQLException se) {
                    if (!se.getSQLState().equals("XJ015")) {
                        throw se;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private void loadPsuIds() {
        try {
            ResultSet rs = stm.executeQuery("SELECT PSU_ID FROM " + TABLE_NAME + " ORDER BY PSU_ID");

            ArrayList<String> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getString(1));
            }
            rs.close();

            comboPsuIds.removeAllItems();
            clearDisplayFields();

            for (String id : ids) {
                comboPsuIds.addItem(id);
            }

            if (ids.isEmpty()) {
                comboPsuIds.addItem(null);
                comboPsuIds.setSelectedItem(null);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading PSU-IDs: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void countStudents() {
        try {
            ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM " + TABLE_NAME);
            if (rs.next()) {
                int count = rs.getInt(1);
                txtTotalStudents.setText(String.valueOf(count));
            }
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error counting students: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadStudentData(String psuId) {
        try {
            PreparedStatement psstm = conn.prepareStatement(
                    "SELECT Name, Gender FROM " + TABLE_NAME + " WHERE PSU_ID = ?");
            psstm.setString(1, psuId);
            ResultSet rs = psstm.executeQuery();

            if (rs.next()) {
                txtName2.setText(rs.getString("Name"));
                lblGenderDisplay.setText(rs.getString("Gender"));
            }
            rs.close();
            psstm.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearDisplayFields() {
        txtName2.setText("");
        lblGenderDisplay.setText("-");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentDatabaseGUI gui = new StudentDatabaseGUI();
            gui.setVisible(true);
        });
    }
}