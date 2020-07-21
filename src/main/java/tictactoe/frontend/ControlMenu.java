package tictactoe.frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

class ControlMenu extends JPanel {

    private final JButton restartButton;
    private final JLabel messageIndicator;

    public ControlMenu(ActionListener actionListener) {
        setBorder(new EmptyBorder(10,10,10,10));
        messageIndicator = new JLabel("", null, JLabel.CENTER);
        messageIndicator.setHorizontalTextPosition(JLabel.CENTER);
        messageIndicator.setVerticalTextPosition(JLabel.BOTTOM);
        restartButton = new JButton("Restart");
        restartButton.setForeground(Color.decode("#173F5F"));
        restartButton.setActionCommand("restart");
        restartButton.addActionListener(actionListener);
        restartButton.setFocusPainted(false);
        setLayout(new BorderLayout());
        initialize();
    }

    private void initialize() {
        var preferredSize = new Dimension();
        preferredSize.width = 480;
        preferredSize.height = 480;
        setPreferredSize(preferredSize);
        add(messageIndicator, BorderLayout.PAGE_START);
        add(restartButton, BorderLayout.PAGE_END);
    }

    public void setCurrentTurn(char currentTurnSymbol){
        messageIndicator.setText("It's " + currentTurnSymbol + "'s turn");
        messageIndicator.setForeground(Color.decode("#538642"));
        messageIndicator.repaint();
    }

    public void showWinner(char winner) {
        if (winner != 0){
            messageIndicator.setText(winner + " won");
        }
    }

    public void showTie() {
        messageIndicator.setText("There was a tie. Try again!");
    }
}
