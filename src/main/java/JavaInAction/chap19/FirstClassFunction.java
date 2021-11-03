package JavaInAction.chap19;

import JavaInAction.chap02.FilteringApples.Apple;
import java.util.Comparator;
import java.util.function.Function;

public class FirstClassFunction {

    public static void main(String[] args) {
        // first-class function
        Function<String, Integer> strToInt = Integer::parseInt;
        System.out.println(strToInt.apply("1234") + 6);

        // higher-order function
        Comparator<Apple> comparator = Comparator.comparing(Apple::getWeight);
        System.out.println(comparator.reversed());


    }
}
