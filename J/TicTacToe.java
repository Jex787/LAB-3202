import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true; 

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if (!btn.getText().equals("")) {
            return;
        }

        btn.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;

        String winner = checkWin();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "Draw!");
            resetBoard();
        }
    }

    private String checkWin() {
        int[][] lines = {
            {0,1,2}, {3,4,5}, {6,7,8}, 
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}         
        };
        for (int[] line : lines) {
            String a = buttons[line[0]].getText();
            String b = buttons[line[1]].getText();
            String c = buttons[line[2]].getText();
            if (!a.equals("") && a.equals(b) && b.equals(c)) {
                return a;
            }
        }
        return null;
    }

    private boolean isBoardFull() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToe::new);
    }
}
