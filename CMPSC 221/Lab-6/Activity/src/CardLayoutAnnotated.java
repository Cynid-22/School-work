// Import necessary AWT (Abstract Window Toolkit) classes for layouts and events
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Import Swing components (JFrame, JPanel, JButton, etc.)
import javax.swing.*;
// These imports are redundant (already imported above) but harmless
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * This class creates a main window (JFrame) to demonstrate CardLayout.
 * NOTE: Naming the class "CardLayout" is confusing because it's the same
 * name as the layout manager "java.awt.CardLayout". It's better to
 * name it something like "CardLayoutDemo".
 */
public class CardLayoutAnnotated extends JFrame {

    // A unique ID for serialization (common for JFrames, often auto-generated)
    private static final long serialVersionUID = 1L;

    // --- Declare GUI Components ---
    // The main panel that will hold the "cards"
    private static JPanel cardPanel;
    // The two "cards" which are themselves JPanels
    private static JPanel panel1, panel2;
    // A separate panel to hold the control buttons
    private static JPanel buttonPanel;
    // Text fields for the cards
    private static JTextField text1, text2, text3;
    // Labels for the cards
    private static JLabel label1, label2;
    // Buttons to switch cards and exit
    private static JButton button1, button2, button3;

    // --- Declare the Layout Manager ---
    // This is the actual CardLayout manager object.
    // We use the full path "java.awt.CardLayout" to avoid conflict
    // with our class name.
    private static java.awt.CardLayout cardLayout = new java.awt.CardLayout();

    /**
     * Constructor: This is where the GUI is built.
     */
    public CardLayoutAnnotated() {
        // --- Set up the main window (the JFrame) ---
        setTitle("CardLayout GUI"); // Set the window's title
        setSize(400, 300);          // Set the window's size (width, height)

        // --- Create the Panels ---
        cardPanel = new JPanel();   // Create the panel that will hold the cards
        buttonPanel = new JPanel(); // Create the panel that will hold the buttons

        // --- Configure the Card Panel ---
        // ** CRITICAL STEP **
        // Tell cardPanel to use our cardLayout object as its layout manager
        cardPanel.setLayout(cardLayout);

        // --- Create Card 1 (panel1) ---
        panel1 = new JPanel();          // Create the first card
        label1 = new JLabel("Card 1");  // Create a label for it
        text1 = new JTextField(10);     // Create a text field
        panel1.add(label1);             // Add the label to the first card
        panel1.add(text1);              // Add the text field to the first card

        // --- Create Card 2 (panel2) ---
        panel2 = new JPanel();          // Create the second card
        label2 = new JLabel("Card 2");  // Create a label for it
        text2 = new JTextField(10);     // Create text field 1
        text3 = new JTextField(10);     // Create text field 2
        panel2.add(label2);             // Add label to the second card
        panel2.add(text2);              // Add text field 1 to the second card
        panel2.add(text3);              // Add text field 2 to the second card

        // --- Add the "cards" to the cardPanel ---
        // ** CRITICAL STEP **
        // Add panel1 to cardPanel and give it the name "1"
        cardPanel.add(panel1, "1");
        // Add panel2 to cardPanel and give it the name "2"
        cardPanel.add(panel2, "2");

        // --- Create and Configure Buttons ---
        button1 = new JButton("Show Card 1"); // Create button 1
        // Add an "anonymous" ActionListener (code that runs on click)
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // ** CRITICAL STEP **
                // Tell the layout manager to show the card named "1"
                cardLayout.show(cardPanel, "1");
            }
        });

        button2 = new JButton("Show Card 2"); // Create button 2
        // Add an ActionListener
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tell the layout manager to show the card named "2"
                cardLayout.show(cardPanel, "2");
            }
        });

        button3 = new JButton("Exit"); // Create the exit button
        // Add an ActionListener
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit the entire application
                System.exit(0);
            }
        });

        // --- Add buttons to the buttonPanel ---
        buttonPanel.add(button1);
        buttonPanel.add(button2);
        buttonPanel.add(button3);

        // --- Add the main panels to the JFrame ---
        // The JFrame's default layout is BorderLayout.
        // Add the cardPanel to the top (NORTH)
        add(cardPanel, BorderLayout.NORTH);
        // Add the buttonPanel to the center (it will fill the remaining space)
        add(buttonPanel, BorderLayout.CENTER);
    }

    /**
     * main method: The entry point of the application.
     */
    public static void main(String[] args) {
        // Use EventQueue.invokeLater to start the GUI on the
        // Event Dispatch Thread (EDT). This is the standard, safe
        // way to start a Swing application.
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of our GUI window
                CardLayout frame = new CardLayout();
                // Tell the program to exit when the window's 'X' is clicked
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Make the window visible
                frame.setVisible(true);
            }
        });
    }

    /**
     * This is an unused, empty method. It has the same name as the
     * "show" method from the CardLayout manager, which is confusing.
     * It doesn't do anything and can be safely removed.
     */
    public void show(JPanel cards, String one) {
        // This method is empty and unused.
    }
}