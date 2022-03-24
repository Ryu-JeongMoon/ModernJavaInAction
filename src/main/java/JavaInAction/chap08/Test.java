package JavaInAction.chap08;

import java.util.*;
import java.util.stream.Stream;

import static java.util.Map.*;
import static java.util.stream.Collectors.toSet;

public class Test {

  public static void main(String[] args) {

    List<String> friends = Arrays.asList("Raphael", "Olivia", "Thibaut");
    friends.set(0, "Hoi");
    System.out.println("friends = " + friends);
    //        Arrays.asList() 메서드 -> 고정된 크기로 생성, 수정은 가능하나 추가 삭제 X
    //        friends.add("Hoi");
    //        friends.remove(2);

    Set<String> friends2 = new HashSet<>(Arrays.asList("Raphael", "Olivia", "Thibaut"));
    Set<String> friends3 = Stream.of("Raphael", "Oracle", "JDBC")
        .collect(toSet());
    //        Arrays.asSet() 은 없음, List 를 인수로 받는 HashSet 을 쓰거나
    //        스트림의 collect 를 이용해 만들어야 함

    Map<String, Integer> language = of("Java", 10, "Python", 20, "C++", 30);
    //        10개 이하 요소일 때 사용하는 간단한 방법
    //        key - value 번갈아가며 입력

    Map<String, Integer> stringIntegerMap = ofEntries(entry("Java1", 30), entry("Java2", 30), entry("Java3", 30),
        entry("Java4", 30), entry("Java5", 30), entry("Java6", 30), entry("Java7", 30), entry("Java8", 30),
        entry("Java9", 30), entry("Java10", 30), entry("Java11", 30), entry("Java12", 30), entry("Java13", 30),
        entry("Java14", 30), entry("Java15", 30));
    //        요소 10개 이상일 때, 가변 인수로 구현되어 있기 때문에 작은 맵 만들때보다
    //        객체 할당 먼저 하고, GC 돌려야 해서 비용 커짐
    //        Map.Entry <K, V> 객체를 인수로 받는다
    //        팩토리 메서드를 사용하며, 가장 유념해야할 것. 얘네는 변경 불가
    //        의도치 않은 변경이 되지 않도록 할 땐 좋을수도..?!

    Map<String, String> favoriteMovies = ofEntries(entry("Raphael", "Star wars"), entry("Cristina", "Matrix"),
        entry("Olivia", "James Bond"));

    favoriteMovies.entrySet()
        .stream()
        //                      키를 기준으로 정렬 or 값을 기준으로 정렬
        //                      .sorted(Map.Entry.comparingByKey())
        .sorted(Entry.comparingByValue())
        .forEachOrdered(System.out::println);

    //        키가 없거나, 키에 해당하는 값이 없을 때, NullPointer 터지지 않도록 하는 메서드
    //        getOrDefault(키, 기본값);
    System.out.println(favoriteMovies.getOrDefault("Olivia", "Matrix"));
    System.out.println(favoriteMovies.getOrDefault("John", "Matrix"));

  }
}
