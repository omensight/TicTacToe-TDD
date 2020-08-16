import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.IUltimateTicTacToe;
import tictactoe.backend.UltimateTicTacToe;
import tictactoe.frontend.util.IBoxUI;
import tictactoe.frontend.util.ITicTacToeBoardRenderer;
import tictactoe.frontend.util.UltimateBoardRenderer;

public class ITicTacToeRendererTest {
    public ITicTacToeBoardRenderer boardRenderer;

    @Before
    public void setup(){
        IUltimateTicTacToe ultimateTicTacToe = new UltimateTicTacToe();
        ultimateTicTacToe.markMove(1,1);
        ultimateTicTacToe.markMove(4,3);
        ultimateTicTacToe.markMove(4,1);
        ultimateTicTacToe.markMove(5,3);
        ultimateTicTacToe.markMove(7,1);
        boardRenderer = new UltimateBoardRenderer(ultimateTicTacToe);
    }

    @Test
    public void getRenderableBoard_71isWhite_True(){
        IBoxUI boxUI = boardRenderer.getRenderableBoard()[0][0];
        Assert.assertEquals(UltimateBoardRenderer.SIMPLE_MARKED_COLOR,boxUI.getBoxColor());
    }

    @Test
    public void getRenderableBoard_35isYellow_True(){
        var board = boardRenderer.getRenderableBoard();
        IBoxUI boxUI = boardRenderer.getRenderableBoard()[3][5];
        Assert.assertEquals(UltimateBoardRenderer.ENABLED_QUADRANT_COLOR,boxUI.getBoxColor());
    }

    @Test
    public void getRenderableBoard_11ColorSymbolIsXColor_True(){
        IBoxUI boxUI = boardRenderer.getRenderableBoard()[1][1];
        Assert.assertEquals(UltimateBoardRenderer.X_COLOR,boxUI.getSymbolColor());
    }

    @Test
    public void getRenderableBoard_53ColorSymbolIsXColor_True(){
        IBoxUI boxUI = boardRenderer.getRenderableBoard()[5][3];
        Assert.assertEquals(UltimateBoardRenderer.O_COLOR,boxUI.getSymbolColor());
    }


}
