# Collection API

<hr>

## Collection Factory

고정 크기의 리스트 생성, 아래와 같은 에러를 만날 수 있다!

~~~
List<String> friends = Arrays.asList("Tom", "Jerry", "Ben");
friends.add("Hellen");

Exception in thread "main" java.lang.UnsupportedOperationException
	at java.base/java.util.AbstractList.add(AbstractList.java:153)
	at java.base/java.util.AbstractList.add(AbstractList.java:111)
	at JavaInAction.chap08.Summary.main(Summary.java:10)
~~~

파이썬 & 그루비 같은 녀석들은 [42, 1, 5] 과 같은 문법으로 컬렉션 리터럴을 만들 수 있음, 자바는 변화의 큰 비용 때문에 적용하지 못 함 <br>
아래와 같이 팩토리 메서드 of로 생성 가능 이 역시 수정 불가, null 불가

`List<String> friends2 = List.of("Tom", "Jerry", "Ben");`

> 이걸 왜 쓰고 언제 쓰나? <br>
> 데이터 처리 형식을 설정하거나 데이터를 변환할 필요가 없을 때!

<hr>

## List and Set and New Idiomatic Pattern

<hr>

## Map and New Idiomatic Pattern

자바 8에서 HashMap 내부 구조를 바꿔 성능 개선을 이뤄냈다 naver d2에 HashMap 관련 글 참고하기 <br>
hash 충돌을 피하기 위해 기존에 LinkedList 에 담아뒀던 것을 Tree 로 바꿈 기준을 8개로 잡아놨고 LL -> Tree(8), Tree -> LL(6) 형태다 <br>
이는 자료구조 특성 상 1개를 기준으로 바뀌면 수정이 자주 일어나 성능이 저하될 수 있으니 2개를 기준으로 잡아놓은 것
> key - String or Number 같은 Comparable 형태여야만 정렬된 트리가 지원된다!?

Map의 keySet(), entrySet() 개념 잘 잡고 가도록!

## Advanced Concurrent HashMap

내부 자료구조의 특정 부분만 잠궈 동시성에 문제 없도록 HashMap 개선한 버전