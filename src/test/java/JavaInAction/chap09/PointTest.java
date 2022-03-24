package JavaInAction.chap09;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PointTest {

  @Test
  void testMoveRightBy() {
    Point p1 = new Point(5, 5);
    Point p2 = p1.moveRightBy(10);

    assertEquals(15, p2.getX());
    assertEquals(5, p2.getY());
  }

  @Test
  void testComparingTwoPoints() {
    Point p1 = new Point(10, 15);
    Point p2 = new Point(10, 20);

    int result = Point.compareXAndThenY.compare(p1, p2);
    assertTrue(result < 0);
  }

  @Test
  void testMoveAllPointsRightBy() {
    List<Point> points = Arrays.asList(new Point(5, 5), new Point(10, 5));
    List<Point> expectedPoints = Arrays.asList(new Point(15, 5), new Point(20, 5));
    List<Point> newPoints = Point.moveAllPointsRightBy(points, 10);

    assertEquals(expectedPoints, newPoints);
  }

  @Test
  void testFilter() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
    List<Integer> even = numbers.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    assertEquals(2, even.size());
  }
}