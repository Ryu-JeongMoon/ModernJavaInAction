package JavaInAction.chap06;

import JavaInAction.chap04.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static JavaInAction.chap04.Dish.menu;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class GroupingBy {

  public static void main(String[] args) {

    //        분류 기준이 음식의 종류, 한 가지만으로 분류하므로 메서드 참조를 이용해 나타낼 수 있음
    Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
        .collect(groupingBy(Dish::getType));

    //        분류 기준이 범위일 때
    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
        .collect(groupingBy(dish -> {
          if (dish.getCalories() <= 400) {
            return CaloricLevel.DIET;
          } else if (dish.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
          } else {
            return CaloricLevel.FAT;
          }
        }));

    //        filter & groupingBy
    //        fish 타입의 요리 중 조건을 만족하는 요소가 하나도 없어 Map<Dish.Type, List<Dish>> 에서 fish 타입 자체가 사라짐
    Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
        .filter(d -> d.getCalories() > 500)
        .collect(groupingBy(Dish::getType));

    Map<Dish.Type, List<Dish>> caloricDishesByType2 = menu.stream()
        .collect(groupingBy(Dish::getType, filtering(d -> d.getCalories() > 500, toList())));

    //        mapping method
    Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
        .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

    //        다수준 그룹화
    Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
        .collect(groupingBy(Dish::getType, groupingBy(dish -> {
          if (dish.getCalories() <= 400) {
            return CaloricLevel.DIET;
          } else if (dish.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
          } else {
            return CaloricLevel.FAT;
          }
        })));

    //        groupingBy(f) -> groupingBy(f, toList()) 의 축약형임둥
    //        가장 칼로리 높은 것
    Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream()
        .collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));

    Map<Dish.Type, Dish> mostCaloricByType2 = menu.stream()
        .collect(
            groupingBy(Dish::getType, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));

    //        quiz 6-2
    Map<Boolean, Map<Boolean, List<Dish>>> a = menu.stream()
        .collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));

    //        partitioningBy 는 boolean 을 반환하는 Predicate 를 인수로 받음!, 따라서 요놈은 컴파일 에러 발생
    //        menu.stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));

  }

  public enum CaloricLevel {DIET, NORMAL, FAT}
}
