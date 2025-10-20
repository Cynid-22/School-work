import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryManagementGUI extends JFrame implements ActionListener {

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JLabel titleLabel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LibraryManagementGUI frame = new LibraryManagementGUI();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    public LibraryManagementGUI() {
        setTitle("Library Management System");
        setSize(500, 400);
        setLayout(new BorderLayout());

        setJMenuBar(createMenuBar());

        titleLabel = new JLabel("Select an option from the menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createBooksPanel(), "Books");
        cardPanel.add(createFacultyPanel(), "Faculty");
        cardPanel.add(createStudentPanel(), "Students");

        add(cardPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // book
        JMenu booksMenu = new JMenu("Books");
        JMenuItem newBook = new JMenuItem("New_Book");
        JMenuItem deleteBook = new JMenuItem("Delete_Book");
        JMenuItem viewBook = new JMenuItem("View_Book");

        newBook.addActionListener(this);
        deleteBook.addActionListener(this);
        viewBook.addActionListener(this);

        booksMenu.add(newBook);
        booksMenu.add(deleteBook);
        booksMenu.add(viewBook);

        // faculty
        JMenu facultyMenu = new JMenu("Faculty");
        JMenuItem newFaculty = new JMenuItem("New_Faculty");
        JMenuItem deleteFaculty = new JMenuItem("Delete_Faculty");
        JMenuItem viewFaculty = new JMenuItem("View_Faculty");

        newFaculty.addActionListener(this);
        deleteFaculty.addActionListener(this);
        viewFaculty.addActionListener(this);

        facultyMenu.add(newFaculty);
        facultyMenu.add(deleteFaculty);
        facultyMenu.add(viewFaculty);

        // student
        JMenu studentsMenu = new JMenu("Students");
        JMenuItem newStudent = new JMenuItem("New_Student");
        JMenuItem deleteStudent = new JMenuItem("Delete_Student");
        JMenuItem viewStudent = new JMenuItem("View_Student");

        newStudent.addActionListener(this);
        deleteStudent.addActionListener(this);
        viewStudent.addActionListener(this);

        studentsMenu.add(newStudent);
        studentsMenu.add(deleteStudent);
        studentsMenu.add(viewStudent);

        menuBar.add(booksMenu);
        menuBar.add(facultyMenu);
        menuBar.add(studentsMenu);

        return menuBar;
    }

    private JPanel createBooksPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Book Name:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Author Name:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Edition Number:"));
        formPanel.add(new JTextField(15));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton newButton = new JButton("New");
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View");

        newButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        buttonPanel.add(newButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createFacultyPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Faculty Name:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Department:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Office Number:"));
        formPanel.add(new JTextField(15));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton newButton = new JButton("New");
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View");

        newButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        buttonPanel.add(newButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(new JTextField(15));
        formPanel.add(new JLabel("Major:"));
        formPanel.add(new JTextField(15));

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton newButton = new JButton("New");
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View");

        newButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        buttonPanel.add(newButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewButton);

        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle Menu Item Clicks
        if (command.equals("New_Book") || command.equals("Delete_Book") || command.equals("View_Book")) {
            cardLayout.show(cardPanel, "Books");
            titleLabel.setText("Navigation from " + command + " Menubar");
        } else if (command.equals("New_Faculty") || command.equals("Delete_Faculty") || command.equals("View_Faculty")) {
            cardLayout.show(cardPanel, "Faculty");
            titleLabel.setText("Navigation from " + command + " Menubar");
        } else if (command.equals("New_Student") || command.equals("Delete_Student") || command.equals("View_Student")) {
            cardLayout.show(cardPanel, "Students");
            titleLabel.setText("Navigation from " + command + " Menubar");
        }

        else if (command.equals("New") || command.equals("Delete") || command.equals("View")) {
            JOptionPane.showMessageDialog(this, "Button Clicked: " + command);
        }
    }
}