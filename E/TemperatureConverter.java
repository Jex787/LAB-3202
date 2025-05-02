import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame implements ActionListener {
    private JTextField inputField, resultField;

    //private JButton cToFBtn, fToCBtn, clearBtn;

    private JComboBox<String> conversionBox;
    private JButton convertBtn, clearBtn;

           //.......*****.........//

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 5, 5));

        inputField = new JTextField();
        inputField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        inputField.setPreferredSize(new Dimension(300, 40));

        resultField = new JTextField();
        resultField.setEditable(false);
        resultField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        resultField.setPreferredSize(new Dimension(300, 40));

        /*JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        cToFBtn = new JButton("Celsius to Fahrenheit");
        fToCBtn = new JButton("Fahrenheit to Celsius");
        clearBtn = new JButton("Clear");

        cToFBtn.addActionListener(this);
        fToCBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        buttonPanel.add(cToFBtn);
        buttonPanel.add(fToCBtn);
        buttonPanel.add(clearBtn); */

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        conversionBox = new JComboBox<>(new String[] {
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius"
        });

        convertBtn = new JButton("Convert");
        clearBtn = new JButton("Clear");

        convertBtn.addActionListener(this);
        clearBtn.addActionListener(this);

        buttonPanel.add(conversionBox);
        buttonPanel.add(convertBtn);
        buttonPanel.add(clearBtn);


        //..........***.........//

        add(new JLabel("Enter Temperature:"));
        add(inputField);
        add(buttonPanel);
        add(resultField);

        pack();
        setSize(400, 250);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = inputField.getText().trim();
        try {
            double temp = Double.parseDouble(input);
           /* if (e.getSource() == cToFBtn) {
                double fahrenheit = (temp * 9 / 5) + 32;
                resultField.setText("Fahrenheit: " + String.format("%.2f", fahrenheit));
            } else if (e.getSource() == fToCBtn) {
                double celsius = (temp - 32) * 5 / 9;
                resultField.setText("Celsius: " + String.format("%.2f", celsius));
            } else if (e.getSource() == clearBtn) {
                inputField.setText("");
                resultField.setText("");
            } */
            if (e.getSource() == convertBtn) {
                String selected = (String) conversionBox.getSelectedItem();
                if (selected.equals("Celsius to Fahrenheit")) {
                    double fahrenheit = (temp * 9 / 5) + 32;
                    resultField.setText("Fahrenheit: " + String.format("%.2f", fahrenheit));
                } else if (selected.equals("Fahrenheit to Celsius")) {
                    double celsius = (temp - 32) * 5 / 9;
                    resultField.setText("Celsius: " + String.format("%.2f", celsius));
                }
            }
                          //..........***.........//


        } catch (NumberFormatException ex) {
            resultField.setText("Invalid input");
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();            // use it instead of  SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}
