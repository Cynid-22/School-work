import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseGUI extends JFrame implements ActionListener, ItemListener {

    private static final String DB_NAME = "PennState";
    private static final String CONNECTION_URL = "jdbc:derby:" + DB_NAME + ";create=true";
    private static final String TABLE_NAME_EMP = "Employee";
    private static final String TABLE_NAME_DEPT = "Department";

    private Connection conn;
    private Statement stm;

    private JLabel lblDept, lblEmp;
    private JComboBox<String> comboDept;
    private JList<String> listEmp;
    private DefaultListModel<String> listModel;
    private JButton btnCreateTable, btnInserDept, btnLoadDeptName, btnInserEmp;

    public DatabaseGUI() {
        setTitle("PennState");
        setSize(435, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        lblDept = new JLabel("Departments Names");
        lblDept.setBounds(20, 20, 180, 25);
        add(lblDept);

        lblEmp = new JLabel("List of Employees");
        lblEmp.setBounds(20, 60, 120, 25);
        add(lblEmp);

        comboDept = new JComboBox<>();
        comboDept.setBounds(200, 20, 200, 25);
        add(comboDept);

        listModel = new DefaultListModel<>();
        listEmp = new JList<>(listModel);

        JScrollPane scrollPane = new JScrollPane(listEmp);
        scrollPane.setBounds(200, 60, 200, 80);
        add(scrollPane);

        btnCreateTable = new JButton("Create Tables");
        btnCreateTable.setBounds(20, 160, 185, 35);
        add(btnCreateTable);

        btnInserDept = new JButton("Insert Departments");
        btnInserDept.setBounds(215, 160, 184, 35);
        add(btnInserDept);

        btnLoadDeptName = new JButton("Load Departments Names");
        btnLoadDeptName.setBounds(20, 205, 185, 35);
        add(btnLoadDeptName);

        btnInserEmp = new JButton("Insert Employee");
        btnInserEmp.setBounds(215, 205, 184, 35);
        add(btnInserEmp);

        btnCreateTable.addActionListener(this);
        btnInserDept.addActionListener(this);
        btnLoadDeptName.addActionListener(this);
        btnInserEmp.addActionListener(this);

        comboDept.addItemListener(this);

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
            conn = DriverManager.getConnection(CONNECTION_URL);
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
        } else if (source == btnInserDept) {
            insertDepartment();
        } else if (source == btnLoadDeptName) {
            loadDepartmentNames();
        } else if (source == btnInserEmp) {
            insertEmployee();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == comboDept && e.getStateChange() == ItemEvent.SELECTED) {
            String selectedDeptName = (String) e.getItem();
            if (selectedDeptName != null && !selectedDeptName.isEmpty()) {
                loadEmployeesByDept(selectedDeptName);
            }
        }
    }

    private void createTable() {
        String createDept = "CREATE TABLE " + TABLE_NAME_DEPT + " ("
                + "deptNo INT PRIMARY KEY, "
                + "deptName VARCHAR(100), "
                + "location VARCHAR(100)"
                + ")";

        String createEmp = "CREATE TABLE " + TABLE_NAME_EMP + " ("
                + "empNo INT PRIMARY KEY, "
                + "empName VARCHAR(100), "
                + "Dept_ID INT"
                + ")";

        try (Statement stm = conn.createStatement()) {
            if (!tableExists(TABLE_NAME_DEPT)) {
                stm.executeUpdate(createDept);
                JOptionPane.showMessageDialog(this, TABLE_NAME_DEPT + " created.");
            } else {
                JOptionPane.showMessageDialog(this, TABLE_NAME_DEPT + " Table already created.");
            }

            if (!tableExists(TABLE_NAME_EMP)) {
                stm.executeUpdate(createEmp);
                JOptionPane.showMessageDialog(this, TABLE_NAME_EMP + " created.");
            } else {
                JOptionPane.showMessageDialog(this, TABLE_NAME_EMP + " Table already created.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error creating tables: " + e.getMessage());
        }
    }

    private boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet rs = dbm.getTables(null, null, tableName.toUpperCase(), null);
        boolean exists = rs.next();
        rs.close();
        return exists;
    }

    private void insertDepartment() {
        try (Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_DEPT + " VALUES (10, 'Computer Science', 'Burke Center')");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_DEPT + " VALUES (20, 'Electrical Engineering', 'AMIC')");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_DEPT + " VALUES (30, 'Computer Enginering', 'Burke Center')");

            JOptionPane.showMessageDialog(this, "Departments Inserted.");

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(this, "Department(s) already exist", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void loadDepartmentNames() {
        comboDept.removeItemListener(this);
        comboDept.removeAllItems();

        try (Statement localStm = conn.createStatement();
             ResultSet rs = localStm.executeQuery("SELECT deptName FROM " + TABLE_NAME_DEPT)) {

            while (rs.next()) {
                comboDept.addItem(rs.getString("deptName"));
            }
            JOptionPane.showMessageDialog(this, "Departments Loaded.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading departments: " + e.getMessage());
        } finally {
            comboDept.addItemListener(this);
        }
    }

    private void insertEmployee() {
        try (Statement stm = conn.createStatement()) {
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (1, 'Wang', 10)");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (2, 'Rasooli', 20)");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (3, 'Hussain', 10)");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (4, 'Naseem', 10)");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (5, 'Syed', 20)");
            stm.executeUpdate("INSERT INTO " + TABLE_NAME_EMP + " VALUES (6, 'Brain', 30)");


            JOptionPane.showMessageDialog(this, "Employees Inserted and Loaded.");

            String currentSelection = (String) comboDept.getSelectedItem();
            if (currentSelection != null) {
                loadEmployeesByDept(currentSelection);
            }

        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                JOptionPane.showMessageDialog(this, "Employee(s) already exist", "Duplicate Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }
    }

    private void loadEmployeesByDept(String deptName) {
        listModel.clear();
        try (Statement localStm = conn.createStatement()) {
            String sql = "SELECT empName FROM " + TABLE_NAME_EMP +
                    " WHERE Dept_ID = (SELECT deptNo FROM " + TABLE_NAME_DEPT + " WHERE deptName = '" + deptName + "')";

            try (ResultSet rs = localStm.executeQuery(sql)) {
                while (rs.next()) {
                    listModel.addElement(rs.getString("empName"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                        se.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DatabaseGUI gui = new DatabaseGUI();
            gui.setVisible(true);
        });
    }
}