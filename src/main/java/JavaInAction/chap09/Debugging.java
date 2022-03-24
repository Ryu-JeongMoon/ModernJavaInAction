package JavaInAction.chap09;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Debugging {

  public static void main(String[] args) {
//        List<Point> points = Arrays.asList(new Point(12, 2), null);
//        points.stream()
//            .map(Point::getX).forEach(System.out::println);

    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    numbers.stream()
        .peek(x -> System.out.println("from stream : " + x))
        .map(x -> x + 17)
        .peek(x -> System.out.println("after map : " + x))
        .filter(x -> x % 2 == 0)
        .peek(x -> System.out.println("after filter : " + x))
        .limit(3)
        .peek(x -> System.out.println("after limit : " + x))
        .collect(Collectors.toList());
  }
}
