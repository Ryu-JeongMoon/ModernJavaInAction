package JavaInAction.chap05;

import static JavaInAction.chap04.Dish.menu;

import JavaInAction.chap04.Dish;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reducing {

    public static void main(String[] args) {

        //        평범한 방법으로 람다식 사용
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        Optional<Integer> maxNumber = numbers.stream()
            .reduce((x, y) -> x > y ? x : y);

        //        Integer class 에는 연산 관련 특화 메서드들이 있다
        Optional<Integer> minNumber = numbers.stream()
            .reduce(Integer::min);

        //        quiz 5-3
        //        개수를 구하기 위해 각 요소들을 1로 mapping
        Integer count = menu.stream()
            .map(d -> 1)
            .reduce(0, (a, b) -> a + b);
        //        count() 를 이용한 개수 구하기
        long count1 = menu.stream()
            .map(Dish::getName)
            .count();

        //        5.7 칼로리의 합
        //        Integer::sum 으로 합을 구하면 박싱 비용이 있다
        //        이를 개선하기 위해 기본형 특화 스트림을 이용해야 함
        Optional<Integer> sumCalories = menu.stream()
            .map(Dish::getCalories)
            .reduce(Integer::sum);

        //        숫자 스트림으로 변환하는 mapToInt, mapToFloat, mapToDouble
        //        박싱 비용 뿐만 아니라, 숫자를 다루는데 특화된 메서드도 존재
        //        sum(), max(), min() 등
        int sumCalories2 = menu.stream()
            .mapToInt(Dish::getCalories)
            .sum();

        //        기본형 특화 스트림으로 연산을 처리한 후 객체 형으로 복원하기 위한
        //        boxed() 메서드
        Stream<Integer> boxed = menu.stream()
            .mapToInt(Dish::getCalories)
            .boxed();

        //        sum() 의 경우엔 기본값이 0이지만, max 나 min 의 경우에
        //        최댓값, 최솟값이 0일 수 있음, 따라서 요소가 없을 때를 위해
        //        OptionalInt 가 제공됨
        OptionalInt maxCalorie = menu.stream()
            .mapToInt(Dish::getCalories)
            .max();

        //        숫자 범위를 통해 스트림 생성, range & rangeClosed
        IntStream.range(1, 100)
            .filter(i -> i % 15 == 0)
            .forEach(System.out::println);

        //        Stream.of() 로 스트림 생성
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase)
            .forEach(System.out::println);


    }
}
