package se.djupfeldt.explore.dto;

/**
 * Created by vi on 2017-01-17.
 */
public class Coordinate {
    private long x;
    private long y;

    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate other = (Coordinate) obj;
            return x == other.x && y == other.y;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(x) + Long.hashCode(y);
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
