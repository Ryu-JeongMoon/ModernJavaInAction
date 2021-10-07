package JavaInAction.chap04;

import static JavaInAction.chap04.Dish.menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Summary2 {

    public static void main(String[] args) {
        List<String> threeHighCaloricDishNames = menu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .limit(3)
            .collect(Collectors.toList());

        System.out.println("threeHighCaloricDishNames = " + threeHighCaloricDishNames);

        // external iteration
        List<String> highCaloricDishesByCollection = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();

        while (iterator.hasNext()) {
            Dish dish = iterator.next();
            if (dish.getCalories() > 300) {
                highCaloricDishesByCollection.add(dish.getName());
            }
        }

        // internal iteration
        List<String> highCaloricDishesByStream = menu.stream()
            .filter(dish -> dish.getCalories() > 300)
            .map(Dish::getName)
            .collect(Collectors.toList());


        // 4.4 Stream Operation
        // filter & map -> loop fusion (다른 두 연산이 하나로 합쳐짐)
        List<String> names = menu.stream()
            .filter(dish -> {
                System.out.println("filtering = " + dish.getName());
                return dish.getCalories() > 300;
            })
            .map(dish -> {
                System.out.println("mapping = " + dish.getName());
                return dish.getName();
            })
            .limit(3)
            .collect(Collectors.toList());

        System.out.println("names = " + names);
    }
}
