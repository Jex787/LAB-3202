import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ThreadOperations extends JFrame {
    private Thread thread;
    private JLabel counterLabel;
    private int count = 0;

    public ThreadOperations() {
        setTitle("Thread Operations");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        counterLabel = new JLabel("Count: 0");
        counterLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(counterLabel);

        JButton start = new JButton("Start");
        JButton suspend = new JButton("Suspend");
        JButton resume = new JButton("Resume");
        JButton stop = new JButton("Stop");

        add(start);
        add(suspend);
        add(resume);
        add(stop);

        start.addActionListener(e -> {
            thread = new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        Thread.sleep(1000);
                        SwingUtilities.invokeLater(() -> {
                            count++;
                            counterLabel.setText("Count: " + count);
                        });
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            thread.start();
        });

        suspend.addActionListener(e -> {
            if (thread != null) thread.suspend();
        });

        resume.addActionListener(e -> {
            if (thread != null) thread.resume();
        });

        stop.addActionListener(e -> {
            if (thread != null) thread.interrupt();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThreadOperations().setVisible(true));
    }
}