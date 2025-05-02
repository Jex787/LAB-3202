import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField inputField, resultField;
    private JButton convertBtn, clearBtn;
    private JComboBox<String> fromBox, toBox;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 5, 5));

        inputField = new JTextField();
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        resultField = new JTextField();
        resultField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        resultField.setEditable(false);

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromBox = new JComboBox<>(units);
        toBox = new JComboBox<>(units);

        JPanel comboPanel = new JPanel();
        comboPanel.add(new JLabel("From: "));
        comboPanel.add(fromBox);
        comboPanel.add(new JLabel("To: "));
        comboPanel.add(toBox);

        JPanel buttonPanel = new JPanel();
        convertBtn = new JButton("Convert");
        clearBtn = new JButton("Clear");
        convertBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        buttonPanel.add(convertBtn);
        buttonPanel.add(clearBtn);

        add(new JLabel("Enter Temperature:"));
        add(inputField);
        add(comboPanel);
        add(buttonPanel);
        add(resultField);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clearBtn) {
            inputField.setText("");
            resultField.setText("");
            return;
        }

        try {
            double value = Double.parseDouble(inputField.getText().trim());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();
            double result = convertTemperature(value, from, to);
            resultField.setText(String.format("%.2f %s", result, to));
        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    private double convertTemperature(double value, String from, String to) {
        if (from.equals(to)) return value;

        switch (from + " to " + to) {
            case "Celsius to Fahrenheit": return (value * 9/5) + 32;
            case "Celsius to Kelvin": return value + 273.15;
            case "Fahrenheit to Celsius": return (value - 32) * 5/9;
            case "Fahrenheit to Kelvin": return ((value - 32) * 5/9) + 273.15;
            case "Kelvin to Celsius": return value - 273.15;
            case "Kelvin to Fahrenheit": return ((value - 273.15) * 9/5) + 32;
            default: return value;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}
