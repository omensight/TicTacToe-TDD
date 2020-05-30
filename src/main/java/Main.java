public class Main {
    public static void main(String[] args){
        TicTacToe ticTacToe = new TicTacToeMatrix();
        Game game = new Game(ticTacToe);
        game.showMainMenu();
    }
}
