package tictactoe.backend;

public class MathQuadrantHelper {

    private final int quadrantSize;

    public MathQuadrantHelper(int quadrantSize) {
        this.quadrantSize = quadrantSize;
    }

    public int getQuadrantSize() {
        return quadrantSize;
    }

    public Pair<Integer, Integer> getQuadrantComponents(int quadrant) {
        return new Pair<>(quadrant / quadrantSize, quadrant % quadrantSize);
    }

    public int getQuadrant(int x, int y) {
        return x * quadrantSize + y;
    }

    public int getQuadrantFromGlobalCoordinates(int globalX, int globalY) {
        return getQuadrant(globalX / quadrantSize, globalY / quadrantSize);
    }

    public Pair<Integer, Integer> getQuadrantFromBigCoordinates(int globalX, int globalY) {
        return new Pair<>(globalX / quadrantSize, globalY / quadrantSize);
    }


    public boolean  insideQuadrant(Pair<Integer,Integer> quadrant, int bigRow, int bigColumn) {
        var candidateQuadrant = getQuadrantFromBigCoordinates(bigRow, bigColumn);
        return candidateQuadrant.equals(quadrant);
    }
}
