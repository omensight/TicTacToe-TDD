import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.ITicTacToe;
import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.backend.UltimateTicTacToe;
import tictactoe.controller.TwoPlayerTurnHandler;

public class UltimateTicTacToeTest {
    IUltimateTicTacToe ultimateTicTacToe;

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
        Assert.assertFalse(ultimateTicTacToe.markMove(20, 10));
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
        var marked = ultimateTicTacToe.markMove(1, 1);
        char[][] board = ultimateTicTacToe.getBoard();
        Assert.assertEquals('X', board[1][1]);
    }

    @Test
    public void getBoard_markMoveAndGetXAtThatBox00_True() {
        var marked = ultimateTicTacToe.markMove(0, 0);
        char[][] board = ultimateTicTacToe.getBoard();
        Assert.assertEquals('X', board[0][0]);
    }

    @Test
    public void markMove_canALockedQuadrantBeChecked_False(){
        ultimateTicTacToe.markMove(7,7);
        Assert.assertFalse(ultimateTicTacToe.markMove(7,7));
    }

    @Test
    public void markMove_markZerothQuadrantAt11Position_True(){
        Assert.assertTrue(ultimateTicTacToe.markMove(0,1,1));
    }

    @Test
    public void markMove_markFourthQuadrantAt00AfterToMarkZerothQuadrantIn11_True(){
        ultimateTicTacToe.markMove(0,1,1);
        Assert.assertTrue(ultimateTicTacToe.markMove(4,1,1));
    }

    @Test
    public void markMove_markSeventhQuadrantAt11AfterToMarkZerothQuadrantIn11_False(){
        ultimateTicTacToe.markMove(0,1,1);
        Assert.assertFalse(ultimateTicTacToe.markMove(7,1,1));
    }

    @Test
    public void markMove_mark55_True(){
        Assert.assertTrue(ultimateTicTacToe.markMove(5,5));
    }

    @Test
    public void getBoard_mark55AndGetXAtThatPosition_True(){
        ultimateTicTacToe.markMove(5,5);
        var board = ultimateTicTacToe.getBoard();
        Assert.assertEquals('X', board[5][5]);
    }

    @Test
    public void getGlobalBoard_11IsOAfterTheSixthMove_True(){
        ultimateTicTacToe.markMove(1,1);
        ultimateTicTacToe.markMove(4,3);
        ultimateTicTacToe.markMove(4,1);
        ultimateTicTacToe.markMove(5,3);
        ultimateTicTacToe.markMove(7,1);
        ultimateTicTacToe.markMove(3,3);
        var board = ultimateTicTacToe.getGlobalBoard();
        Assert.assertEquals('O',board[1][1]);
    }

    @Test
    public void getGlobalBoard_10IsOAfterTheEleventhMove_True(){
        boolean a = ultimateTicTacToe.markMove(1,1);
        boolean b = ultimateTicTacToe.markMove(4,3);
        boolean c = ultimateTicTacToe.markMove(4,1);
        boolean d = ultimateTicTacToe.markMove(5,3);
        boolean e = ultimateTicTacToe.markMove(7,1);
        boolean f = ultimateTicTacToe.markMove(3,3);

        boolean g = ultimateTicTacToe.markMove(1,0);
        boolean h = ultimateTicTacToe.markMove(5, 0);
        boolean i = ultimateTicTacToe.markMove(7,0);
        boolean j = ultimateTicTacToe.markMove(5,1);
        boolean k = ultimateTicTacToe.markMove(7,3);
        boolean l = ultimateTicTacToe.markMove(5,2);
        var board = ultimateTicTacToe.getGlobalBoard();
        Assert.assertEquals('O',board[1][0]);
    }


    @Test
    public void getGlobalBoard_02IsOAfterTheEleventhMove_True(){
        ultimateTicTacToe.markMove(1,1);
        ultimateTicTacToe.markMove(4,3);
        ultimateTicTacToe.markMove(4,1);
        ultimateTicTacToe.markMove(5,3);
        ultimateTicTacToe.markMove(7,1);
        ultimateTicTacToe.markMove(3,3);

        ultimateTicTacToe.markMove(1,0);
        ultimateTicTacToe.markMove(5,0);
        ultimateTicTacToe.markMove(7,0);
        ultimateTicTacToe.markMove(5,1);
        ultimateTicTacToe.markMove(7,3);
        ultimateTicTacToe.markMove(5,2);

        ultimateTicTacToe.markMove(7,8);
        ultimateTicTacToe.markMove(5,7);
        ultimateTicTacToe.markMove(7,5);
        ultimateTicTacToe.markMove(3,7);
        ultimateTicTacToe.markMove(1,5);
        ultimateTicTacToe.markMove(4,7);

        var board = ultimateTicTacToe.getGlobalBoard();
        Assert.assertEquals('O',board[1][0]);
    }

    @Test
    public void checkTicTacToe_ThereIsAWinner_True(){
        boolean a = ultimateTicTacToe.markMove(1,1);
        boolean b = ultimateTicTacToe.markMove(4,3);
        boolean c = ultimateTicTacToe.markMove(4,1);
        boolean d = ultimateTicTacToe.markMove(5,3);
        boolean e = ultimateTicTacToe.markMove(7,1);
        boolean f = ultimateTicTacToe.markMove(3,3);

        boolean g = ultimateTicTacToe.markMove(1,0);
        boolean h = ultimateTicTacToe.markMove(5,0);
        boolean i = ultimateTicTacToe.markMove(7,0);
        boolean j = ultimateTicTacToe.markMove(5,1);
        boolean k = ultimateTicTacToe.markMove(7,3);
        boolean l = ultimateTicTacToe.markMove(5,2);

        boolean m = ultimateTicTacToe.markMove(7,8);
        boolean n = ultimateTicTacToe.markMove(5,7);
        boolean o = ultimateTicTacToe.markMove(7,5);
        boolean p = ultimateTicTacToe.markMove(3,7);
        boolean q = ultimateTicTacToe.markMove(1,5);
        boolean r = ultimateTicTacToe.markMove(4,7);

        Assert.assertTrue(ultimateTicTacToe.checkTicTacToe());
    }
}
