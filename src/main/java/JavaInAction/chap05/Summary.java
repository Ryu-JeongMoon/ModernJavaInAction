package JavaInAction.chap05;

import JavaInAction.chap04.Dish;
import JavaInAction.chap04.Dish.Type;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static JavaInAction.chap04.Dish.menu;

public class Summary {

  public static void main(String[] args) {
    // 정렬된 상태에서 filter 처럼 사용할 수 있는 takeWhile
    menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories))
        .takeWhile(dish -> dish.getCalories() < 320)
        .forEach(System.out::println);

    System.out.println();

    // takeWhile 과 정반대로 Predicate 가 false 반환하는 지점까지의 값들을 모두 버리고 나머지 값을 반환
    menu.stream()
        .sorted(Comparator.comparing(Dish::getCalories))
        .dropWhile(dish -> dish.getCalories() < 320)
        .forEach(System.out::println);

    System.out.println();

    // quiz 5-1
    menu.stream()
        .filter(dish -> dish.getType() == Type.MEAT)
        .limit(2)
        .forEach(System.out::println);

    // flatMap 평면화
    String[] arrayOfWords = {"Goodbye", "World"};
    Arrays.stream(arrayOfWords)
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .forEach(System.out::print);

    System.out.println();

    // quiz 5-2
    int[] arrayOfInt = {1, 2, 3, 4, 5};
    Arrays.stream(arrayOfInt)
        .map(i -> i * i)
        .forEach(System.out::print);

    System.out.println();

    List<Integer> a1 = List.of(1, 2, 3);
    List<Integer> a2 = List.of(3, 4);

    List<int[]> ints = a1.stream()
        .flatMap(i -> a2.stream()
            .filter(j -> (i + j) % 3 == 0)
            .map(j -> new int[]{i, j}))
        .collect(Collectors.toList());

    for (int[] anInt : ints) {
      System.out.println("anInt = " + Arrays.toString(anInt));
    }

  }
}
