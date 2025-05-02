import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private double firstNum;
    private String operator;

    public Calculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        display.setPreferredSize(new Dimension(300, 50)); 
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel(new GridLayout(4, 4, 4, 4));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.addActionListener(this);
            panel.add(btn);
        }
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.matches("\\d")) { 
            display.setText(display.getText() + cmd);
        } else if ("+-*/".contains(cmd)) {
            firstNum = Double.parseDouble(display.getText());
            operator = cmd;
            display.setText("");
        } else if ("=".equals(cmd)) {
            double secondNum = Double.parseDouble(display.getText());
            double result = 0;
            switch (operator) {
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                case "*":
                    result = firstNum * secondNum;
                    break;
                case "/":
                    result = firstNum / secondNum;
                    break;
            }
            display.setText(String.valueOf(result));
        } else if ("C".equals(cmd)) { 
            display.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
