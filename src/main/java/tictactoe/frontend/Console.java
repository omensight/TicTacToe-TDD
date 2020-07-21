package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Console implements ITicTacToeUI, IObserver, PropertyChangeListener {
    private final ITicTacToe ticTacToe;
    private final tictactoe.frontend.ITurnHandler<Character> mTurnHandler;
    private Scanner scanner;
    private Status status;
    private InputHelper inputHelper;
    private boolean running;

    public Console(ITicTacToe ticTacToe){
        System.out.println("Main" + Thread.currentThread().getName());
        this.ticTacToe = ticTacToe;
        mTurnHandler = new tictactoe.frontend.TwoPlayerTurnHandler<>('X', 'O');
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        scanner = new Scanner(inputStreamReader);
        status = Status.NOT_STARTED;
        inputHelper = new InputHelper();
        running = true;
    }

    private void loop(){
        while (running){
            String line = scanner.nextLine();
            int parameter = inputHelper.parseInt(line);
            if (status == Status.NOT_STARTED){

            }else if (status == Status.PLAYING){
                performMove(parameter);
            }else if (status == Status.MAIN_MENU){
                performMainMenuAction(parameter);
            }
        }
    }

    private void performMainMenuAction(int move) {
        switch (move){
            case 1:{
                status = Status.PLAYING;
                ticTacToe.create();
                break;
            }
            case 2:{
                status = Status.MAIN_MENU;
                running = false;
                System.exit(0);
                break;
            }
        }
    }

    private void performMove(int option) {
        if (option >8){
            status = Status.MAIN_MENU;
            showMainMenu();
        }else {
            int x = option/3;
            int y = option%3;
            if (!ticTacToe.markMove(x,y)){
                drawBoard();
                showPlayingOptions();
            }
            ticTacToe.checkTicTacToe();
        }
    }

    private void showMainMenu(){
        System.out.println("---------------------------------------\n");
        System.out.println("What would you like to do?");
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("Your selection: ");
    }

    @Override
    public void run() {
        ticTacToe.addListener(this);
        showMainMenu();
        status = Status.MAIN_MENU;
        loop();
    }


    private void drawBoard(){
        System.out.println();
        System.out.println("----------- New Turn -----------");
        char[][] board =ticTacToe.getBoard();
        for (char[] chars : board) {
            for (char symbol : chars) {
                if (symbol == 0) {
                    System.out.print('-');
                } else {
                    System.out.print(symbol);
                }
            }
            System.out.println();
        }
    }

    private void showPlayingOptions() {
        System.out.println("It is the turn of " + mTurnHandler.getTurn());
        System.out.println("[0-8] Mark");
        System.out.println("[Other] Main Menu");
        System.out.print("Select: ");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case "create":{
                status = Status.PLAYING;
                mTurnHandler.reset();
                drawBoard();
                showPlayingOptions();
                break;
            }
            case "markMove": {
                status = Status.PLAYING;
                mTurnHandler.changeTurn();
                drawBoard();
                showPlayingOptions();
                break;
            }
            case "winner":{
                status = Status.MAIN_MENU;
                System.out.println("The winner is: " + ticTacToe.winner());
                showMainMenu();
                break;
            }

        }
    }
}
