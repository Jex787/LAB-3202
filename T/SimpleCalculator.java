import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame {
    private JTextField display;
    private double firstNum, secondNum, result;
    private String operation;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setEditable(false);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        String[] buttons = {"7", "8", "9", "/",
                           "4", "5", "6", "*",
                           "1", "2", "3", "-",
                           "C", "0", "=", "+"};

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            
            if (command.matches("[0-9]")) {
                display.setText(display.getText() + command);
            } else if (command.matches("[+\\-*/]")) {
                firstNum = Double.parseDouble(display.getText());
                operation = command;
                display.setText("");
            } else if (command.equals("=")) {
                secondNum = Double.parseDouble(display.getText());
                switch (operation) {
                    case "+": result = firstNum + secondNum; break;
                    case "-": result = firstNum - secondNum; break;
                    case "*": result = firstNum * secondNum; break;
                    case "/": result = firstNum / secondNum; break;
                }
                display.setText(String.valueOf(result));
            } else if (command.equals("C")) {
                display.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator().setVisible(true));
    }
}