package tictactoe.backend;

public class QuadrantMathHelper {

    private final int quadrantSize;

    public QuadrantMathHelper(int quadrantSize) {
        this.quadrantSize = quadrantSize;
    }

    public int getQuadrantSize() {
        return quadrantSize;
    }

    public Pair<Integer, Integer> getQuadrantComponents(int quadrant) {
        return new Pair<>(quadrant / quadrantSize, quadrant % quadrantSize);
    }

    public int getQuadrant(Pair<Integer, Integer> quadrant) {
        return quadrant.getX()*quadrantSize + quadrant.getY();
    }
}
