## Stream
Collection이 없다면 우찌할꼬<br>
거의 모든 Java Application에는 Collection이 사용된다
근데 SQL 마냥 해야할 일만 명시하고 처리는 알아서 됐으면 참 좋겠다 
요래서 나온게 스트림 추후 최적화를 통해 개선될 수 있겠지만 
현재로써는 마냥 좋다고만 할 수는 없는게 외부 반복을 내부 반복으로 처리해 성능 저하가 있기도 하다
<br>성능이 정말 중요한 프로그램이라면 내부 vs 외부 성능 테스트를 거친 후 선택하자


### What is Stream ?
chap04 Summary.java 참고<br>
for-each 0.3s & stream 0.03s 결과 비교 - System.nanoTime() 이용

![](../../../../../../../../../../../var/folders/ys/q9j6g8k935x3vqvm0n5llzs40000gn/T/TemporaryItems/NSIRD_screencaptureui_FiLLdI/Screen Shot 2021-10-07 at 21.43.21.png)

- continued element
<br>데이터 처리 연산을 지원하도록 소스에서 추출된 연속된 요소
- source
<br>컬렉션, 배열, I/O 자원 등에서 데이터를 제공 받는다
- data processing operation
<br>함수형 프로그래밍 언어에서 지원하는 연산 지원!!
- pipelining
<br>laziness, short-circuit 최적화
- internal iteration
<br>내부 반복 지원


### Collection & Stream
데이터를 언제 계산하느냐?
1. Collection : 저장 하기 전 자료구조가 포함하는 모든 값이 저장되어야 함
<br>ex) DVD에 저장된 영화
2. Stream 요청할 때만 요소를 계산하는 고정된 자료구조
<br>ex) Streaming 서비스로 보는 영화

데이터 반복 처리 방법
1. Collection : 사용자가 직접 요소 반복
2. Stream : 반복을 알아서 처리하고 결과 값을 어딘가에 저장 (이후 저장된 값은 최종 연산으로 소비)


### Internal & External Iteration 
~~~
// external iteration
List<String> highCaloricDishesByCollection = new ArrayList<>();
Iterator<Dish> iterator = menu.iterator();

while (iterator.hasNext()) {
    Dish dish = iterator.next();
    if (dish.getCalories() > 300) {
        highCaloricDishesByCollection.add(dish.getName());
    }
}

// internal iteration
List<String> highCaloricDishesByStream = menu.stream()
    .filter(dish -> dish.getCalories() > 300)
    .map(Dish::getName)
    .collect(Collectors.toList());
~~~


### Intermediate & Terminal Operation
