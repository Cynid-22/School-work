import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator implements ActionListener {

    private static JTextField screen;

    public static void main(String[] args) {
        createWindow();
    }

    private static void createWindow() {
        JFrame frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI(frame);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void createUI(JFrame frame) {
        JPanel panel = new JPanel();
        Calculator calculatorListener = new Calculator();

        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gbc = new GridBagConstraints();

        // Setup the screen.
        screen = new JTextField("0", 15);
        screen.setEditable(false);
        screen.setHorizontalAlignment(JTextField.RIGHT);
        screen.setFont(new Font("SansSerif", Font.BOLD, 28));

        String[] buttonLabels = {
                "mc", "mr", "m-", "m+",
                "AC", "√x", "%", "÷",
                "7", "8", "9", "×",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "+/-", "=",
                "π", "xʸ", "R2", "R0"
        };

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Add the screen to the top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(screen, gbc);

        // Reset gridwidth and create buttons in a loop.
        gbc.gridwidth = 1;
        int row = 1;
        int col = 0;
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("SansSerif", Font.BOLD, 16));
            button.addActionListener(calculatorListener);

            gbc.gridx = col;
            gbc.gridy = row;
            panel.add(button, gbc);

            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }

        frame.getContentPane().add(panel, BorderLayout.CENTER);
    }

    private boolean isNewNumber = true; // will it delete te screen to input a new number?
    private String operator = "";
    private double firstVar = 0;
    private double memory = 0; // memory var for mc, mr, +m, -m

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String currentText = screen.getText();
        double displayValue = 0;

        if (!currentText.equals("Error. Press AC")) {
            displayValue = Double.parseDouble(currentText); // get text from the screen to use for the next calculation
        }

        // Number and Decimal
        if (command.matches("[0-9]")) {
            if (isNewNumber || currentText.equals("0")) {
                screen.setText(command);
                isNewNumber = false; // keep the current number and append the new number
            } else {
                screen.setText(currentText + command);
            }
        } else if (command.equals(".")) {
            if (!currentText.contains(".")) {
                screen.setText(currentText + ".");
            }
            // Operator
            } else if (command.matches("[×÷\\-+]") || command.matches("xʸ")) {
            calculate(); // does not activate on the first time due to there being no "operator"
            firstVar = Double.parseDouble(screen.getText());
            operator = command;
            isNewNumber = true;
            // Equals
        } else if (command.equals("=")) {
            calculate();
            operator = ""; // reset to state before pressing any "operators"
            isNewNumber = true;
            // Clear
        } else if (command.equals("AC")) {
            screen.setText("0");
            firstVar = 0;
            operator = ""; // reset to state before pressing any "operators"
            isNewNumber = true;
            // Unary
        } else if (command.equals("+/-")) {
            screen.setText(formatResult(displayValue * -1));
        } else if (command.equals("√x")) {
            if (displayValue >= 0) {
                screen.setText(formatResult(Math.sqrt(displayValue)));
            } else {
                screen.setText("Error. Press AC");
            }
            isNewNumber = true;
        } else if (command.equals("%")) {
            screen.setText(formatResult(displayValue / 100.0));
            isNewNumber = true;
            // Constants and Rounding
        } else if (command.equals("π")) {
            screen.setText("3.1415926536");
            isNewNumber = true;
        } else if (command.equals("R0")) {
            screen.setText(String.valueOf(Math.round(displayValue)));
            isNewNumber = true;
        } else if (command.equals("R2")) {
            screen.setText(String.format("%.2f", displayValue));
            isNewNumber = true;
            // Memory Functions
        } else if (command.equals("mc")) {
            memory = 0;
        } else if (command.equals("mr")) {
            screen.setText(formatResult(memory)); // output memory
            isNewNumber = true;
        } else if (command.equals("m+")) {
            memory += displayValue;
            isNewNumber = true;
        } else if (command.equals("m-")) {
            memory -= displayValue;
            isNewNumber = true;
        }
    }

    private void calculate() {
        if (operator.isEmpty() || isNewNumber) return; // do nothing

        double secondVar = Double.parseDouble(screen.getText());
        double result = 0;

        if (operator.equals("÷")) {
            if (secondVar == 0) {
                screen.setText("Error. Press AC");
                operator = "";
                isNewNumber = true;
                return;
            }
            result = firstVar / secondVar;
        } else if (operator.equals("×")) {
            result = firstVar * secondVar;
        } else if (operator.equals("-")) {
            result = firstVar - secondVar;
        } else if (operator.equals("+")) {
            result = firstVar + secondVar;
        } else if (operator.equals("xʸ")) {
            result = Math.pow(firstVar, secondVar);
        }

        screen.setText(formatResult(result));
    }

    // Format the result to avoid unnecessary ".0" for whole numbers
    private String formatResult(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        } else {
            return String.valueOf(number);
        }
    }
}
