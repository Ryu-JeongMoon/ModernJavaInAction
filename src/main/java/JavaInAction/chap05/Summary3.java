package JavaInAction.chap05;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Summary3 {

  private static final Trader raoul = new Trader("Raoul", "Cambridge");
  private static final Trader mario = new Trader("Mario", "Milan");
  private static final Trader alan = new Trader("Alan", "Cambridge");
  private static final Trader brian = new Trader("Brian", "Cambridge");

  private static final List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
      new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400), new Transaction(mario, 2012, 710),
      new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

  public static void main(String[] args) {
    // 1
    transactions.stream()
        .filter(transaction -> transaction.getYear() == 2011)
        .sorted(Comparator.comparing(Transaction::getValue))
        .forEach(System.out::println);

    // 2
    transactions.stream()
        .map(Transaction::getTrader)
        .map(Trader::getCity)
        .distinct()
        .forEach(System.out::println);

    // 3
    transactions.stream()
        .map(Transaction::getTrader)
        .filter(trader -> trader.getCity().equals("Cambridge"))
        .map(Trader::getName)
        .sorted()
        .forEach(System.out::println);

    // 4
    transactions.stream()
        .map(transaction -> transaction.getTrader().getName())
        .sorted()
        .forEach(System.out::println);

    // 5 boolean 반환 필요할 때 anyMatch 쓰면 되는구만!
    transactions.stream()
        .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milano"));

    // 6
    transactions.stream()
        .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
        .forEach(System.out::println);

    // 7
    int max = transactions.stream()
        .mapToInt(Transaction::getValue)
        .max()
        .getAsInt();

    // 8
    int minByMethod = transactions.stream()
        .mapToInt(Transaction::getValue)
        .min()
        .getAsInt();

    int minByReduce = transactions.stream()
        .mapToInt(Transaction::getValue)
        .reduce(Integer::min)
        .getAsInt();

    // reduce 2
    transactions.stream()
        .mapToInt(Transaction::getValue)
        .reduce((v1, v2) -> v1 < v2 ? v1 : v2);

    // reduce 3
    transactions.stream()
        .mapToInt(Transaction::getValue)
        .reduce((v1, v2) -> Math.min(v1, v2));

    // reduce 4
    transactions.stream()
        .mapToInt(Transaction::getValue)
        .reduce(Math::min);

    // reduce -> min
    transactions.stream()
        .mapToInt(Transaction::getValue)
        .min();

    // quiz 5-4
    Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
        .limit(20)
        .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
  }
}
