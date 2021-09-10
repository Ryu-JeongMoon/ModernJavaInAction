package JavaInAction.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

public class FilteringApples {

    enum Color {RED, GREEN}

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(79, Color.GREEN), new Apple(81, Color.GREEN),
            new Apple(79, Color.RED), new Apple(81, Color.RED), new Apple(151, Color.GREEN),
            new Apple(149, Color.GREEN), new Apple(151, Color.RED), new Apple(149, Color.RED));

        //        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println(heavyApples);

        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println(greenApples);

        List<Apple> redAndLightApples = filterApples(inventory, new AppleRedAndLightPredicate());
        System.out.println(redAndLightApples);

        prettyPrintApple(inventory, new WeightOfAppleFormatter());

        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);
    }

    public interface ApplePredicate {

        boolean test(Apple apple);
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public interface AppleFormatter {

        String accept(Apple a);
    }

    static class WeightOfAppleFormatter implements AppleFormatter {

        @Override
        public String accept(Apple a) {
            return "weight : " + a.getWeight();
        }
    }

    static class AppleHeavyWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleGreenColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return Color.GREEN.equals(apple.getColor());
        }
    }

    static class AppleRedAndLightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor()) && apple.getWeight() < 150;
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @Data
    @AllArgsConstructor
    public static class Apple {

        private int weight = 0;
        private Color color;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

}
