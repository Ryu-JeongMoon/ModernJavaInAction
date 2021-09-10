package JavaInAction.chap05;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Practice {

    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

        //        5.6 실전 연습
        //        1. 2011년
        //        sorted 에다가 comparing(Transaction::getValue) 로 조건 줘서 해결
        List<Integer> value2011 = transactions.stream()
            .filter(t -> t.getYear() == 2011)
            .map(t -> t.getValue())
            .sorted()
            .collect(toList());

        //        2. 근무 도시
        //      map 을 한번만 써서 메서드 체이닝으로 transaction -> transaction.getTrader().getCity()로 해결
        //      distinct() 를 쓰는 대신 중복을 제거하는 toSet() 메서드 사용 가능
        List<String> cityDistinct = transactions.stream()
            .map(Transaction::getTrader)
            .map(t -> t.getCity())
            .distinct()
            .collect(toList());

        //        3. 케임브리지 거래자
        //        1과 마찬가지로 sorted() 에 조건 주기
        List<String> traderNames = transactions.stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity()
                .equals("Cambridge"))
            .map(Trader::getName)
            .distinct()
            .sorted()
            .collect(toList());

        //        4. 모든 거래자
        //        트레이더 이름으로 변환할 필요 없이 sorted() 에 조건 주기!!
        List<String> allTraders = transactions.stream()
            .map(Transaction::getTrader)
            .map(Trader::getName)
            .distinct()
            .sorted()
            .collect(toList());

        //        5. 밀라노 거래자
        //        값 확인 -> findAny, 유무 확인 (boolean) -> anyMatch
        Optional<Trader> milanTrader = transactions.stream()
            .map(Transaction::getTrader)
            .filter(t -> t.getCity()
                .equals("Milan"))
            .findAny();

        //        6. 케임브리지 거래자 모든 트랜잭션 값
        transactions.stream()
            .filter(t -> t.getTrader()
                .getCity()
                .equals("Cambridge"))
            .forEach(System.out::println);

        //        7. 전체 최댓값
        Optional<Integer> maxValue = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::max);

        //        8. 전체 최솟값
        Optional<Integer> minValue = transactions.stream()
            .map(Transaction::getValue)
            .reduce(Integer::min);

    }
}
