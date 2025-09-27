import java.util.Objects;
public class Coordinates{
    private final int start;
    private final int end;
    Coordinates(int start, int end){
        if(start <= 0 || end <= 0) {
            throw new IllegalArgumentException("Start and end positions must be positive integers.");
        }
        this.start = start;
        this.end = end;
    }
    public int getStart() {
        return start;
    }
    public int getEnd() {
        return end;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return start == that.start && 
               end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return String.format("%s{start=%d, end=%d}", 
            start, end);
    }
}