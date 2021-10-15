# Refactoring, Testing, Debugging
1. 기존 코드 리팩터링 방법
2. 전략, 템플릿 메서드, 옵저버, 의무 체인, 팩토리 등 객체 지향 패턴 간소화
3. 테스트 & 디버깅

<hr>

## Refactoring to Lambda Expression
가독성과 유연성 개선 <br>
다른 사람이 봤을 때 이해하기 쉽고 유지보수가 쉬운 코드를 의미한다

- boiler plate 를 제거해 코드를 간소화한다
- behavior parameterization 으로 더 큰 유연성을 갖을 수 있다

무슨 일을 할지 메서드 이름으로 알 수 있는 람다의 선언적 프로그래밍을 활용하자 <br>
내장 컬렉터, 메서드 참조 이용하기 -> chap09.Summary 참고

~~~
- conditional deferred execution

매번 상태 확인 & logger 의 구현을 클라이언트 코드에 드러냄
if (logger.isLoggable(Log.FINER)) {
    logger.finer("Problem " + generateDiagnostic());
}

logger 객체가 적절한 수준으로 설정되었는지 내부적으로 확인하는 log method 이용
불필요한 if 문 제거 & 구현을 드러내지 않음
logger level이 적절하지 않더라도 항상 수행되는 문제 발생
logger.log(Level.FINER, "Problem " + generateDiagnostic());

public void log(Level level, Supplier<String> msgSupplier) method 이용
조건을 통과하기 전에는 msgSupplier 실행을 미룸
logger.log(Level.FINER, () -> "Problem " + generateDiagnostic());

클라이언트 코드에서 내부 상태를 자주 확인하고 메서드를 호출하는 경우
** 메서드 추출하여 내부적으로 확인하게 만들면 가독성과 캡슐화를 향상시킨다 **
~~~
~~~
- execute around

매번 같은 준비, 종료 과정을 반복적으로 수행할 때, 이를 람다로 변환
함수형 인터페이스를 받아 동작 결정
ex) DB Connect, File Read

public String processFileByLambda(BufferedReaderProcessor bp) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
        return bp.process(br);
    }
}

@FunctionalInterface
private interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}
~~~

<hr>

## Effect of Lambda Expression to OOP
언어에 새로운 기능 추가는 기존의 패턴, 관용코드 사용을 줄인다 (내부적으로는 기존 코드 사용) <br>
ex) for-each 로 인해 일반 for 문 사용 저하, Generic 으로 인한 명시적 생성 저하

디자인 패턴은 공통적인 소프트웨어 문제를 설계할 때 재사용할 수 있는 청사진을 제공한다 <br>
재사용할 수 있는 부품으로 여러가지 다리 (아치교, 현수교) 를 건설하는 엔지니어링에 비유할 수 있다

>1 - Strategy <br>
>전략 패턴은 Algorithm family 를 가지고 있고 런타임에 유연하게 algorithm 선택하는 기법 <br>
>Validator validator = new Validator(new IsNumeric()); <br>
>validator = new Validator(s -> s.matches("[\\d+]"));

>2 - Template Method <br>
>알고리즘의 개요를 제시하고 일부를 고칠 수 있는 유연함을 제공해야 할 때, <br>
>즉 템플릿 제공하고 '세부사항은 알아서 고쳐라' 할 때 유용 <br>
>hook method, abstract method 제공해서 abstract 알아서 구현해서 써라!

>3 - Observer <br>
>이벤트 발생 시 Subject -> Observer 자동으로 알림을 보내야 하는 상황에서 사용 <br>
>Observer 가 상태를 가지고 여러 메서드를 정의하는 경우에는 람다 사용 X

>4 - Chain of Responsibility <br>
>작업 처리 객체의 체인을 만들 때 사용

>5 - Factory <br>
>인스턴스화 로직을 클라이언트에 노출하지 않고 생성 책임을 Factory 에 맡긴다 <br>
>1-5 디자인 패턴의 예시를 살펴보면 간단한 로직인 경우 람다로 변환 가능하다는 점이 핵심이고 <br>
>기존 메서드의 Signature 를 토대로 람다로 변환한다는 것을 알 수 있다

<hr>

## Lambda Expression Testing
`PointTest 참고`

<i>고차 함수 ?!</i><br>
함수를 인수로 받거나 다른 함수를 반환하는 메서드

<hr>

## Lambda Expression and Stream API Debugging
람다를 사용하게 되면 에러가 터졌을 때, 컴파일러가 만들어낸 람다의 이름이 나타난다 <br>
