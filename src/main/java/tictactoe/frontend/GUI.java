package tictactoe.frontend;

import tictactoe.backend.IObservableTicTacToe;
import tictactoe.controller.MyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUI extends JFrame implements ITicTacToeUI{
    private final IObservableTicTacToe game;
    private final ConsoleHelper consoleHelper;
    private final static int WIDTH = 9;
    private final static int TOTAL_LENGTH = WIDTH*WIDTH;
    private JButton[] buttons;
    private JLabel labelTurn;

    public GUI(IObservableTicTacToe game) {
        this.game = game;
        this.game.addListener(this);
        consoleHelper = new ConsoleHelper();
    }

    @Override
    public void run() {
        startFrame();
        startLabelTurn();
        startButtons();
        startButtonNewGame();
        setVisible(true);
    }

    @Override
    public void update(MyEvent event) {
        if(event.getPropertyName().equals("markMove")){
            updateBoard();
            checkStatusGame();
        }
        if(event.getPropertyName().equals("create")){
            newGame();
        }
    }

    private void startFrame() {
        setTitle("TIC TAC TOE 2.0");
        setSize (350,420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation (this.EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void startLabelTurn() {
        JPanel panelLabelMessage = new JPanel();
        labelTurn = new JLabel("Turn X");
        labelTurn.setFont(new Font("Dialogo", Font.BOLD,16));
        panelLabelMessage.add(labelTurn);
        add(panelLabelMessage, BorderLayout.NORTH);
    }

    private void startButtons() {
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new GridLayout(9,9,1,1));
        //panelButtons.setBackground(Color.BLACK);
        buttons = new JButton[TOTAL_LENGTH];
        for (int i = 0; i < TOTAL_LENGTH; i++) {
            JButton button = new JButton();
            button.setActionCommand(Integer.toString(i));
            button.setBackground(Color.WHITE);
            button.setFocusPainted(false);
            button.setFont(new Font("Dialogo", Font.BOLD,20));
            button.setBorder(new LineBorder(null));
//            button.setPreferredSize(new Dimension(100,100));
            button.setToolTipText("Click to mark");
            button.addActionListener(event -> play(event.getActionCommand()));
            buttons[i] = button;
            panelButtons.add(buttons[i]);
        }
        add(panelButtons);
    }

    private void startButtonNewGame(){
        JPanel panelButtonNewGame = new JPanel();
        JButton buttonNewGame = new JButton("New Game");
        //buttonNewGame.setBackground(Color.GREEN);
        buttonNewGame.setFont(new Font("Dialogo", Font.BOLD,13));
        buttonNewGame.addActionListener((event) -> game.create());
        panelButtonNewGame.add(buttonNewGame);
        add(panelButtonNewGame, BorderLayout.SOUTH);
    }

    private void play(String numberPlay) {
        int number = Integer.parseInt(numberPlay);
        if(!game.checkTicTacToe()){
            if(!game.markMove(convertRow(number),convertColumn(number))) {
                buttons[number].setFocusPainted(true);
            }
        }
    }

    private void checkStatusGame() {
        if(game.checkTicTacToe()){
            String winnerOut = consoleHelper.messageWinnerGame(String.valueOf(game.winner()));
            labelTurn.setText(winnerOut);
            setButtonEnable(false);
        }else{
            if (game.draw()){
                labelTurn.setText(consoleHelper.messageDrawGame());
                setButtonEnable(false);
            }
        }
    }

    public void updateBoard() {
        changeLabelTurn();
        for (int i = 0; i < TOTAL_LENGTH; i++) {
            setButtonMove(i);
        }
    }

    private void setButtonMove(int number){
        String piece = getBox(convertRow(number),convertColumn(number));
        buttons[number].setText(piece);
        if(piece.equals("X")){
            Color xColor = Color.decode("#AC87B4");
            buttons[number].setForeground(xColor);
        }else{
            if(piece.equals("O")){
                Color oColor = Color.decode("#289395");
                buttons[number].setForeground(oColor);
            }else{
                buttons[number].setEnabled(true);
            }
        }
    }

    private String getBox(int row, int column) {
        char [][] boardPlay = game.getBoard();
        return String.valueOf(boardPlay[row][column]);
    }

    private void newGame() {
        labelTurn.setText("Turn X");
        setButtonEnable(true);
        setButtonEmpty();
    }

    private void setButtonEnable(boolean status) {
        for (int i = 1; i < 10; i++) {
            buttons[i].setEnabled(status);
        }
    }

    private void setButtonEmpty() {
        for (int i = 1; i < 10; i++) {
            buttons[i].setText("");
        }
    }

    private void changeLabelTurn(){
        if(labelTurn.getText().equals("Turn X")){
            labelTurn.setText("Turn O");
        }else{
            labelTurn.setText("Turn X");
        }
    }

    private int convertRow(int number){
        return (number) / 9;
    }

    private int convertColumn(int number){
        return (number) % 9;
    }
}