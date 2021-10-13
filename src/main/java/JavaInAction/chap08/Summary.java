package JavaInAction.chap08;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Summary {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 추가 불가, 수정은 가능
        List<String> friends = Arrays.asList("Tom", "Jerry", "Ben");

        // 추가, 수정 불가
        List<String> friends2 = List.of("Tom", "Jerry", "Ben");

        // 중복 시 오류
        Set<String> capitals = Set.of("Paris", "Seoul", "Tokyo", "Washington");

        // 10개 이하의 요소로만 생성 가능
        Map<String, Integer> players = Map.of("Ronaldo", 7, "Messi", 9);

        // 10개 이상에서는 ofEntries() 인수로 Map.entry() 받음 얘는 굳이스럽다
        Map<String, Integer> integers = Map.ofEntries(Map.entry("1", 11), Map.entry("2", 22), Map.entry("3", 33));

        List<String> referenceCodes = Arrays.asList("a12", "b13", "c14");

        // 새 문자열 컬렉션을 만드는 코드
        referenceCodes.stream()
            .map(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1))
            .collect(Collectors.toList())
            .forEach(System.out::println);

        // 새로 만들지 않고 변경, 넘 복잡
        for (ListIterator<String> iterator = referenceCodes.listIterator(); iterator.hasNext(); ) {
            String code = iterator.next();
            iterator.set(Character.toUpperCase(code.charAt(0)) + code.substring(1));
        }

        // replaceAll(), UnaryOperator 를 인수로 받는구만
        referenceCodes.replaceAll(code -> Character.toUpperCase(code.charAt(0)) + code.substring(1));

        // 8.3.1 forEach
        for (Map.Entry<String, Integer> entry : players.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " is " + value + "!");
        }

        // BiConsumer 인수로 받음둥
        players.forEach((k, v) -> System.out.println(k + " has " + v + " as a back number"));

        players.entrySet().stream()
            .sorted(Entry.comparingByKey())
            .forEachOrdered(System.out::println);

        // 스트림 순서대로 조회하고 싶은 경우에는 forEachOrdered()
        players.entrySet().stream()
            .sorted(Entry.comparingByValue())
            .forEachOrdered(System.out::println);

        // stream orElseGet 같은 놈이네, Map 에 적용시킬 수 있는 getOrDefault()
        System.out.println(players.getOrDefault("Son", 99));

        // 8.3.6 replace pattern
        Map<String, String> favouriteMovies = new HashMap<>();
        favouriteMovies.put("Raphael", "Star Wars");
        favouriteMovies.put("Olivia", "James Bond");
        favouriteMovies.replaceAll((friend, movie) -> movie.toUpperCase());

        // 8.3.7 merge
        Map<String, String> family = Map.ofEntries(Map.entry("Ted", "Star Wars"), Map.entry("Cristina", "Ronaldo"));
        Map<String, String> friend = Map.ofEntries(Map.entry("Raphael", "Star Wars"));
        HashMap<String, String> everyone = new HashMap<>(family);
        everyone.putAll(friend);

        // quiz 8-2
        Map<String, Integer> movies = new HashMap<>();
        movies.put("JamesBond", 20);
        movies.put("Matrix", 15);
        movies.put("Harry Potter", 5);

        movies.keySet().removeIf(s -> s.contains("Ma"));
        System.out.println("movies = " + movies);

        movies.entrySet().removeIf(stringIntegerEntry -> stringIntegerEntry.getValue() < 10);
        System.out.println("movies = " + movies);
    }
}
