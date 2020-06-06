public class Main {
    public static void main(String[] args){
        ITicTacToe ticTacToe = new TicTacToe();
        Game game = new Game(ticTacToe);
        game.run();
    }
}
