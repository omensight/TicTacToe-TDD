import java.util.Scanner;

public class Game {
    private TicTacToe ticTacToe;
    private boolean running;
    Scanner scanner;

    public Game(TicTacToe ticTacToe){
        this.ticTacToe = ticTacToe;
        running = true;
        scanner = new Scanner(System.in);
    }
    public void showMainMenu(){
        System.out.println("What would you like to do?");
        System.out.println("1. Play");
        System.out.println("2. Exit");
        System.out.print("Option: ");
        int option = scanner.nextInt();
        switch (option){
            case 1:
                startGame();
                break;
            case 2:
                running = false;
                break;
            default:
                System.out.println("Invalid option\n");
                showMainMenu();
        }
    }

    private void startGame() {
        while (ticTacToe.isGameInProgress()){
            drawBoard();
            inputData();
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        char winner = ticTacToe.checkWinner();
        if (winner != '\u0000' ){
            System.out.println("The winner is: " + winner);
            running = false;
            showMainMenu();
        }else if (!ticTacToe.isGameInProgress() && ticTacToe.getTurn() == 9){
            System.out.println("There was a tie");
            showMainMenu();
        }else if (!ticTacToe.isGameInProgress() && ticTacToe.getTurn() <= 9){
            System.out.println("The Game was interrupted");
            showMainMenu();
        }
    }

    private void inputData() {
        System.out.println("It is the turn of " + ticTacToe.getSymbolOfCurrentPlayer());
        System.out.println("If you want to exit to the main menu, please enter 10 at any of the coordinates");
        System.out.println("Enter the x coordinate of the current turn");
        int x = scanner.nextInt();
        if (x == 10){
            ticTacToe.stop();
            return;
        }
        System.out.println("Enter the y coordinate of the current turn");
        int y = scanner.nextInt();
        if (y == 10){
            ticTacToe.stop();
            return;
        }
        ticTacToe.checkBox(x, y);

    }

    private void drawBoard(){
        char[][] board =ticTacToe.peekBoard();
        for (int i = 0; i<board.length; i++){
            for (int j = 0; j<board.length; j++){
                char symbol = board[i][j];
                if (symbol == 0){
                    System.out.print('-');
                }else {
                    System.out.print(symbol);
                }
            }
            System.out.println();
        }
    }

}
