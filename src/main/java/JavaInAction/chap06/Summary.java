package JavaInAction.chap06;

import static JavaInAction.chap04.Dish.menu;

import JavaInAction.chap04.Dish;
import JavaInAction.chap05.Trader;
import JavaInAction.chap05.Transaction;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Summary {

    private static final Trader raoul = new Trader("Raoul", "Cambridge");
    private static final Trader mario = new Trader("Mario", "Milan");
    private static final Trader alan = new Trader("Alan", "Cambridge");
    private static final Trader brian = new Trader("Brian", "Cambridge");

    private static final List<Transaction> transactions = List.of(new Transaction(brian, 2011, 300),
        new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710),
        new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

    public static void main(String[] args) {

        // Collectors.counting()
        menu.stream().collect(Collectors.counting());

        // 간단하게 표현
        menu.stream().count();

        // 더 간단하게
        menu.size();

        // Collectors.maxBy(), Optional 반환함
        menu.stream().collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));

        // 간단하게 표현
        Optional<Dish> max = menu.stream().max(Comparator.comparingInt(Dish::getCalories));
        System.out.println("max = " + max);

        // summingInt, IntSummaryStatistics{count=12, sum=4850, min=100, average=404.166667, max=800} 반환
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        // 모든 문자열 추출, 하나의 문자열로 반환
        String joinedString = menu.stream().map(Dish::getName).collect(Collectors.joining());
        System.out.println("joinedString = " + joinedString);

        // 하나의 문자열은 알아보기 힘드니까 구분자 넣어줌
        String joinedStringWithDemeter = menu.stream().map(Dish::getName).collect(Collectors.joining(", "));
        System.out.println("joinedStringWithDemeter = " + joinedStringWithDemeter);

        // reducing 연산으로 총합 구하기
        menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));

        // reducing 안에 있는 map 밖으로 꺼냄
        menu.stream().map(Dish::getCalories).reduce(0, (i, j) -> i + j);

        // 간단하게 Integer 클래스의 sum 메서드 이용
        menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
    }
}
