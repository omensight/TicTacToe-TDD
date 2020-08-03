import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;
import tictactoe.controller.TwoPlayerTurnHandler;

public class TicTacToeTest {

    private ITicTacToe ticTacToe;

    @Before
    public void setUp() {
        ticTacToe = new TicTacToe();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createBoard(){
        ticTacToe.create();
        Assert.assertNotNull(ticTacToe.getBoard());
    }

    @Test
    public void markMoveXValid(){
        ticTacToe.create();
        boolean status = ticTacToe.markMove(1,1);
        Assert.assertTrue(status);
    }

    @Test
    public void markMoveOValid(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        boolean status = ticTacToe.markMove(1,1);
        Assert.assertTrue(status);
    }

    @Test
    public void markMoveXRemarkNotValid(){
        ticTacToe.create();
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(2,2);
        boolean status = ticTacToe.markMove(2,2);
        Assert.assertFalse(status);
    }

    @Test
    public void markMoveORemarkNotValid(){
        ticTacToe.create();
        ticTacToe.markMove(1,2);
        boolean status = ticTacToe.markMove(1,2);
        Assert.assertFalse(status);
    }

    @Test
    public void markMoveXLimitRowAndColumnNotValid(){
        
        ticTacToe.create();
        boolean status = ticTacToe.markMove(5,3);
        Assert.assertFalse(status);
    }

    @Test
    public void checkTicTacToeRowValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(1,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(1,2);
        boolean thereAreTicTacToe = ticTacToe.checkTicTacToe();
        Assert.assertTrue(thereAreTicTacToe);
    }

    @Test
    public void checkTicTacToeColumnValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,1);
        boolean thereAreTicTacToe = ticTacToe.checkTicTacToe();
        Assert.assertTrue(thereAreTicTacToe);
    }

    @Test
    public void checkTicTacToeDiagonalValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,0);
        boolean thereAreTicTacToe = ticTacToe.checkTicTacToe();
        Assert.assertTrue(thereAreTicTacToe);
    }

    @Test
    public void checkTicTacToeNotValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(1,2);
        boolean thereAreTicTacToe = ticTacToe.checkTicTacToe();
        Assert.assertFalse(thereAreTicTacToe);
    }

    @Test
    public void winnerXRow(){
        
        ticTacToe.create();
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,2);
        ticTacToe.checkTicTacToe();
        char winner = ticTacToe.winner();
        Assert.assertEquals('X', winner);
    }

    @Test
    public void winnerXColumn(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(2,2);
        ticTacToe.checkTicTacToe();
        char winner = ticTacToe.winner();
        Assert.assertEquals('O', winner);
    }

    @Test
    public void winnerXDiagonal(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(2,2);
        ticTacToe.checkTicTacToe();
        char winner = ticTacToe.winner();
        Assert.assertEquals('X', winner);
    }

    @Test
    public void drawValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,0);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(2,1);
        boolean isDraw = ticTacToe.draw();
        Assert.assertTrue(isDraw);
    }

    @Test
    public void drawNotValid(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(2,2);
        ticTacToe.checkTicTacToe();
        boolean isDraw = ticTacToe.draw();
        Assert.assertFalse(isDraw);
    }

    @Test
    public void getBoardEmpty(){
        
        ticTacToe.create();
        char[][] board1 = {{'\0','\0','\0'},{'\0','\0','\0'},{'\0','\0','\0'}};
        char[][] board2 = ticTacToe.getBoard();
        Assert.assertArrayEquals(board1, board2);
    }

    @Test
    public void getBoardHalfFull(){
        
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,2);
        char[][] board1 = {{'X','X','X'},{'\0','O','\0'},{'\0','O','\0'}};
        char[][] board2 = ticTacToe.getBoard();
        Assert.assertArrayEquals(board1, board2);
    }

}