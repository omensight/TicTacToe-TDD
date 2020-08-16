import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tictactoe.backend.Pair;

public class PairTest {
    public Pair<Integer,Integer> pair;

    @Before
    public void setup(){
        pair = new Pair<>(1,1);
    }

    @Test
    public void equals_pair11IsEqualToAnother11_true(){
        Assert.assertEquals(pair, new Pair<>(1,1));
    }
}
