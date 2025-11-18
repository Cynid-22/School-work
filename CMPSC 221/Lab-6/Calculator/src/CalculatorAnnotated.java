// Import necessary AWT packages for layout, font, and event handling
import java.awt.BorderLayout; // For arranging components (e.g., panel in the frame)
import java.awt.Font;          // For setting the font of the screen and buttons
import java.awt.GridBagConstraints; // For specifying constraints for GridBagLayout
import java.awt.GridBagLayout;   // For arranging components in a flexible grid
import java.awt.Insets;        // For adding padding around components
import java.awt.event.ActionEvent; // Object that describes what action occurred
import java.awt.event.ActionListener; // Interface for receiving action events (like button clicks)

// Import Swing packages for GUI components
import javax.swing.JButton;    // For the calculator buttons
import javax.swing.JFrame;     // For the main application window
import javax.swing.JPanel;     // For holding all the components
import javax.swing.JTextField; // For the calculator display "screen"

/**
 * The main Calculator class.
 * It implements ActionListener to handle all button clicks itself.
 */
public class CalculatorAnnotated implements ActionListener {

    // The calculator's display screen. Declared as 'static' so it can be
    // accessed by the static 'createUI' method.
    private static JTextField screen;

    /**
     * The main entry point of the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Start the process of creating the GUI.
        createWindow();
    }

    /**
     * Creates and configures the main application window (JFrame).
     */
    private static void createWindow() {
        // Create the main window with the title "Calculator".
        JFrame frame = new JFrame("Calculator");
        // Set the 'X' button to close and exit the application.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Call the method to create and add all the UI components (screen, buttons)
        createUI(frame);
        // Set the size of the window.
        frame.setSize(300, 400);
        // Center the window on the screen.
        frame.setLocationRelativeTo(null);
        // Make the window visible.
        frame.setVisible(true);
    }

