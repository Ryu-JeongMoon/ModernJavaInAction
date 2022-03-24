package JavaInAction.chap02;

import JavaInAction.chap02.FilteringApples.Apple;
import JavaInAction.chap02.FilteringApples.Color;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Summary {

  private static final List<Apple> inventory = Arrays.asList(new Apple(79, Color.GREEN), new Apple(81, Color.GREEN),
      new Apple(79, Color.RED), new Apple(81, Color.RED), new Apple(151, Color.GREEN),
      new Apple(149, Color.GREEN), new Apple(151, Color.RED), new Apple(149, Color.RED));

  public static void main(String[] args) {
    final List<Integer> integers = List.of(1, 2, 3, 4, 5, 6);
    final List<Integer> filteredIntegers = filter(integers, integer -> integer > 3);
    System.out.println("filteredIntegers = " + filteredIntegers);


    // compareTo 쓰려면 변수가 Integer 형이어야 한다
    // inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
    // 요렇게 축약도 가능하구나
    inventory.sort(Comparator.comparing(Apple::getWeight));
    System.out.println("inventory = " + inventory);


        /*
        final Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("yaho");
            }
        });
        */

    // t1.run(); -> t1.start(); 바꾸라고 알려준당
    final Thread t1 = new Thread(() -> System.out.println("yaho!"));
    t1.start();

    // Callable 은 Runnable 의 업그레이드 버전?
    final ExecutorService executorService = Executors.newCachedThreadPool();
        /*
        final Future<String> threadName = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        */

    // 람다 이용!
    final Future<String> threadName = executorService.submit(() -> Thread.currentThread().getName());
    System.out.println("threadName = " + threadName);
  }

  private static <T> List<T> filter(List<T> list, Predicate<T> p) {
    return list.stream()
        .filter(p)
        .collect(Collectors.toList());
  }
}

/*
T로 추상화해 List 형식에 적용될 수 있도록 캬!
 */