package JavaInAction.chap02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

public class FilteringApples {

    public static void main(String[] args) {

        List<Apple> inventory = Arrays.asList(new Apple(79, Color.GREEN), new Apple(81, Color.GREEN),
            new Apple(79, Color.RED), new Apple(81, Color.RED), new Apple(151, Color.GREEN),
            new Apple(149, Color.GREEN), new Apple(151, Color.RED), new Apple(149, Color.RED));

        //List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);

        List<Apple> heavyApples = filterApples(inventory, new AppleHeavyWeightPredicate());
        System.out.println("heavyApples = " + heavyApples);

        List<Apple> greenApples = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println("greenApples = " + greenApples);

        List<Apple> redAndLightApples = filterApples(inventory, new AppleRedAndLightPredicate());
        System.out.println("redAndLightApples = " + redAndLightApples);

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println("redAndHeavyApples = " + redAndHeavyApples);

        List<Apple> greenAndLightApples = filterApples(inventory,
            apple -> Color.GREEN == apple.getColor() && apple.getWeight() < 150);
        System.out.println("greenAndLightApples = " + greenAndLightApples);

        prettyPrintApple(inventory, new WeightOfAppleFormatter());

        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    // stream 사용으로 refactoring
    // result 변수를 만들 필요 없고 반복 과정을 직접 제어하지 않아도 된다 무엇을 할지 명시해줌
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        return inventory.stream()
            .filter(p::test)
            .collect(Collectors.toList());
    }

    // refactoring 이전 버전
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor().equals(color)) {
                result.add(apple);
            }
        }
        return result;
    }

    enum Color {RED, GREEN}

    public interface ApplePredicate {

        boolean test(Apple apple);
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
            return Color.GREEN == apple.getColor();
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return Color.RED == apple.getColor() && apple.getWeight() > 150;
        }
    }

    static class AppleRedAndLightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return Color.RED == apple.getColor() && apple.getWeight() < 150;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Apple {

        private Integer weight = 0;
        private Color color;
    }

}
