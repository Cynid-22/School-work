// Import necessary packages
import javax.swing.*; // For GUI components (JFrame, JButton, JLabel, etc.)
import java.awt.*;      // For layout managers and component properties (e.g., setBounds)
import java.awt.event.*; // For event handling (ActionListener, ItemListener, WindowAdapter)
import java.sql.*;       // For JDBC (database connectivity - Connection, Statement, ResultSet, etc.)
import java.util.ArrayList; // For using a dynamic list to hold PSU IDs

/**
 * Main application class.
 * - Extends JFrame to act as the main window.
 * - Implements ActionListener to listen for button clicks.
 * - Implements ItemListener to listen for changes in the JComboBox (dropdown).
 */
public class StudentDatabaseGUIAnnotated extends JFrame implements ActionListener, ItemListener {

    // --- Database Constants ---
    private static final String DB_NAME = "Students"; // Name of the Derby database directory
    // The connection URL for an embedded Derby database. ";create=true" will create the database if it doesn't exist.
    private static final String CONNECTION_URL = "jdbc:derby:" + DB_NAME + ";create=true";
    private static final String TABLE_NAME = "StudentBio"; // The name of the table to store student data

    // --- Database Variables ---
    private Connection conn;  // Holds the active connection to the database
    private Statement stm;    // Used for executing simple, static SQL statements

    // --- GUI Components (Left Panel - Input) ---
    private JLabel lblPsuId1, lblName1, lblGender1; // Labels to describe the text fields
    private JTextField txtPsuId1, txtName1;         // Text fields for entering new student data
    private JComboBox<String> comboGender;          // Dropdown for selecting gender ("Male", "Female")

    // --- GUI Components (Buttons) ---
    private JButton btnCreateTable, btnSave, btnDelete, btnExit, btnLoadPsuIds, btnTotalStudents;

    // --- GUI Components (Right Panel - Display/Query) ---
    private JComboBox<String> comboPsuIds;      // Dropdown to show existing PSU IDs from the database
    private JLabel lblPsuId2, lblName2, lblGender2, lblGenderDisplay; // Labels for displaying student data
    private JTextField txtName2, txtTotalStudents; // Text fields to display data (name, total count)

