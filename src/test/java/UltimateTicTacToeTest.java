import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.UltimateTicTacToe;

public class UltimateTicTacToeTest {
    ITicTacToe ultimateTicTacToe;

    @Before
    public void setup() {
        ultimateTicTacToe = new UltimateTicTacToe();
    }

    @Test
    public void markMove_CanBeMarkedInsideBounds_True() {
        Assert.assertTrue(ultimateTicTacToe.markMove(8, 8));
    }

    @Test
    public void markMove_CanBeMarkedOutsideBounds_False() {
        Assert.assertFalse(ultimateTicTacToe.markMove(10, 10));
    }

    @Test
    public void getBoard_getAnEmptyNineByNineBoard_True() {
        char[][] board = ultimateTicTacToe.getBoard();
        int boardRows = board.length;
        int index = 0;
        boolean hasSameLength = true;
        while (index < UltimateTicTacToe.LENGTH && hasSameLength) {
            hasSameLength = board[index].length == UltimateTicTacToe.LENGTH;
            index++;
        }
        Assert.assertTrue(boardRows == UltimateTicTacToe.LENGTH && hasSameLength);
    }

    @Test
    public void getBoard_markMoveAndGetXAtThatBox_True() {
        ultimateTicTacToe.markMove(1, 1);
        char[][] board = ultimateTicTacToe.getBoard();
        Assert.assertEquals('X', board[1][1]);
    }

    @Test
    public void markMove_canALockedQuadrantBeChecked_False(){
        ultimateTicTacToe.markMove(7,7);
        Assert.assertFalse(ultimateTicTacToe.markMove(7,8));
    }
}
