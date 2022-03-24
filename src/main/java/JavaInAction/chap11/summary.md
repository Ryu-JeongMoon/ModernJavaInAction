# Optional Class

값이 없는 상황에서 null 지정하는 것이 왜 십억달러 짜리 실수일까? <br>

~~~
Car, Insurance 하나라도 할당되어 있지 않다면 NPE

Person person = new Person();
person.getCar().getInsurance();
~~~

보수적인 접근으로는 if (person != null) 등의 확인 코드가 잔뜩 들어가야 한다 <br>
이와 같은 null check 코드가 반복되는 코드를 `deep doubt` 라 한다 <br>
중첩을 줄여 if 문을 분할하면 가독성은 나아지지만 if 문 출구가 늘어난다

그럼 어떻게 하란겨? <br>
값이 있는지 없는지 확인할 방법이 필요함, 그 필요성에 의해 나온 것이 Optional
<hr>

## Do not Keep close ties with Null

1. 에러의 근원이다<br>
   NPE
2. 코드를 더럽힌다<br>
   유지보수 & 가독성이 떨어짐
3. 아무 의미 없다<br>
   null 은 아무 의미도 표현하지 않는다
4. 자바 철학에 위배<br>
   개발자로부터 모든 pointer 를 숨겼는데 null 은 예외
5. 형식 시스템에 구멍을 만든다<br>
   null 은 어디에나 할당될 수 있고 시스템에 다른 부분으로 퍼져나갈 수 있다

<hr>

## Safe Domain Model By Optional instead of Null

값이 없을 수도 있는 도메인의 인스턴스 변수로는 `Optional<T>` 형식을 사용하도록 하고, 모든 null 을 Optional 로 대치할 필요는 없다 <br>
값이 필수로 있어야 하는 객체에는 사용하지 말 것

<hr>

## Optional Usage

Summary 참고<br>
기본형 Optional 은 사용 말 것 (ex. OptionalInt ...)
<hr>

## How to check Value in Optional

Summary 참고