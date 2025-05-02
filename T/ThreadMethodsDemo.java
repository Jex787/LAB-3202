import javax.swing.*;
import java.awt.*;

public class ThreadMethodsDemo extends JFrame {
    private Thread thread;
    private JTextArea output;

    public ThreadMethodsDemo() {
        setTitle("Thread Methods Demo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        output = new JTextArea();
        output.setEditable(false);
        add(new JScrollPane(output), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startBtn = new JButton("Start");
        JButton yieldBtn = new JButton("Yield");
        JButton sleepBtn = new JButton("Sleep");
        JButton stopBtn = new JButton("Stop");

        buttonPanel.add(startBtn);
        buttonPanel.add(yieldBtn);
        buttonPanel.add(sleepBtn);
        buttonPanel.add(stopBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        startBtn.addActionListener(e -> {
            if (thread == null || !thread.isAlive()) {
                thread = new Thread(this::runThread);
                thread.start();
            }
        });

        yieldBtn.addActionListener(e -> {
            if (thread != null) {
                Thread.yield();
                output.append("Yield called\n");
            }
        });

        sleepBtn.addActionListener(e -> {
            if (thread != null) {
                try {
                    output.append("Sleeping for 2 seconds...\n");
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    output.append("Sleep interrupted\n");
                }
            }
        });

        stopBtn.addActionListener(e -> {
            if (thread != null) {
                thread.interrupt();
                output.append("Thread stopped\n");
            }
        });
    }

    private void runThread() {
        try {
            for (int i = 1; i <= 10; i++) {
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
                final int count = i;
                SwingUtilities.invokeLater(() -> 
                    output.append("Running: " + count + "\n"));
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            SwingUtilities.invokeLater(() -> output.append("Thread stopped\n"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ThreadMethodsDemo().setVisible(true));
    }
}