package JavaInAction.chap04;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

import java.util.List;

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
