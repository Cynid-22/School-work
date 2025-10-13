import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListTransferGUI implements ActionListener {
    private static JFrame frame;
    private static JPanel panel;
    private static JList<String> list1;
    private static JList<String> list2;
    private static DefaultListModel<String> model1;
    private static DefaultListModel<String> model2;
    private static JButton addButton, addAllButton, removeButton, removeAllButton;

    public static void main(String[] args) {
        createUI();
    }

    public static void createUI() {
        frame = new JFrame("List Transfer GUI");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        ListTransferGUI mylist = new ListTransferGUI();

        model1 = new DefaultListModel<>();
        model2 = new DefaultListModel<>();

        model1.addElement("CMPSC");
        model1.addElement("MATH");
        model1.addElement("PHYS");
        model1.addElement("COMM");

        list1 = new JList<>(model1);
        list2 = new JList<>(model2);

        addButton = new JButton("Add >");
        addAllButton = new JButton("Add All >>");
        removeButton = new JButton("< Remove");
        removeAllButton = new JButton("<< Remove All");

        addButton.addActionListener(mylist);
        addAllButton.addActionListener(mylist);
        removeButton.addActionListener(mylist);
        removeAllButton.addActionListener(mylist);

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("List Box 1"), gbc);

        gbc.gridx = 2; gbc.gridy = 0;
        panel.add(new JLabel("List Box 2"), gbc);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;

        gbc.gridx = 0; gbc.gridy = 1; gbc.gridheight = 4;
        panel.add(new JScrollPane(list1), gbc);

        gbc.gridx = 2; gbc.gridy = 1; gbc.gridheight = 4;
        panel.add(new JScrollPane(list2), gbc);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5)); // 4 rows, 1 col, 5px gaps
        buttonPanel.add(addButton);
        buttonPanel.add(addAllButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton);

        gbc.gridx = 1; gbc.gridy = 1; gbc.gridheight = 4;
        panel.add(buttonPanel, gbc);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Add >" -> {
                String selectedValue = list1.getSelectedValue();
                if (selectedValue == null)
                    JOptionPane.showMessageDialog(frame, "Please select element first");
                else if (!model2.contains(selectedValue))
                    model2.addElement(selectedValue);
            }
            case "Add All >>" -> {
                for (int i = 0; i < model1.getSize(); i++) {
                    String item = model1.getElementAt(i);
                    if (!model2.contains(item)) // Avoid dupes
                        model2.addElement(item);
                }
            }
            case "< Remove" -> {
                int selectedIndex = list2.getSelectedIndex();
                if (selectedIndex == -1)
                    JOptionPane.showMessageDialog(frame, "Please select element first");
                else
                    model2.remove(selectedIndex);
            }
            case "<< Remove All" -> model2.clear();
        }
    }
}