package JavaInAction.chap05;


import JavaInAction.chap04.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static JavaInAction.chap04.Dish.menu;
import static java.util.stream.Collectors.toList;

public class Mapping {

  public static void main(String[] args) {

    //        함수를 인수로 받는 map 메서드, 함수를 적용한 결과가 새로운 결과로 매핑된다
    //        기존의 값을 고치는 것이 아닌 새로운 버전이 만들어진다는 개념에 가까워서 mapping 이라고 함
    List<String> dishNames = menu.stream()
        .map(Dish::getName)
        .collect(toList());

    //        각 요리 명의 글자 길이를 알고 싶을 때
    //        menu -> 요리명으로 변환 -> 글자 길이로 변환
    List<Integer> dishNameLength = menu.stream()
        .map(Dish::getName)
        .map(String::length)
        .collect(toList());

    //        Hello , World 의 두 스트림이 생성됨, 이를 리스트에 담아서 반환
    List<String> words = Arrays.asList("Hello", "World");
    List<Stream<String>> streams = words.stream()
        .map(word -> word.split(""))
        .map(Arrays::stream)
        .distinct()
        .collect(toList());

    //        두 스트림이 평면화되어 하나의 스트림이 생성되고 요소들이 리스트에 담겨 반환
    List<String> collect = words.stream()
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(toList());

    //        quiz 5-2
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> collect1 = numbers.stream()
        .map(i -> i * i)
        .collect(toList());

    //        어렵당
    List<Integer> numbers1 = Arrays.asList(1, 2, 3);
    List<Integer> numbers2 = Arrays.asList(3, 4);
    List<int[]> pairs = numbers1.stream()
        .flatMap(i -> numbers2.stream()
            .map(j -> new int[]{i, j}))
        .collect(toList());

    List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
    someNumbers.stream()
        .map(n -> n * n)
        .filter(n -> n % 3 == 0)
        .findFirst()
        .ifPresent(i -> System.out.println(i));

  }
}
