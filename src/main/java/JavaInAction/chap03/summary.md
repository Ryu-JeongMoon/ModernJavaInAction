# Lambda Expression
- 람다란 무엇인고
- 어디에 어떻게?
- 실행 어라운드 패턴
- 함수형 인터페이스, 형식 추론
- 메서드 참조
- 람다 만들기

## What is Lambda ?
__메서드로 전달할 수 있는 익명 함수를 단순화한 것__
<br>메서드처럼 클래스에 종속될 필요가 없어 함수라 칭하고 익명 클래스를 구현하는 것처럼 불필요한 코드를 작성할 필요 없다 

1. return이 함축되어 있어 생략 가능 `(String s) -> s.length()` 
2. 여러 행의 문장을 표현할 때는 중괄호 사용하고 return 값이 있는 경우 명시
3. parameter 가 없을 수도 있음, 빈 괄호 () 필수 `() -> 42`
4. 객체의 타입 생략 가능 `Comparator<Apple> byWeight3 = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());`

~~~
ETC..

(Apple a) -> a.getWeight() > 150

(int x, int y) -> {
    system.out.println("Result : ");
    system.out.println(x + y);
}

(Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight())
~~~

## Where, How ?

Functional Interface has an abstract method
- Function 
- Predicate 
- Supplier 
- Consumer

디폴트 메서드를 가지고 있어도 추상 메서드가 하나라면 함수형 인터페이스이다
<br>overloading 된 메서드를 가지고 있다면 추상 메서드가 아니다! 기준이 엄격하구만
`ìnt add(int a, int b); & int add(double a, double b);`

## Function Descriptor
람다 표현식의 시그니처를 가르킨다 @FunctionalInterface -> type 맞지 않으면 compile error 터트려줌<br>

`function descriptor String process(BufferedReader b) throws IOException;`


## Execute Around Pattern
자원 처리하는데 open -> process -> close 과정을 거친다
open, close 를 람다를 통해 할 수 있다 (프록시 맹키로)

## Boxing
Predicate, Consumer 등과 같은 기본 F/I 의 경우 참조형만 받게 설계되어 있어 성능 상의 약점이 있다 오토 박싱을 피하기 위해 기본형 특화 F/I 가 존재한다 따라서 성능을 위해 꼭 필요한 경우가 아니라면 기본형을 쓰자
Wrapper class 는 감싸는 객체이며 힙에 저장되기 때문에 메모리 소모!!

## Type Check, Type Reference, Constraint
람다가 사용되는 Context 를 통해 형식을 추론할 수 있다 -> target type <br>

`List<Apple> heavierThan150g = filter(inventory, apple -> apple.getWeight() > 150);`

1. check signature of filter method
2. filter method takes Predicate<Apple> type as second parameter
3. Predicate<Apple> has an abstract method named test
4. test method takes Apple object and return boolean
5. filter method should be satisfied with demand

lambda가 사용되는 context -> method signature 보고 인수로 어떤 것을 받는지 확인
Predicate<T> 에서 T를 확인된 타입으로 변환
Predicate F/I 의 method, parameter 확인

`람다 표현식이 예외를 던진다면 추상 메서드에도 명시적으로 선언해주어야 함!`

## Local Variable Using
람다 표현식에서 인수를 넘겨 받아 사용할 수 있지만 final 로 선언되어 있거나 사실 상 final 인 변수만 받아 사용할 수 있다 이를 __capturing lambda__ 라 한다 