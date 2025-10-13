import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class CourseEnrollmentGUI implements ActionListener {
    private static JTextField name;
    private static JLabel label1, label2, label3;
    private static JButton button1, button2, button3, button4; // add button 4 for "selected major"
    private static JList<String> listBox;
    private static JComboBox<String> combobox;
    private static int indexList;
    private static String[] majors = {"Computer Science", "Software Engineering",
            "Computer Engineering"};
    private static DefaultListModel<String> model = new DefaultListModel<>();
    public static void main(String[] args) {
        createWindow();
    }
    private static void createWindow() {
        JFrame frame = new JFrame("Course Enrollment GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createUI(frame);
        frame.setSize(560, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private static void createUI(final JFrame frame){
        JPanel panel = new JPanel();
        CourseEnrollmentGUI mylist = new CourseEnrollmentGUI();
//LayoutManager layout = new FlowLayout();
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(layout);
        name = new JTextField("",10);
        name.setEditable(true);
        label1 = new JLabel("Add Course");
        label2 = new JLabel("List of Enrolled Courses");
        label3 = new JLabel("Select your Major");
        JButton button1 = new JButton("Add Course");
        JButton button2 = new JButton("Total Enrolled Courses");
        JButton button3 = new JButton("Drop Course");
        JButton button4 = new JButton("Selected Major");
        button1.addActionListener(mylist);
        button2.addActionListener(mylist);
        button3.addActionListener(mylist);
        button4.addActionListener(mylist);
//JList<String> listBox = new JList<>(numbers);
        model.addElement("Sweng-311");
        model.addElement("Sweng-411");
        model.addElement("CMPSC-221");
        combobox = new JComboBox(majors);                    // remove the declaration of a new combobox here, and used the one alr declared
        combobox.setVisible(true);
        JList<String> listBox = new JList<>(model);
        listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listBox.setVisibleRowCount(3);                       // replace from -1 to 3
        listBox.setSize(250, 250);
        listBox.setVisible(true);
        listBox.setSelectedIndex(0);                         // replace from 3 to 0
        listBox.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                JList list = (JList)e.getSource();
                indexList=((JList<?>) e.getSource()).getSelectedIndex();
                if (list.getSelectedValue() != null)        // add this condition to avoid empty window when dropping course
                    JOptionPane.showMessageDialog(frame,list.getSelectedValue());
            }
        });
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0; gbc.gridy = 0; panel.add(label1, gbc);
        gbc.gridx = 1; gbc.gridy = 0; panel.add(name, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(button1, gbc);
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(label2, gbc);
        gbc.gridx = 0; gbc.gridy = 3;
        JScrollPane scrollPane = new JScrollPane(listBox); // replace
        panel.add(scrollPane, gbc);                        // replace
        gbc.gridx = 0; gbc.gridy = 7;
        panel.add(label3, gbc);
        gbc.gridx = 0; gbc.gridy = 9;
        panel.add(combobox, gbc);
        gbc.gridx = 0; gbc.gridy = 11;
        panel.add(button2, gbc);
        gbc.gridx = 0; gbc.gridy = 12;
        panel.add(button3, gbc);
        gbc.gridx = 0; gbc.gridy = 13;                    // pos for button 4
        panel.add(button4, gbc);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
//JRadioButton theJRB = (JRadioButton) e.getSource();
        String option = e.getSource().toString();
        if (command.equals("Add Course")) {
            String st= name.getText();
            model.addElement(st);
            JOptionPane.showMessageDialog(null, "Course Added");
        } else if (command.equals("Total Enrolled Courses")) {
            int c = model.getSize();
            JOptionPane.showMessageDialog(null, "Total Courses are: " + c);
        } else if (command.equals("Drop Course")) {
            String droppedCourse = model.get(indexList);  // Change these 3 lines
            model.removeElementAt(indexList);
            JOptionPane.showMessageDialog(null, "You have dropped " + droppedCourse);
        } else if (command.equals("Selected Major")) {    // Add this for button 4
            String selectedMajor = (String) combobox.getSelectedItem();
            JOptionPane.showMessageDialog(null, "Your Major is " + selectedMajor);
        }
    }
}