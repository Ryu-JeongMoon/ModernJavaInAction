# Functional Programming Technique

함수형 프로그래밍이란 함수나 메서드가 수학의 함수처럼 동작함을, 특히 부작용 없이 동작함을 의미한다 <br>
<hr>

## First-Class Function and Higher-Order Function, Curring

함수형 프로그래밍을 폭 넓게 보면 함수를 일반 값처럼 사용해 인수로 전달, 결과로 반환, 자료구조에 저장할 수 있다 <br>
이와 같이 사용되는 함수를 일급 함수라 한다

고차원 함수란 하나 이상의 동작을 수행하는 함수를 의미한다 <br>
함수를 인수로 받고, 함수를 결과로 반환

커링이란 함수를 모듈화하고 코드를 재사용하는데 도움을 주는 기법 <br>
`CelciusToFarenheit(x) = x * 9 / 5 + 32`라고 할 때 9/5 는 변환 요소, 32는 기준치 조정 요소라 한다 <br>

~~~
아래와 같이 표현할 수 있다
요놈은 사용할 때 바꿀 값, 변환 요소, 기준치 조정 요소를 전부 받아야 해서 귀찮당

static double converter(double x, double f, double b) {
    return x * f + b;
}

귀찮은건 똑같은데 뭐가 다르지 싶지만 변환 요소, 기준치 조정 요소를 이용해 함수를 반환한다
여러개의 인수를 받는 함수에서 쭉쭉 커리해나가면 최종적으로 하나의 인수를 받는 함수로 만들 수 있다
요놈 처럼 표현하면 하나의 인수를 받는 유연한 함수를 반환한당!!

static DoubleUnaryOperator curriedConverter(double f, double b) {
    return (double x) -> x * f + b;
}
~~~

<hr>

## Persistent Date Structure

함수형 프로그래밍에서 함수형 자료구조, 불변 자료구조라고도 하는데 일반적으로는 영속 자료구조라 한다 <br>
JPA 에서의 영속과는 다른 의미로 사용된다

### 파괴적인 갱신과 함수형

PersistentDataStructure.java 참고

X -> Y 의 함수 f1이 있었는데 다른 함수 g에서 이를 X -> Z, f2로 갱신해버리면 f1의 동작이 파괴된다 <br>
예를 들어 서울역 -> 구미역의 여정이 있었는데 다른 함수에서 이를 서울역 -> 부산역으로 바꿔버린 것!! OMG
<hr>

오마이갓 넘 어렵당

## Lazy Evaluation and Lazy List

<hr>

## Pattern Matching

<hr>

## Reference Transparency and Caching
