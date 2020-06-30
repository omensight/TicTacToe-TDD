package tictactoe.frontend;

import tictactoe.backend.ITicTacToe;

import java.util.Scanner;

public class Console implements ITicTacToeUI {
    private final ITicTacToe ticTacToe;
    private final ITurnHandler<Character> mTurnHandler;
    private final Scanner scanner;
    private boolean gameInProgress;

    public Console(ITicTacToe ticTacToe){
        this.ticTacToe = ticTacToe;
        mTurnHandler = new TwoPlayerTurnHandler<>('X', 'O');
        scanner = new Scanner(System.in);
    }
    private void showMainMenu(){
        System.out.println("---------------------------------------\n");
        System.out.println("What would you like to do?");
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("Option: ");
        int option = scanner.nextInt();
        switch (option){
            case 1:
                gameInProgress = true;
                ticTacToe.create();
                mTurnHandler.reset();
                startLoop();
                break;
            case 2:
                gameInProgress = false;
                break;
            default:
                System.out.println("Invalid option");
                showMainMenu();
                break;
        }
    }

    private void startLoop(){
        while (!ticTacToe.draw() && !ticTacToe.checkTicTacToe() && gameInProgress){
            drawBoard();
            inputData();
            checkGameStatus();
        }
    }

    @Override
    public void run() {
        showMainMenu();
    }

    private void checkGameStatus() {
        if (ticTacToe.checkTicTacToe()){
            char winner = ticTacToe.winner();
            System.out.println("The winner is: " + winner);
            drawBoard();
            showMainMenu();
        }else if (ticTacToe.draw()){
            System.out.println("There was a tie");
            showMainMenu();
        }
    }

    private void inputData() {
        System.out.println("It is the turn of " + mTurnHandler.getTurn());
        System.out.println("If you want to exit to the main menu, please enter 10 at any of the coordinates");
        System.out.println("Enter the x coordinate of the current turn");
        int x = scanner.nextInt();
        if (x == 10){
            gameInProgress = false;
            showMainMenu();
            return;
        }
        System.out.println("Enter the y coordinate of the current turn");
        int y = scanner.nextInt();
        if (y == 10){
            gameInProgress = false;
            showMainMenu();
            return;
        }
        if (ticTacToe.markMove(x, y)){
            mTurnHandler.changeTurn();
        }else {
            System.err.println("Invalid mark, please enter a correct one");
        }
    }

    private void drawBoard(){
        System.out.println("\n····································");
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


}
