import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.TicTacToe;

import java.util.Random;

public class ITicTacToeTest {
    ITicTacToe ticTacToe = null;

    @Before
    public void setup(){
        ticTacToe = new TicTacToe();
    }

    @Test
    public void getBoard_TicTacToeHasASquareBoardAfterCreateGame_True(){
        ticTacToe.create();
        var board = ticTacToe.getBoard();
        int rand = new Random().nextInt(board.length);
        Assert.assertEquals(board.length, board[rand].length);
    }

    @Test
    public void markMove_canAnEmptySpaceBeMarked_True(){
        ticTacToe.create();
        Assert.assertTrue(ticTacToe.markMove(0,0));
    }

    @Test
    public void markMove_canAnOccupiedSpaceBeMarked_False(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        Assert.assertFalse(ticTacToe.markMove(0,0));
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInDecrescentDiagonal_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,2);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInCrescentDiagonal_True(){
        ticTacToe.create();
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(0,2);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheFirstRow_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(0,2);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheSecondRow_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(0,2);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheThirdRow_True(){
        ticTacToe.create();
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,2);
        boolean thereIsTicTacToe = ticTacToe.checkTicTacToe();
        Assert.assertTrue(thereIsTicTacToe);
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheFirstColumn_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,0);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheSecondColumn_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(2,1);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void checkTicTacToe_ThereIsTicTacToeInTheThirdColumn_True(){
        ticTacToe.create();
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,2);
        Assert.assertTrue(ticTacToe.checkTicTacToe());
    }

    @Test
    public void winner_XWonAfterTheFifthTurn(){
        ticTacToe.create();
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,2);
        char winner = ticTacToe.winner();
        Assert.assertEquals('X', winner);
    }

    @Test
    public void winner_OWonAfterTheSixthTurn(){
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(2,2);
        char winner = ticTacToe.winner();
        Assert.assertEquals('O', winner);
    }

    @Test
    public void draw_GameDrawAfterTheNinthTurn(){
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(1,0);
        ticTacToe.markMove(2,0);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,2);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(1,2);
        Assert.assertTrue(ticTacToe.draw() && !ticTacToe.checkTicTacToe());
    }

    @Test
    public void draw_ThereIsADrawAfterTheEighthTurn_False(){
        ticTacToe.markMove(0,0);
        ticTacToe.markMove(1,1);
        ticTacToe.markMove(0,2);
        ticTacToe.markMove(0,1);
        ticTacToe.markMove(2,1);
        ticTacToe.markMove(1,0);
        ticTacToe.markMove(1,2);
        ticTacToe.markMove(2,0);
        Assert.assertFalse(ticTacToe.draw());
    }

}
