package JavaInAction.chap04;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamVsCollection {

  public static void main(String[] args) {
    List<String> title = Arrays.asList("java8", "in", "action");
    Stream<String> s = title.stream();
    s.forEach(System.out::println);
    s.forEach(System.out::println);
  }
}
