import javax.swing.*;     // For GUI components like JFrame, JButton
import java.awt.*;        // For layout (GridLayout)

// Main class extending JFrame (a window in Swing)
public class TTT extends JFrame {

    // 9 buttons for the 3x3 grid
    JButton[] buttons = new JButton[9];

    // Keeps track of current turn (true = X, false = O)
    boolean xTurn = true;

    // Constructor: Sets up the GUI and game board
    public TTT() {
        super("Tic-Tac-Toe");              // Set window title
        setSize(300, 300);                 // Window size
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Close app on exit
        setLayout(new GridLayout(3, 3));   // 3x3 grid layout

        // Create and add buttons to the frame
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();                            // Create button
            buttons[i].setFont(new Font("Arial", Font.BOLD, 50)); // Large font
            final int index = i;                                   // Make index effectively final for lambda
            buttons[i].addActionListener(e -> click(index));       // Add click listener
            add(buttons[i]);                                       // Add button to frame
        }

        setVisible(true);  // Make the window visible
    }

    // This method is called when a button is clicked
    void click(int i) {
        // If button already clicked, do nothing
        if (!buttons[i].getText().isEmpty()) return;

        // Set X or O based on whose turn it is
        buttons[i].setText(xTurn ? "X" : "O");

        // Check for win or draw
        if (win()) {
            JOptionPane.showMessageDialog(this, buttons[i].getText() + " wins!");
            reset(); // Start new game
        } else if (draw()) {
            JOptionPane.showMessageDialog(this, "Draw!");
            reset(); // Start new game
        } else {
            xTurn = !xTurn; // Switch turns
        }
    }

    // Method to check for a winning combination
    boolean win() {
        // All possible win positions (rows, columns, diagonals)
        int[][] w = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},  // rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},  // columns
                {0, 4, 8}, {2, 4, 6}              // diagonals
        };

        // Check if any winning combination is met
        for (int[] line : w) {
            String a = buttons[line[0]].getText();
            String b = buttons[line[1]].getText();
            String c = buttons[line[2]].getText();

            if (!a.isEmpty() && a.equals(b) && b.equals(c)) {
                return true; // Win found
            }
        }
        return false; // No win
    }

    // Method to check if all buttons are filled (draw)
    boolean draw() {
        for (JButton btn : buttons) {
            if (btn.getText().isEmpty()) return false;
        }
        return true;
    }

    // Reset the board and start a new game
    void reset() {
        for (JButton btn : buttons) {
            btn.setText(""); // Clear button text
        }
        xTurn = true; // Set turn back to X
    }

    // Main method to launch the game
    public static void main(String[] args) {
        new TTT(); // Create game window and start the game
    }
}
