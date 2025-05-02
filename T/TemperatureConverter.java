import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureConverter extends JFrame {
    private JTextField inputField;
    private JLabel resultLabel;
    private JRadioButton celsiusToFahrenheitRadio;
    private JRadioButton fahrenheitToCelsiusRadio;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        // Input panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter temperature:"));
        inputField = new JTextField(10);
        inputPanel.add(inputField);
        add(inputPanel);

        // Radio buttons for conversion type
        ButtonGroup conversionGroup = new ButtonGroup();
        celsiusToFahrenheitRadio = new JRadioButton("Celsius to Fahrenheit", true);
        fahrenheitToCelsiusRadio = new JRadioButton("Fahrenheit to Celsius");
        conversionGroup.add(celsiusToFahrenheitRadio);
        conversionGroup.add(fahrenheitToCelsiusRadio);

        JPanel radioPanel = new JPanel();
        radioPanel.add(celsiusToFahrenheitRadio);
        radioPanel.add(fahrenheitToCelsiusRadio);
        add(radioPanel);

        // Convert button
        JButton convertButton = new JButton("Convert");
        add(convertButton);

        // Result display
        resultLabel = new JLabel("Result will appear here", SwingConstants.CENTER);
        add(resultLabel);

        // Action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double inputTemp = Double.parseDouble(inputField.getText());
                    double result;
                    if (celsiusToFahrenheitRadio.isSelected()) {
                        result = fahrenheit(inputTemp);
                        resultLabel.setText(inputTemp + "°C = " + result + "°F");
                    } else {
                        result = celsius(inputTemp);
                        resultLabel.setText(inputTemp + "°F = " + result + "°C");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number");
                }
            }
        });
    }

    // Method to convert Celsius to Fahrenheit
    public static double fahrenheit(double celsius) {
        return 9.0 / 5.0 * celsius + 32;
    }

    // Method to convert Fahrenheit to Celsius
    public static double celsius(double fahrenheit) {
        return 5.0 / 9.0 * (fahrenheit - 32);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TemperatureConverter().setVisible(true);
            }
        });
    }
}