    /**
     * Constructor: This method is called when a new StudentDatabaseGUI object is created.
     * It sets up the entire GUI window and all its components.
     */
    public StudentDatabaseGUIAnnotated() {
        // --- Window Setup ---
        setTitle("Student Database Management"); // Set the text in the window's title bar
        setSize(620, 280);                 // Set the window's width (620px) and height (280px)
        setLayout(null);                        // Use absolute positioning (manually set component x,y,w,h)
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // We will handle closing with `addWindowListener`
        setResizable(false);                    // Prevent the user from resizing the window
        setLocationRelativeTo(null);            // Center the window on the screen when it first appears

        // --- Left Panel Components (Input) ---
        lblPsuId1 = new JLabel("PSU-ID");      // Create label
        lblPsuId1.setBounds(20, 20, 80, 25); // Set position (x, y, width, height)
        add(lblPsuId1);                         // Add the label to the window

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

        comboGender = new JComboBox<>(new String[]{"Male", "Female"}); // Create dropdown with options
        comboGender.setBounds(100, 100, 150, 25);
        add(comboGender);

        // --- Right Panel Components (Display/Query) ---
        lblPsuId2 = new JLabel("PSU-ID");
        lblPsuId2.setBounds(280, 20, 80, 25);
        add(lblPsuId2);

        comboPsuIds = new JComboBox<>(); // Create an empty dropdown; will be filled from DB
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
        txtName2.setEditable(false); // Make this field read-only
        add(txtName2);

        lblGender2 = new JLabel("Gender");
        lblGender2.setBounds(280, 100, 80, 25);
        add(lblGender2);

        lblGenderDisplay = new JLabel("-"); // Placeholder text
        lblGenderDisplay.setBounds(360, 100, 230, 25);
        lblGenderDisplay.setBorder(BorderFactory.createEtchedBorder()); // Add a border to make it look like a field
        add(lblGenderDisplay);

        // --- Bottom Panel Components (Buttons) ---
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
        btnTotalStudents.setBounds(300, 160, 150, 55); // Taller button
        add(btnTotalStudents);

        txtTotalStudents = new JTextField("0"); // Default text
        txtTotalStudents.setBounds(460, 160, 130, 55);
        txtTotalStudents.setEditable(false); // Read-only
        txtTotalStudents.setHorizontalAlignment(JTextField.CENTER); // Center the text
        add(txtTotalStudents);

        // --- Event Listener Registration ---
        // Tell all buttons that `this` class (StudentDatabaseGUI) will handle their clicks
        btnCreateTable.addActionListener(this);
        btnSave.addActionListener(this);
        btnDelete.addActionListener(this);
        btnExit.addActionListener(this);
        btnLoadPsuIds.addActionListener(this);
        btnTotalStudents.addActionListener(this);

        // Tell the PSU-ID dropdown that `this` class will handle its selection changes
        comboPsuIds.addItemListener(this);

        // Add a custom listener for the window's close button (the 'X')
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp(); // Call our custom exit method
            }
        });

        // --- Database Initialization ---
        createDbConnection(); // Call the method to connect to the database
    }

    /**
     * Initializes the connection to the embedded Derby database.
     */
    private void createDbConnection() {
        try {
            // Load the Derby embedded driver class
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            // Establish the connection using the URL
            conn = DriverManager.getConnection(CONNECTION_URL);
            // Create a reusable Statement object
            stm = conn.createStatement();
        } catch (ClassNotFoundException e) {
            // Error if derby.jar is not in the project's classpath
            JOptionPane.showMessageDialog(this,
                    "Derby Driver not found. Please add derby.jar to your classpath.",
                    "Driver Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit the application
        } catch (SQLException e) {
            // Error if the database connection fails
            JOptionPane.showMessageDialog(this,
                    "Database Connection Error: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is required by the ActionListener interface.
     * It's called automatically whenever a registered button is clicked.
     *
     * @param e The ActionEvent object containing details about the event (e.g., which button was clicked).
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource(); // Get the component that triggered the event

        // Check which button was clicked and call the appropriate method
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

    /**
     * This method is required by the ItemListener interface.
     * It's called automatically when the selected item in the `comboPsuIds` dropdown changes.
     *
     * @param e The ItemEvent object containing details about the event.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        // We only care about events from our dropdown AND when an item is newly SELECTED
        if (e.getSource() == comboPsuIds && e.getStateChange() == ItemEvent.SELECTED) {
            String selectedId = (String) e.getItem(); // Get the selected PSU-ID string
            // Check if it's a valid ID (not null or empty)
            if (selectedId != null && !selectedId.isEmpty()) {
                loadStudentData(selectedId); // Load and display the data for that ID
            }
        }
    }

    /**
     * Executes the SQL to create the StudentBio table if it doesn't already exist.
     */
    private void createTable() {
        String createString = "CREATE TABLE " + TABLE_NAME + " (" +
                "PSU_ID VARCHAR(20) NOT NULL PRIMARY KEY, " + // PSU_ID is the Primary Key
                "Name VARCHAR(30), " +
                "Gender VARCHAR(10))";
        try {
            // Check if the table already exists
            ResultSet rs = conn.getMetaData().getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (rs.next()) {
                // If rs.next() is true, the table exists
                JOptionPane.showMessageDialog(this, "Table '" + TABLE_NAME + "' already exists.");
            } else {
                // Table does not exist, so create it
                stm.execute(createString);
                JOptionPane.showMessageDialog(this, "Table '" + TABLE_NAME + "' created successfully.");
            }
            rs.close(); // Always close the ResultSet
        } catch (SQLException e) {
            // Handle any SQL errors during table creation
            JOptionPane.showMessageDialog(this, "Error creating table: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves a new student record to the database using the data from the left-panel text fields.
     */
    private void saveStudent() {
        try {
            // Get data from the input fields
            String psuId = txtPsuId1.getText();
            String name = txtName1.getText();
            String gender = (String) comboGender.getSelectedItem(); // Get selected item from dropdown

            // --- Input Validation ---
            if (psuId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "PSU-ID cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return; // Stop the method
            }
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                return; // Stop the method
            }

            // Use a PreparedStatement for safety (prevents SQL injection) and efficiency
            PreparedStatement psstm = conn.prepareStatement(
                    "INSERT INTO " + TABLE_NAME + " (PSU_ID, Name, Gender) VALUES (?, ?, ?)");
            psstm.setString(1, psuId);  // Set the first '?' to the psuId
            psstm.setString(2, name);   // Set the second '?' to the name
            psstm.setString(3, gender); // Set the third '?' to the gender
            psstm.executeUpdate(); // Execute the insert statement

            JOptionPane.showMessageDialog(this, "Student saved successfully.");
            psstm.close(); // Always close the PreparedStatement

            // Clear the input fields after successful save
            txtPsuId1.setText("");
            txtName1.setText("");

            // Refresh the GUI to show the new data
            loadPsuIds();    // Reload the list of PSU-IDs in the dropdown
            countStudents(); // Update the total student count

        } catch (SQLException e) {
            // Handle specific SQL errors
            if (e.getSQLState().equals("23505")) {
                // This is the SQL state for a "Primary Key Violation" (duplicate ID)
                JOptionPane.showMessageDialog(this, "Error: PSU-ID " + txtPsuId1.getText() + " already exists.",
                        "Save Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Handle all other SQL errors
                JOptionPane.showMessageDialog(this, "Error saving student: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Deletes a student record from the database based on the PSU-ID in the left-panel text field.
     */
    private void deleteStudent() {
        try {
            String psuId = txtPsuId1.getText(); // Get the ID to delete

            // Input validation
            if (psuId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a PSU-ID to delete.",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return; // Stop the method
            }

            // Use a PreparedStatement for the DELETE operation
            PreparedStatement psstm = conn.prepareStatement(
                    "DELETE FROM " + TABLE_NAME + " WHERE PSU_ID = ?");
            psstm.setString(1, psuId); // Set the '?' to the psuId to be deleted
            int rowsAffected = psstm.executeUpdate(); // Execute the delete

            // Check if anything was actually deleted
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record for PSU-ID " + psuId + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No record found with PSU-ID " + psuId + ".");
            }
            psstm.close(); // Close the statement

            // Clear the input field
            txtPsuId1.setText("");

            // Refresh the GUI
            loadPsuIds();       // Reload the PSU-ID dropdown
            countStudents();    // Update the total count
            clearDisplayFields(); // Clear the right-side display fields

        } catch (SQLException e) {
            // Handle any SQL errors during deletion
            JOptionPane.showMessageDialog(this, "Error deleting student: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Shuts down the Derby database connection properly and exits the application.
     */
    private void exitApp() {
        try {
            // 1. Close the Statement and Connection
            if (stm != null) stm.close();
            if (conn != null) {
                conn.close();
                // 2. Shut down the Derby embedded database instance
                // This is the standard way to shut down Derby. It's expected to throw an "XJ015" exception.
                try {
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");
                } catch (SQLException se) {
                    // Check if the exception is the expected shutdown signal (XJ015)
                    if (!se.getSQLState().equals("XJ015")) {
                        throw se; // If it's a different error, re-throw it
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print any errors during shutdown to the console
        }
        // 3. Exit the Java application
        System.exit(0);
    }

    /**
     * Loads all PSU-IDs from the database and populates the `comboPsuIds` dropdown.
     */
    private void loadPsuIds() {
        try {
            // Execute a SELECT query to get all PSU_IDs, ordered alphabetically
            ResultSet rs = stm.executeQuery("SELECT PSU_ID FROM " + TABLE_NAME + " ORDER BY PSU_ID");

            // Use an ArrayList to temporarily store the IDs
            ArrayList<String> ids = new ArrayList<>();
            while (rs.next()) { // Loop through all rows in the result
                ids.add(rs.getString(1)); // Add the ID from the first column to the list
            }
            rs.close(); // Close the ResultSet

            // --- Update the JComboBox (dropdown) ---
            comboPsuIds.removeAllItems(); // Clear all old items from the dropdown
            clearDisplayFields();          // Clear the student data display fields

            // Add all the IDs from the ArrayList to the dropdown
            for (String id : ids) {
                comboPsuIds.addItem(id);
            }

            // If no IDs were found, add a 'null' item to avoid a blank dropdown
            if (ids.isEmpty()) {
                comboPsuIds.addItem(null);
                comboPsuIds.setSelectedItem(null);
            }

        } catch (SQLException e) {
            // Handle errors during loading
            JOptionPane.showMessageDialog(this, "Error loading PSU-IDs: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Counts the total number of student records in the table and updates the display field.
     */
    private void countStudents() {
        try {
            // Execute a query to count all rows (*) in the table
            ResultSet rs = stm.executeQuery("SELECT COUNT(*) FROM " + TABLE_NAME);
            if (rs.next()) { // Move to the first (and only) row of the result
                int count = rs.getInt(1); // Get the count from the first column
                txtTotalStudents.setText(String.valueOf(count)); // Update the text field
            }
            rs.close(); // Close the ResultSet
        } catch (SQLException e) {
            // Handle errors during counting
            JOptionPane.showMessageDialog(this, "Error counting students: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads the Name and Gender for a specific PSU-ID and displays them in the right-panel fields.
     *
     * @param psuId The PSU-ID of the student to load.
     */
    private void loadStudentData(String psuId) {
        try {
            // Use a PreparedStatement to select data for a specific PSU_ID
            PreparedStatement psstm = conn.prepareStatement(
                    "SELECT Name, Gender FROM " + TABLE_NAME + " WHERE PSU_ID = ?");
            psstm.setString(1, psuId); // Set the '?' parameter
            ResultSet rs = psstm.executeQuery(); // Execute the query

            if (rs.next()) { // If a record was found
                txtName2.setText(rs.getString("Name"));         // Display the Name
                lblGenderDisplay.setText(rs.getString("Gender")); // Display the Gender
            }
            rs.close();    // Close the ResultSet
            psstm.close(); // Close the PreparedStatement
        } catch (SQLException e) {
            // Handle errors during data loading
            JOptionPane.showMessageDialog(this, "Error loading student data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Helper method to clear the text from the right-side display fields.
     */
    private void clearDisplayFields() {
        txtName2.setText("");
        lblGenderDisplay.setText("-");
    }

    /**
     * The main entry point for the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure that the GUI is created and updated
        // on the main Event Dispatch Thread (EDT). This is the standard, safe way to start a Swing application.
        SwingUtilities.invokeLater(() -> {
            StudentDatabaseGUI gui = new StudentDatabaseGUI(); // Create an instance of our GUI
            gui.setVisible(true); // Make the window visible
        });
    }
}