    /**
     * Creates and lays out all the GUI components inside the main frame.
     *
     * @param frame The main JFrame to which the UI will be added.
     */
    private static void createUI(JFrame frame) {
        // Create a main panel to hold all components.
        JPanel panel = new JPanel();
        // Create an instance of this Calculator class to use as the ActionListener
        // for all the buttons.
        Calculator calculatorListener = new Calculator();

        // --- Set up the GridBagLayout ---
        // This layout manager allows flexible arrangement of components in a grid.
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        // Create a 'constraints' object to define how each component is placed.
        GridBagConstraints gbc = new GridBagConstraints();

        // --- Setup the calculator screen ---
        screen = new JTextField("0", 15); // Initial text is "0", preferred width 15 columns
        screen.setEditable(false);                   // User cannot type directly into the screen
        screen.setHorizontalAlignment(JTextField.RIGHT); // Align text to the right
        screen.setFont(new Font("SansSerif", Font.BOLD, 28)); // Set a large, bold font

        // --- Define all button labels in order ---
        String[] buttonLabels = {
                "mc", "mr", "m-", "m+",
                "AC", "√x", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "+/-", "=",
                "π", "xʸ", "R2", "R0"
        };

        // --- Configure GridBagConstraints for buttons ---
        // Make components fill their grid cell horizontally.
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Add 5px padding around all components.
        gbc.insets = new Insets(5, 5, 5, 5);

        // --- Add the screen to the top of the grid ---
        gbc.gridx = 0; // Start at column 0
        gbc.gridy = 0; // Start at row 0
        gbc.gridwidth = 4; // Make the screen span 4 columns
        panel.add(screen, gbc); // Add the screen to the panel with these constraints

        // --- Create and add all buttons in a loop ---
        gbc.gridwidth = 1; // Reset gridwidth for buttons (each takes 1 column)
        int row = 1; // Start adding buttons from row 1 (below the screen)
        int col = 0; // Start adding buttons from column 0
        for (String label : buttonLabels) {
            JButton button = new JButton(label); // Create a new button with the label
            button.setFont(new Font("SansSerif", Font.BOLD, 16)); // Set button font
            // Add the *same* listener instance to *every* button.
            // The actionPerformed method will figure out which button was clicked.
            button.addActionListener(calculatorListener);

            // Set the grid position for this button
            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(button, gbc); // Add the button to the panel

            // Move to the next column
            col++;
            // If we've reached the end of a row (after 4 columns)...
            if (col > 3) {
                col = 0; //...reset to the first column
                row++;   //...and move to the next row
            }
        }

        // Add the fully populated panel to the center of the frame's content area.
        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    // --- Instance variables for calculator logic ---

    // Tracks if the next number typed should replace the screen (true)
    // or be appended to it (false).
    private boolean isNewNumber = true;
    // Stores the pending operation (e.g., "+", "÷").
    private String operator = "";
    // Stores the first number in a calculation (e.g., the '12' in "12 + 5").
    private double firstVar = 0;
    // Stores the value for the memory functions (m+, m-, mr, mc).
    private double memory = 0;

    /**
     * This single method is called whenever ANY button is clicked.
     *
     * @param e The ActionEvent object containing details about the click,
     * like which button was pressed.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the text from the button that was clicked (e.g., "7", "+", "AC").
        String command = e.getActionCommand();
        // Get the current text displayed on the screen.
        String currentText = screen.getText();
        // This will hold the numeric value of the text on the screen.
        double displayValue = 0;

        // Only parse the text if it's not in an error state.
        if (!currentText.equals("Error. Press AC")) {
            // Convert the screen's text into a 'double' number for calculations.
            displayValue = Double.parseDouble(currentText);
        }

        // --- Logic to handle different button types ---

        // 1. Number Buttons (0-9)
        if (command.matches("[0-9]")) {
            // If it's a new number (after an operator or "=") or screen is just "0",
            // replace the screen text with the new digit.
            if (isNewNumber || currentText.equals("0")) {
                screen.setText(command);
                isNewNumber = false; // It's no longer a new number
            } else {
                // Otherwise, append the new digit to the existing number.
                screen.setText(currentText + command);
            }
        }
        // 2. Decimal Point Button
        else if (command.equals(".")) {
            // Only add a decimal point if one doesn't already exist.
            if (!currentText.contains(".")) {
                screen.setText(currentText + ".");
            }
        }
        // 3. Operator Buttons (+, -, ×, ÷, xʸ)
        else if (command.matches("[×÷\\-+]") || command.matches("xʸ")) {
            // Perform any pending calculation first (e.g., if user types "5 + 2 +")
            calculate();
            // Store the *current* screen value as the first variable.
            firstVar = Double.parseDouble(screen.getText());
            // Store the operator that was just pressed.
            operator = command;
            // Set flag so the *next* number input will be a new number.
            isNewNumber = true;
        }
        // 4. Equals Button
        else if (command.equals("=")) {
            calculate(); // Perform the final calculation.
            operator = ""; // Clear the operator.
            isNewNumber = true; // Next number typed will be a new one.
        }
        // 5. All Clear (AC) Button
        else if (command.equals("AC")) {
            screen.setText("0"); // Reset screen to 0.
            firstVar = 0;        // Reset first variable.
            operator = "";       // Reset operator.
            isNewNumber = true;  // Reset new number flag.
        }
        // 6. Unary Operations (operate on the current display value)
        else if (command.equals("+/-")) {
            // Toggle the sign (positive/negative).
            screen.setText(formatResult(displayValue * -1));
        } else if (command.equals("√x")) {
            // Calculate square root.
            if (displayValue >= 0) { // Check for valid input
                screen.setText(formatResult(Math.sqrt(displayValue)));
            } else {
                screen.setText("Error. Press AC"); // Error for negative numbers
            }
            isNewNumber = true; // Result is a new number
        } else if (command.equals("%")) {
            // Convert to percentage (divide by 100).
            screen.setText(formatResult(displayValue / 100.0));
            isNewNumber = true;
        }
        // 7. Constants and Rounding
        else if (command.equals("π")) {
            screen.setText("3.1415926536"); // Set screen to Pi
            isNewNumber = true;
        } else if (command.equals("R0")) {
            // Round to 0 decimal places (a whole number).
            screen.setText(String.valueOf(Math.round(displayValue)));
            isNewNumber = true;
        } else if (command.equals("R2")) {
            // Round to 2 decimal places.
            screen.setText(String.format("%.2f", displayValue));
            isNewNumber = true;
        }
        // 8. Memory Functions
        else if (command.equals("mc")) {
            memory = 0; // Memory Clear: set memory to 0
        } else if (command.equals("mr")) {
            // Memory Recall: set screen to memory value
            screen.setText(formatResult(memory));
            isNewNumber = true;
        } else if (command.equals("m+")) {
            // Memory Add: add current display value to memory
            memory += displayValue;
            isNewNumber = true;
        } else if (command.equals("m-")) {
            // Memory Subtract: subtract current display value from memory
            memory -= displayValue;
            isNewNumber = true;
        }
    }

    /**
     * Performs the pending calculation using 'firstVar', 'operator',
     * and the current screen value as the second variable.
     */
    private void calculate() {
        // If there's no operator or we're in the middle of typing a new number,
        // do nothing.
        if (operator.isEmpty() || isNewNumber) return;

        // Get the second number from the screen.
        double secondVar = Double.parseDouble(screen.getText());
        double result = 0;

        // Perform the correct operation based on the stored 'operator'.
        if (operator.equals("÷")) {
            if (secondVar == 0) { // Check for division by zero
                screen.setText("Error. Press AC");
                operator = "";
                isNewNumber = true;
                return; // Exit the method early
            }
            result = firstVar / secondVar;
        } else if (operator.equals("×")) {
            result = firstVar * secondVar;
        } else if (operator.equals("-")) {
            result = firstVar - secondVar;
        } else if (operator.equals("+")) {
            result = firstVar + secondVar;
        } else if (operator.equals("xʸ")) {
            result = Math.pow(firstVar, secondVar); // x to the power of y
        }

        // Display the result on the screen, formatted nicely.
        screen.setText(formatResult(result));
    }

    /**
     * A helper method to format the result.
     * It removes the ".0" from whole numbers (e.g., 12.0 becomes "12").
     *
     * @param number The number to format.
     * @return A formatted string.
     */
    private String formatResult(double number) {
        // Check if the number is a whole number (e.g., 12.0)
        if (number == (long) number) {
            // If yes, format it as a long integer (no decimal)
            return String.format("%d", (long) number);
        } else {
            // Otherwise, convert the double to a string as-is
            return String.valueOf(number);
        }
    }
}