package JavaInAction.chap06;

import JavaInAction.chap04.Dish;
import JavaInAction.chap04.Dish.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.*;

public class Summary2 {

  private static final Map<String, List<String>> dishTags = new HashMap<>();

  public static void main(String[] args) {

    dishTags.put("pork", List.of("greasy", "salty"));
    dishTags.put("beef", List.of("salty", "roasted"));
    dishTags.put("chicken", List.of("fried", "crisp"));
    dishTags.put("french fries", List.of("greasy", "fried"));
    dishTags.put("rice", List.of("light", "natural"));
    dishTags.put("season fruit", List.of("fresh", "natural"));
    dishTags.put("pizza", List.of("tasty", "salty"));
    dishTags.put("prawns", List.of("tasty", "roasted"));
    dishTags.put("salmon", List.of("delicious", "fresh"));
    dishTags.put("apple", List.of("fresh", "natural"));
    dishTags.put("tomato", List.of("fresh", "natural"));
    dishTags.put("melon", List.of("fresh", "natural"));

    Map<Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
    System.out.println("dishesByType = " + dishesByType);

    Map<CaloricLevel, List<Dish>> dishesByCalories = menu.stream()
        .collect(groupingBy(dish -> {
          if (dish.getCalories() <= 400) {
            return CaloricLevel.DIET;
          } else if (dish.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
          } else {
            return CaloricLevel.FAT;
          }
        }));
    System.out.println("dishesByCalories = " + dishesByCalories);

    Map<Type, List<Dish>> dishedByTypeAndCalories = menu.stream()
        .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
    System.out.println("dishedByTypeAndCalories = " + dishedByTypeAndCalories);

    Map<Type, Set<String>> flatMappingMap = menu.stream()
        .collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get(dish.getName()).stream(), toSet())));
    System.out.println("typeSetMap = " + flatMappingMap);

    Map<Type, Map<CaloricLevel, List<Dish>>> multiLevelGroupingMap = menu.stream()
        .collect(groupingBy(Dish::getType,
            groupingBy(dish -> {
              if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
              } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
              } else {
                return CaloricLevel.FAT;
              }
            })));
    System.out.println("typeMapMap = " + multiLevelGroupingMap);

    Map<Type, Long> subGroupDataCollection = menu.stream()
        .collect(groupingBy(Dish::getType, counting()));
    System.out.println("subGroupDataCollection = " + subGroupDataCollection);

    menu.stream()
        .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));


  }

  public enum CaloricLevel {
    DIET, NORMAL, FAT
  }
}
