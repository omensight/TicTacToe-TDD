package tictactoe.backend;

import java.util.Objects;

public class Pair<X,Y> {
    private X x;
    private Y y;

    public Pair(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    public Pair() {}

    public void setX(X x) {
        this.x = x;
    }

    public X getX() {
        return x;
    }

    public void setY(Y y) {
        this.y = y;
    }

    public Y getY() {
        return y;
    }

    public void set(X x, Y y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(x, pair.x) &&
                Objects.equals(y, pair.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
