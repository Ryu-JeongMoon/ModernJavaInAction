package JavaInAction.chap06;

import JavaInAction.chap04.Dish;

import java.util.IntSummaryStatistics;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.*;

public class Reducing_Summary {

  public static void main(String[] args) {

    //        동일한 기능, count() 는 최종 연산이므로 다른 연산이 더 필요한 경우에 Collectors.counting() 이용함
    //        long howManyDishes = menu.stream().collect(counting());
    long howManyDishes = menu.stream().count();
    System.out.println("howManyDishes = " + howManyDishes);

    //        합-1 특화 Collector method 이용
    Integer sumCalories = menu.stream()
        .collect(summingInt(Dish::getCalories));
    System.out.println("sumCalories = " + sumCalories);

    //        합-2 reducing() 이용
    Integer totalCalories = menu.stream()
        .collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
    System.out.println("totalCalories = " + totalCalories);

    //        평균
    Double avgCalories = menu.stream()
        .collect(averagingInt(Dish::getCalories));
    System.out.println("avgCalories = " + avgCalories);

    //        요소 수, 합, 최소, 최대, 평균 값
    IntSummaryStatistics menuStat = menu.stream()
        .collect(summarizingInt(Dish::getCalories));
    System.out.println("menuStat = " + menuStat);

    //        6.2.3 문자열 연결
    String shortMenu = menu.stream()
        .map(Dish::getName)
        .collect(joining("/"));
    System.out.println("shortMenu = " + shortMenu);


  }
}
