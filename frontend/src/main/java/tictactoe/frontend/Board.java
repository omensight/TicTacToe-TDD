package tictactoe.frontend;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Board extends JPanel{

    private final int dimension;
    private final int boxWidth;
    private final ActionListener mActionListener;

    public Board(int dimension, int boxWidth, ActionListener actionListener) throws HeadlessException {
        this.dimension = dimension;
        this.boxWidth = boxWidth;
        this.mActionListener = actionListener;
        setLayout(new GridLayout(dimension,dimension));
        var limit = dimension*dimension;
        for (int i = 0; i < limit ; i++){
            JButton button = new JButton();
            button.setActionCommand(String.valueOf(i));
            button.setSize(boxWidth, boxWidth);
            button.setFont(new Font("Roboto",Font.PLAIN, 40));
            button.addActionListener(mActionListener);
            add(button);
        }

    }

    public void disableBoard(){
        var limit = dimension * dimension;
        var buttons = getComponents();
        for (int i = 0; i < limit ; i++) {
            JButton button = (JButton) buttons[i];
            button.setEnabled(false);
        }
    }

    public void update(char[][] boardState){
        var components = getComponents();
        for (int row = 0; row < dimension; row++){
            for (int column = 0; column < dimension; column++){
                int position = row*dimension + column%dimension;
                JButton button = (JButton) components[position];
                char symbol = boardState[row][column];
                button.setEnabled(symbol == 0);
                button.setText(String.valueOf(symbol));
                button.setFocusPainted(false);
            }
        }
    }
}
