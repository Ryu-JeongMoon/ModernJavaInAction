package JavaInAction.chap05;

import JavaInAction.chap04.Dish;

import java.util.Arrays;
import java.util.List;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class VegetarianDish {

  public static void main(String[] args) {

    //        컬렉션을 사용하면 개발자가 직접 외부 반복을 통해 데이터 처리
    //        List<Dish> vegetarianDishes = new ArrayList<>();
    //
    //        for(Dish d : menu) {
    //            if(d.isVegetarian())
    //                vegetarianDishes.add(d);
    //        }

    //        data 를 어떻게 처리할 것인가는 스트림 API 가 내부 반복으로 처리
    //        무엇을 할지 메서드 체이닝을 통해 정한당
    List<Dish> vegetarianDishes = menu.stream()
        .filter(Dish::isVegetarian)
        .collect(toList());

    System.out.println(vegetarianDishes);

    List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4, 5, 6, 1, 3, 4);
    numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .forEach(System.out::print);
  }

}
