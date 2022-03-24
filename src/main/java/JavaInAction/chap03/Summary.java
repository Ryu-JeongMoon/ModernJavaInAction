package JavaInAction.chap03;

import JavaInAction.chap02.FilteringApples.Apple;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;

import static JavaInAction.chap02.FilteringApples.inventory;

public class Summary {

  public static void main(String[] args) throws IOException {
    Comparator<Apple> byWeight = new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
      }
    };

    // simpler than java 7
    Comparator<Apple> byWeight2 = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());

    // it can be omitted Apple object notation
    Comparator<Apple> byWeight3 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

    // comparing method & method reference
    Comparator<Apple> byWeight4 = Comparator.comparing(Apple::getWeight);


        /* 3-1
        (String s) -> s.length();
        (Apple a) -> a.getWeight() > 150
        (int x, int y) -> {
            system.out.println("Result : ");
            system.out.println(x + y);
        }
        () -> 42
        (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
         */

    Runnable r1 = () -> System.out.println("Hello World");

    Runnable r2 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello World2");
      }
    };

    process(r1);
    process(r2);
    process(() -> System.out.println("Hello World3"));

    Summary s = new Summary();

    // 한줄만 읽을 수 있음
    System.out.println(s.processFile());

    // 람다를 통해 읽는 동작 자체를 넘겨준다
    System.out.println(s.processFileByLambda((BufferedReader br) -> br.readLine()));
    System.out.println(s.processFileByLambda((BufferedReader br) -> br.readLine() + br.readLine()));
    System.out.println(s.processFileByLambda((BufferedReader br) -> br.readLine() + br.readLine() + br.readLine()));

    // method reference
    System.out.println(s.processFileByLambda(BufferedReader::readLine));

    // 3.6.2 constructor reference
    Supplier<Apple> c1 = Apple::new;
    Apple a1 = c1.get();

    Function<Integer, Apple> c2 = Apple::new;
    Apple a2 = c2.apply(10);

    System.out.println("a2.getWeight() = " + a2.getWeight());

    TriFunction<Integer, Integer, Integer, Panda> coronaFactory = Panda::new;
    Panda panda = coronaFactory.apply(1, 2, 3);
    System.out.println("panda = " + panda);


    // 3.7.1, 코드 전달 Comparator 직접 구현
    inventory.sort(new AppleComparator());

    // 3.7.2, 한번만 사용할 Comparator 를 구현할 필요가 있을까? 해서 나온 것이 익명 클래스
    inventory.sort(new Comparator<Apple>() {
      @Override
      public int compare(Apple o1, Apple o2) {
        return o1.getWeight().compareTo(o2.getWeight());
      }
    });

    // 3.7.3, 람다 표현식으로 간결하게 사용
    inventory.sort((apple1, apple2) -> apple1.getWeight().compareTo(apple2.getWeight()));
    inventory.sort(Comparator.comparing(a -> a.getWeight()));

    // 3.7.4, 메서드 참조로 더 간단히 표현
    inventory.sort(Comparator.comparing(Apple::getWeight));


    // 3.8.1 default method 덕분에 역정렬도 가능
    inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

    // Comparator 연결, 두번째 정렬 조건 지정
    inventory.sort(Comparator.comparing(Apple::getWeight)
        .reversed()
        .thenComparing(Apple::getColor));

    System.out.println("inventory = " + inventory);

  }

  public static void process(Runnable r) {
    r.run();
  }

  // try-with-resources 구문 -> TODO 추가 공부
  public String processFile() throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return br.readLine();
    }
  }

  public String processFileByLambda(BufferedReaderProcessor bp) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
      return bp.process(br);
    }
  }

  @FunctionalInterface
  private interface BufferedReaderProcessor {

    // function descriptor
    String process(BufferedReader b) throws IOException;
  }

  // Custom Functional Interface
  @FunctionalInterface
  private interface TriFunction<T, U, V, R> {

    R apply(T t, U u, V v);
  }

  private static class AppleComparator implements Comparator<Apple> {

    @Override
    public int compare(Apple o1, Apple o2) {
      return o1.getWeight().compareTo(o2.getWeight());
    }
  }

  @ToString
  @AllArgsConstructor
  private static class Panda {

    int a;
    int b;
    int c;
  }
}

/*
IDE 가 다 알려주네 신묘한 녀석
 */