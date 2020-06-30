package tictactoe.frontend;

import com.github.weisj.darklaf.LafManager;
import tictactoe.backend.ITicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ITicTacToeUI, ActionListener {
    private LayoutManager mLayoutManager;
    private ITicTacToe mTicTacToe;
    private Board board;
    private ControlMenu controlMenu;
    private ITurnHandler<Character> mITurnHandler;

    public GUI(ITicTacToe ticTacToe){
        this.mTicTacToe = ticTacToe;
        board = new Board(3,100, this);
        controlMenu = new ControlMenu(this);
        mITurnHandler = new TwoPlayerTurnHandler<>('X', 'O');
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
        pack();
        getContentPane().requestFocusInWindow();
        try {
//            LafManager.install(new DarculaTheme());
            LafManager.install();
        }catch (NoClassDefFoundError error){
            System.out.println("[INFO] If you want a nice look and feel, please use the LAF file");
        }
    }

    @Override
    public void run() {
        initialize();
        setVisible(true);
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
                board.update(mTicTacToe.getBoard());
                mITurnHandler.changeTurn();
                controlMenu.setCurrentTurn(mITurnHandler.getTurn());
                if (mTicTacToe.checkTicTacToe()){
                    controlMenu.showWinner(mTicTacToe.winner());
                    board.disableBoard();
                }else if (mTicTacToe.draw()){
                    controlMenu.showTie();
                }
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
}
