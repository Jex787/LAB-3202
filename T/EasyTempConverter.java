import javax.swing.*;
import java.awt.event.*;

public class EasyTempConverter extends JFrame {
    
    public EasyTempConverter() {
        setTitle("Easy Temp Converter");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new java.awt.FlowLayout());
        
        // Input field
        JTextField tempInput = new JTextField(10);
        add(new JLabel("Enter Temperature:"));
        add(tempInput);
        
        // Buttons for conversion
        JButton toCelsius = new JButton("F → C");
        JButton toFahrenheit = new JButton("C → F");
        add(toCelsius);
        add(toFahrenheit);
        
        // Result label
        JLabel result = new JLabel("Result appears here");
        add(result);
        
        // Button actions
        toCelsius.addActionListener(e -> {
            try {
                double f = Double.parseDouble(tempInput.getText());
                double c = (5.0/9.0) * (f - 32);
                result.setText(String.format("%.1f°F = %.1f°C", f, c));
            } catch (Exception ex) {
                result.setText("Enter a number!");
            }
        });
        
        toFahrenheit.addActionListener(e -> {
            try {
                double c = Double.parseDouble(tempInput.getText());
                double f = (9.0/5.0) * c + 32;
                result.setText(String.format("%.1f°C = %.1f°F", c, f));
            } catch (Exception ex) {
                result.setText("Enter a number!");
            }
        });
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EasyTempConverter().setVisible(true));
    }
}