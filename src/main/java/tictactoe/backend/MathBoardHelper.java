package tictactoe.backend;

public class MathBoardHelper {
    private final int quadrantLimit;

    public MathBoardHelper(int quadrantLimit) {
        this.quadrantLimit = quadrantLimit;
    }


    public Pair<Integer, Integer> getLocalCoordinates(int globalRow, int globalColumn) {
        return new Pair<>(globalRow % quadrantLimit, globalColumn % quadrantLimit);
    }

    public Pair<Integer, Integer> getOffset(int x, int y) {
        return new Pair<>(x * quadrantLimit, y * quadrantLimit);
    }
}
