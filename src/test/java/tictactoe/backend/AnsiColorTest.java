package tictactoe.backend;

import org.junit.Assert;
import org.junit.Test;
import tictactoe.frontend.ConsoleHelper;
import tictactoe.frontend.util.AnsiColors;

public class AnsiColorTest {

    @Test
    public void colorRedValid(){
        String color = AnsiColors.RED;
        Assert.assertEquals("\033[31m",color);
    }

    @Test
    public void colorGreenValid(){
        ConsoleHelper consoleHelper = new ConsoleHelper();
        String color = AnsiColors.GREEN;
        Assert.assertEquals("\033[32m",color);
    }

    @Test
    public void colorYellowValid(){
        String color = AnsiColors.YELLOW;
        Assert.assertEquals("\033[33m",color);
    }

    @Test
    public void colorBlueValid(){
        String color = AnsiColors.BLUE;
        Assert.assertEquals("\033[34m",color);
    }

    @Test
    public void colorPurpleValid(){
        String color = AnsiColors.PURPLE;
        Assert.assertEquals("\033[35m",color);
    }

    @Test
    public void colorCyanValid(){
        String color = AnsiColors.CYAN;
        Assert.assertEquals("\033[36m",color);
    }

    @Test
    public void resetColorValid(){
        String color = AnsiColors.RESET;
        Assert.assertEquals("\u001B[0m",color);
    }
}
