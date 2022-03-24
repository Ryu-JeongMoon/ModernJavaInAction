package JavaInAction.chap04;

import java.util.List;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Test {

  public static void main(String[] args) {

    //        List<String> names = new ArrayList<>();
    //
    //        for (Dish dish : menu)
    //            names.add(dish.getName());

    List<String> names = menu.stream()
        .map(Dish::getName)
        .collect(toList());

    System.out.println(names);

    //        quiz 4-1
    List<String> highCaloricDishes = menu.stream()
        .filter(dish -> {
          System.out.println("filtering : " + dish.getName());
          return dish.getCalories() > 300;
        })
        .map(dish -> {
          System.out.println("mapping : " + dish.getName());
          return dish.getName();
        })
        .limit(3)
        .collect(toList());

    System.out.println(highCaloricDishes);

    //        quiz 4-2
    long count = menu.stream()
        .filter(d -> d.getCalories() > 300)
        .distinct()
        .limit(3)
        .count();
    System.out.println("count = " + count);
  }
}

/**
 * iterator 사용 -> for-each 구문 for-each 구문 -> 스트림 내부 반복 아주 간편하구만
 */