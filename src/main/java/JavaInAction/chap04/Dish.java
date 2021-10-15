package JavaInAction.chap04;

import JavaInAction.chap06.Summary2.CaloricLevel;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class Dish {

    public static final List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("apple", false, 100, Type.OTHER),
        new Dish("melon", true, 300, Type.OTHER), new Dish("tomato", false, 150, Type.OTHER),
        new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 400, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH));

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public CaloricLevel getCaloricLevel() {
        if (this.getCalories() <= 400) {
            return CaloricLevel.DIET;
        } else if (this.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
        }
        return CaloricLevel.FAT;
    }

    public enum Type {MEAT, FISH, OTHER}
}
