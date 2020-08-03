import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.Pair;
import tictactoe.backend.QuadrantMathHelper;

public class QuadrantMathHelperTest {
    public QuadrantMathHelper quadrantMathHelper;

    @Before
    public void setup(){
        quadrantMathHelper = new QuadrantMathHelper(3);
    }

    @Test
    public void getDimension_getDimensionAfterInstantiating_3(){
        Assert.assertEquals(3, quadrantMathHelper.getQuadrantSize());
    }

    @Test
    public void getQuadrantComponents_FifthQuadrantComponentX_1(){
        Pair<Integer, Integer> components = quadrantMathHelper.getQuadrantComponents(4);
        Assert.assertEquals(Integer.valueOf(1),components.getX());
    }

    @Test
    public void getQuadrantComponents_FifthQuadrantComponentY_1(){
        Pair<Integer, Integer> components = quadrantMathHelper.getQuadrantComponents(4);
        Assert.assertEquals(Integer.valueOf(1),components.getY());
    }

    @Test
    public void getQuadrant_Input11AndGet4_True(){
        Pair<Integer, Integer> quadrantComponents = new Pair<>(1,1);
        Assert.assertEquals(4, quadrantMathHelper.getQuadrant(quadrantComponents));
    }
}
