package tictactoe.frontend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoPlayerTurnHandlerTest {
    private ITurnHandler<Character> ITurnHandler;

    @Before
    public void setup(){
    }

    @Test
    public void getTurn_ChangeFromXToOAfterTheFirstChange(){
        ITurnHandler = new TwoPlayerTurnHandler<>('X', 'O');
        ITurnHandler.changeTurn();
        Assert.assertEquals(Character.valueOf('O'), ITurnHandler.getTurn());
    }

    @Test
    public void getTurn_TurnOfXAfterTheSecondCall_True(){
        ITurnHandler = new TwoPlayerTurnHandler<>('X', 'O');
        ITurnHandler.changeTurn();
        ITurnHandler.changeTurn();
        Assert.assertEquals(Character.valueOf('X'), ITurnHandler.getTurn());
    }
}
