import tictactoe.backend.ITicTacToe;
import java.util.Scanner;

public class Console {
    private final ITicTacToe ticTacToe;
    private final static char ODD = 'X';
    private final static char EVEN = 'O';
    private char turn = ODD;
    private final Scanner scanner;

    public Console(ITicTacToe ticTacToe){
        this.ticTacToe = ticTacToe;
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
                run();
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid option");
                showMainMenu();
        }
    }

    public void run() {
        ticTacToe.create();
        while (!ticTacToe.draw() && !ticTacToe.checkTicTacToe()){
            drawBoard();
            inputData();
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (ticTacToe.checkTicTacToe()){
            char winner = ticTacToe.winner();
            System.out.println("The winner is: " + winner);
            showMainMenu();
        }else if (ticTacToe.draw()){
            System.out.println("There was a tie");
        }
    }

    private void inputData() {
        System.out.println("It is the turn of " + turn);
        System.out.println("If you want to exit to the main menu, please enter 10 at any of the coordinates");
        System.out.println("Enter the x coordinate of the current turn");
        int x = scanner.nextInt();
        if (x == 10){
            showMainMenu();
            return;
        }
        System.out.println("Enter the y coordinate of the current turn");
        int y = scanner.nextInt();
        if (y == 10){
            showMainMenu();
            return;
        }
        if (ticTacToe.markMove(x, y)){
            turn = turn == ODD ? EVEN : ODD;
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
