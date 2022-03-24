package JavaInAction.chap09;

import JavaInAction.chap04.Dish;
import JavaInAction.chap06.Summary2.CaloricLevel;

import java.util.List;
import java.util.Map;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.groupingBy;

public class Summary {

  public static void main(String[] args) {

    // 장황한 익명 클래스
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello ");
      }
    });

    // 람다는 깔끔!
    Thread t2 = new Thread(() -> System.out.println("World!"));

    t1.start();
    t2.start();

    // groupingBy() 와 같은 메서드에 장황한 코드가 있을 경우 메서드 추출 후 메서드 참조 이용
    Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream()
        .collect(groupingBy(dish -> {
          if (dish.getCalories() <= 400) {
            return CaloricLevel.DIET;
          } else if (dish.getCalories() <= 700) {
            return CaloricLevel.NORMAL;
          }
          return CaloricLevel.FAT;
        }));

    Map<CaloricLevel, List<Dish>> byCaloricLevel = menu.stream()
        .collect(groupingBy(Dish::getCaloricLevel));

    // strategy, Supplier 새로 생성할 필요 없이 람다로 해결!
    Validator validator = new Validator(new IsNumeric());
    validator = new Validator(s -> s.matches("[\\d+]"));

    validator = new Validator(new IsAllLowerCase());
    validator = new Validator(s -> s.matches("[a-z]+"));

    // observer
    Feed feed = new Feed();
    feed.registerObserver(new NYTimes());
    feed.registerObserver(new Guardian());

    feed.notifyObservers("money money");
    feed.notifyObservers("money!! && queen!!");

    feed.registerObserver(s -> {
      if (s.contains("panda")) {
        System.out.println("news about panda bear " + s);
      }
    });

    feed.notifyObservers("panda is king");
  }
}
