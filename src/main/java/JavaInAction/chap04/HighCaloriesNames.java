package JavaInAction.chap04;

import java.util.List;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class HighCaloriesNames {

  public static void main(String[] args) {
    List<String> threeHighCaloricDishNames = menu.stream()
        .filter(dish -> dish.getCalories() > 300)
        .map(Dish::getName)
        .limit(3)
        .collect(toList());
    System.out.println("threeHighCaloricDishNames = " + threeHighCaloricDishNames);
  }
}
