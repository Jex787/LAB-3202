import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class SimpleClient extends JFrame {
    private PrintWriter out;
    private JTextArea chatArea;

    public SimpleClient() {
        setTitle("Simple Chat Client");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        try {
            Socket socket = new Socket("localhost", 1234);
            out = new PrintWriter(socket.getOutputStream(), true);
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                    String response;
                    while ((response = in.readLine()) != null) {
                        chatArea.append(response + "\n");
                    }
                } catch (IOException e) {
                    chatArea.append("Connection error\n");
                }
            }).start();
        } catch (IOException e) {
            chatArea.append("Failed to connect to server\n");
        }

        sendButton.addActionListener(e -> {
            String message = inputField.getText();
            out.println(message);
            chatArea.append("You: " + message + "\n");
            inputField.setText("");
        });

        inputField.addActionListener(e -> sendButton.doClick());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleClient().setVisible(true));
    }
}