import javax.swing.*;
import java.awt.*;

public class SimpleThreadDemo extends JFrame {
    public SimpleThreadDemo() {
        setTitle("Thread Creation Demo");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Thread will count here:");
        JTextArea output = new JTextArea(5, 20);
        output.setEditable(false);
        JButton startButton = new JButton("Start Thread");

        add(label);
        add(new JScrollPane(output));
        add(startButton);

        startButton.addActionListener(e -> {
            Thread countingThread = new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    final int count = i;
                    SwingUtilities.invokeLater(() -> 
                        output.append("Count: " + count + "\n"));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        output.append("Thread interrupted\n");
                        return;
                    }
                }
            });
            countingThread.start();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleThreadDemo().setVisible(true));
    }
}