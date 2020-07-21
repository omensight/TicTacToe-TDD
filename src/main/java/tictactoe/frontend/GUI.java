package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GUI extends JFrame implements ITicTacToeUI, ActionListener, PropertyChangeListener {
    private LayoutManager mLayoutManager;
    private ITicTacToe mTicTacToe;
    private Board board;
    private ControlMenu controlMenu;
    private tictactoe.frontend.ITurnHandler<Character> mITurnHandler;

    public GUI(ITicTacToe ticTacToe){
        this.mTicTacToe = ticTacToe;
        board = new Board(3,100, GUI.this);
        controlMenu = new ControlMenu(GUI.this);
        mITurnHandler = new tictactoe.frontend.TwoPlayerTurnHandler<>('X', 'O');
    }

    private void initialize() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");
        setResizable(false);
        mLayoutManager = new BorderLayout();
        setLayout(mLayoutManager);
        setSize(720, 480);
        add(controlMenu);
        add(board, BorderLayout.LINE_END);
        controlMenu.setCurrentTurn(mITurnHandler.getTurn());
        board.setPreferredSize(new Dimension(480,480));
        controlMenu.setPreferredSize(new Dimension(240,480));
        System.out.println("Second" + Thread.currentThread().getName());
        getContentPane().requestFocusInWindow();
    }

    @Override
    public void run() {
        initialize();
        pack();
        setVisible(true);
        mTicTacToe.addListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int numberCommand = -1;
        String wordCommand = "";
        try{
            numberCommand = Integer.parseInt(e.getActionCommand());
        }catch (NumberFormatException exception){
            wordCommand = e.getActionCommand();
        }

        if (numberCommand != -1){
            if (mTicTacToe.markMove(numberCommand/3, numberCommand%3)){
                updateUI();
            }
        }else if (!wordCommand.equals("")){
            switch (wordCommand){
                case "restart":{
                    mTicTacToe.create();
                    mITurnHandler.reset();
                    board.update(mTicTacToe.getBoard());
                    controlMenu.setCurrentTurn(mITurnHandler.getTurn());
                }
            }
        }
    }

    private void updateUI() {


        if (mTicTacToe.checkTicTacToe()){

        }else if (mTicTacToe.draw()){
            controlMenu.showTie();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()){
            case "create":{
                mITurnHandler.reset();
                board.update(mTicTacToe.getBoard());
                controlMenu.setCurrentTurn(mITurnHandler.getTurn());
                break;
            }
            case "markMove": {
                board.update(mTicTacToe.getBoard());
                mITurnHandler.changeTurn();
                controlMenu.setCurrentTurn(mITurnHandler.getTurn());
                break;
            }
            case "winner":{
                controlMenu.showWinner(mTicTacToe.winner());
                board.disableBoard();
                break;
            }

        }
    }

}
