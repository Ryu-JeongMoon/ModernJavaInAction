package JavaInAction.chap06;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.partitioningBy;

import JavaInAction.chap04.Dish;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Summary3 {

    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian));
        System.out.println("partitionedMenu = " + partitionedMenu);

        List<Dish> vege = partitionedMenu.get(true);
        System.out.println("vege = " + vege);

        Map<Boolean, Map<Boolean, List<Dish>>> vegeAndFatMap = menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500)));
        System.out.println("vegeAndFatMap = " + vegeAndFatMap);

        /* partitioning 은 Predicate 를 인수로 받는다
        menu.stream()
            .collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));
        */

        System.out.println(isPrime(11));
        System.out.println(getPrimeList(55));
        System.out.println(getPrimeList2(68));

    }

    public static boolean isPrime(int candidate) {
        return IntStream.range(2, candidate)
            .noneMatch(i -> candidate % i == 0);
    }

    public static List<Integer> getPrimeList(int candidate) {
        return IntStream.range(2, candidate)
            .filter(Summary3::isPrime)
            .boxed()
            .collect(Collectors.toList());
    }

    public static Map<Boolean, List<Integer>> getPrimeList2(int candidate) {
        return IntStream.range(2, candidate)
            .boxed()
            .collect(groupingBy(Summary3::isPrime));
    }
}
