import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BinDecConverter extends JFrame {
    public BinDecConverter() {
        setTitle("Binary-Decimal Converter");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JTextField input = new JTextField(10);
        JButton toDec = new JButton("Bin → Dec");
        JButton toBin = new JButton("Dec → Bin");
        JLabel result = new JLabel("Result appears here");

        add(new JLabel("Input:"));
        add(input);
        add(toDec);
        add(toBin);
        add(result);

        toDec.addActionListener(e -> {
            try {
                String binary = input.getText();
                int decimal = Integer.parseInt(binary, 2);
                result.setText(binary + " in decimal: " + decimal);
            } catch (Exception ex) {
                result.setText("Invalid binary!");
            }
        });

        toBin.addActionListener(e -> {
            try {
                int decimal = Integer.parseInt(input.getText());
                String binary = Integer.toBinaryString(decimal);
                result.setText(decimal + " in binary: " + binary);
            } catch (Exception ex) {
                result.setText("Invalid decimal!");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BinDecConverter().setVisible(true));
    }
} 
