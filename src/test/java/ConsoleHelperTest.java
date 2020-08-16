import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.frontend.ConsoleHelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConsoleHelperTest {
    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }

    @Test
    public void enterNumberValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String input = "7";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int number = consoleHelper.enterNumber(9,"number play");
        Assert.assertEquals(7,number);
    }

    @Test
    public void enterNumberNotValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String input = "3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        input = "2";
        in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        int number = consoleHelper.enterNumber(2,"number play");
        Assert.assertEquals(2,number);
    }


    @Test
    public void messageDrawGameValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String message = consoleHelper.messageDrawGame();
        Assert.assertEquals("It's a Draw :'v",message);
    }

    @Test
    public void messageFinishGameValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String message = consoleHelper.messageFinishGame();
        Assert.assertEquals("Thanks for Play!!! :)",message);
    }

    @Test
    public void messageWinnerGameValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String message = consoleHelper.messageWinnerGame("Alicia");
        Assert.assertEquals("The winner is Alicia, Congratulations!",message);
    }
}