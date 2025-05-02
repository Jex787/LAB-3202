import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinaryDecimalConverter extends JFrame implements ActionListener {
    private JTextField inputField, resultField;
    private JButton binToDecBtn, decToBinBtn, clearBtn;

    public BinaryDecimalConverter() {
        setTitle("Binary-Decimal Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 5, 5));  
        inputField = new JTextField();
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        inputField.setPreferredSize(new Dimension(300, 40));

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        resultField.setPreferredSize(new Dimension(300, 40));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        binToDecBtn = new JButton("Binary to Decimal");
        decToBinBtn = new JButton("Decimal to Binary");
        clearBtn = new JButton("Clear");

        binToDecBtn.addActionListener(this);
        decToBinBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        buttonPanel.add(binToDecBtn);
        buttonPanel.add(decToBinBtn);
        buttonPanel.add(clearBtn);

        add(new JLabel("Enter Number:"));
        add(inputField);
        add(buttonPanel);
        add(resultField);
            
        setSize(400, 250);              
        setLocationRelativeTo(null);          
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();
        try {
            if (e.getSource() == binToDecBtn) {
                int decimal = Integer.parseInt(input, 2);
                resultField.setText("Decimal: " + decimal);
            } else if (e.getSource() == decToBinBtn) {
                int number = Integer.parseInt(input);
                resultField.setText("Binary: " + Integer.toBinaryString(number));
            } else if (e.getSource() == clearBtn) {
                inputField.setText("");
                resultField.setText("");
            }
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BinaryDecimalConverter::new);
    }
}
