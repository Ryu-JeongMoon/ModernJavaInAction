package JavaInAction.chap04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static JavaInAction.chap04.Dish.menu;

public class Summary {

  public static void main(String[] args) {

    // java 7 이전 코드
    List<Dish> lowCaloricDishes = new ArrayList<>();

    for (Dish dish : menu) {
      if (dish.getCalories() < 400) {
        lowCaloricDishes.add(dish);
      }
    }

    Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
      @Override
      public int compare(Dish o1, Dish o2) {
        return Integer.compare(o1.getCalories(), o2.getCalories());
      }
    });

    lowCaloricDishes.forEach(System.out::println);

    // java 8 stream, 참 짧고 좋다 그쵸잉
    menu.stream()
        .filter(dish -> dish.getCalories() < 400)
        .sorted(Comparator.comparing(Dish::getCalories))
        .forEach(System.out::println);

    // parallel stream, so expensive
    menu.parallelStream()
        .filter(dish -> dish.getCalories() < 400)
        .sorted(Comparator.comparing(Dish::getCalories))
        .forEach(System.out::println);
  }
}
