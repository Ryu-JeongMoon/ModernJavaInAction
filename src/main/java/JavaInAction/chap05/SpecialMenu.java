package JavaInAction.chap05;

import JavaInAction.chap04.Dish;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SpecialMenu {

  public static void main(String[] args) {

    List<Dish> specialMenu = Arrays.asList(new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("chicken", false, 400, Dish.Type.MEAT), new Dish("french fries", true, 530, Dish.Type.OTHER));

    //        요소가 적을 땐, filter 만을 이용해 간단하게 처리할 수 있지만
    //        요소가 많은 경우를 가정하면 filter 는 전체 데이터를 처리하기 때문에 시스템 부하가 커질 수 있음
    List<Dish> filteredMenu = specialMenu.stream()
        .filter(dish -> dish.getCalories() < 320)
        .collect(toList());

    //        자바9 부터 지원하는 takeWhile, dropWhile 을 이용해 개선할 수 있음
    //        이미 정렬된 상태의 specialMenu 를 takeWhile 을 사용해 320보다 큰 요소가 나오면 반복 중단
    List<Dish> slicedMenu = specialMenu.stream()
        .takeWhile(dish -> dish.getCalories() < 320)
        .collect(toList());

    //        dropWhile 은 조건으로 들어온 것이 true 인 경우 모두 버리고 작업을 중단하고
    //        조건에 false 해당하는 것들을 반환함
    List<Dish> slicedMenu2 = specialMenu.stream()
        .dropWhile(dish -> dish.getCalories() < 320)
        .collect(toList());

    //        limit 은 스트림의 반환값의 개수를 제한할 수 있듬
    //        여기서는 filter 조건에 해당하는 세놈을 찾고 바로 반환함
    List<Dish> dishes = specialMenu.stream()
        .filter(dish -> dish.getCalories() > 300)
        .limit(3)
        .collect(toList());

    //        skip 은 스킵할 개수를 지정함
    List<Dish> dishes1 = specialMenu.stream()
        .filter(d -> d.getCalories() > 300)
        .skip(2)
        .collect(toList());

    //        quiz 5-1
    //        처음 등장하는 고기 요리 두개 필터링
    List<Dish> meat = specialMenu.stream()
        .filter(d -> d.getType() == Dish.Type.MEAT)
        .limit(2)
        .collect(toList());
  }
}
