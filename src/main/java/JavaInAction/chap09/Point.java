package JavaInAction.chap09;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Point {

    public static final Comparator<Point> compareXAndThenY = Comparator.comparing(Point::getX).thenComparing(Point::getY);
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point moveRightBy(int x) {
        return new Point(this.x + x, y);
    }

    public static List<Point> moveAllPointsRightBy(List<Point> points, int x) {
        return points.stream()
            .map(p -> new Point(p.getX() + x, p.getY()))
            .collect(Collectors.toList());
    }
}
