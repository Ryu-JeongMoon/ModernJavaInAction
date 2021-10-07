package JavaInAction.chap05;

import static JavaInAction.chap04.Dish.menu;

import JavaInAction.chap04.Dish;
import java.util.Comparator;
import java.util.OptionalInt;

public class Summary2 {

    public static void main(String[] args) {
        // reduce

        // sum
        int sum = menu.stream()
            .map(Dish::getCalories)
            .mapToInt(Integer::intValue)
            .reduce(0, Integer::sum);

        // min
        int minByReduce = menu.stream()
            .map(Dish::getCalories)
            .mapToInt(Integer::intValue)
            .reduce(Integer::min)
            .getAsInt();

        int minByMinMethod = menu.stream()
            .map(Dish::getCalories)
            .mapToInt(Integer::intValue)
            .min()
            .getAsInt();

        System.out.println("minByReduce = " + minByReduce);
        System.out.println("minByMinMethod = " + minByMinMethod);

        // map & reduce -> map-reduce pattern 쉽게 병렬화하는 특징, 구글 웹 검색에 적용된 형태
        Integer sumOfDistinctNameByReduce = menu.stream()
            .map(Dish::getName)
            .distinct()
            .map(name -> 1)
            .reduce(Integer::sum)
            .get();

        System.out.println("sumOfDistinctNameByReduce = " + sumOfDistinctNameByReduce);
    }
}
