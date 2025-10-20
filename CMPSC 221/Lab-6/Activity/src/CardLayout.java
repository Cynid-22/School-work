import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class CardLayout extends JFrame {
    private static final long serialVersionUID = 1L;
    private static JPanel cardPanel, panel1, panel2, buttonPanel;
    private static JTextField text1, text2, text3;
    private static JLabel label1, label2;
    private static JButton button1, button2, button3;
    private static java.awt.CardLayout cardLayout = new java.awt.CardLayout();
    public CardLayout() {
        setTitle("CardLayout GUI");
        setSize(400, 300);
        cardPanel = new JPanel();
        buttonPanel = new JPanel();
        cardPanel.setLayout(cardLayout);
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel("Card 1");
        label2 = new JLabel("Card 2");
        text1 = new JTextField(10);
        text2 = new JTextField(10);
        text3 = new JTextField(10);
        panel1.add(label1);
        panel1.add(text1);
        panel2.add(label2);
        panel2.add(text2);
        panel2.add(text3);
        cardPanel.add(panel1, "1");
        cardPanel.add(panel2, "2");
        button1 = new JButton("Show Card 1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "1");
            }
        });
        button2 = new JButton("Show Card 2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "2");
            }
        });
        button3 = new JButton("Exit");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);
        add(cardPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                CardLayout frame = new CardLayout();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
    public void show(JPanel cards, String one) {
    }
}