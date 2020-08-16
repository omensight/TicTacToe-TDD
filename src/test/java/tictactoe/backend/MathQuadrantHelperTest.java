package tictactoe.backend;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MathQuadrantHelperTest {
    public MathQuadrantHelper mathQuadrantHelper;

    @Before
    public void setup(){
        mathQuadrantHelper = new MathQuadrantHelper(3);
    }

    @Test
    public void getDimension_getDimensionAfterInstantiating_3(){
        Assert.assertEquals(3, mathQuadrantHelper.getQuadrantSize());
    }

    @Test
    public void getQuadrantComponents_FifthQuadrantComponentX_1(){
        Pair<Integer, Integer> components = mathQuadrantHelper.getQuadrantComponents(4);
        Assert.assertEquals(Integer.valueOf(1),components.getX());
    }

    @Test
    public void getQuadrantComponents_FifthQuadrantComponentY_1(){
        Pair<Integer, Integer> components = mathQuadrantHelper.getQuadrantComponents(4);
        Assert.assertEquals(Integer.valueOf(1),components.getY());
    }

    @Test
    public void getQuadrant_Input11AndGet4_True(){
        Assert.assertEquals(4, mathQuadrantHelper.getQuadrant(1,1));
    }

    @Test
    public void getQuadrantFromGlobalCoordinates_CalcThe44QuadrantIs4_True(){
        Assert.assertEquals(4, mathQuadrantHelper.getQuadrantFromGlobalCoordinates(4,4));
    }

}
