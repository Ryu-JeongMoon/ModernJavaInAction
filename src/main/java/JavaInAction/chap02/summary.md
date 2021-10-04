## Behavior Parameterization
동작 파라미터화를 이용하면 어떻게 실행할지를 결정하는 코드를 인수로 전달할 수 있다
<br>변화하는 요구사항에 유연하게 대응할 수 있다
- 메서드 내부적으로 다양한 동작을 수행할 수 있도록 코드를 인수로 받는다
- 자바 8 이전에는 익명 클래스로 인한 지저분한 코드가 많았는데 자바 8 이후 람다 덕분에 간결한 표현이 가능하다
- 정렬, 스레드, GUI 처리 등에 동작 파라미터화를 적용할 수 있다

## 변화하는 요구 사항에 대응
반복되는 패턴이 1 ~ 2개라면 복사, 붙여넣기로 처리해도 되겠지만 미래의 변경 가능성까지 생각했을 때 패턴이 늘어날 가능성이 있다면 추상화를 시도한다 문제 상황을 인지하고 어떻게 처리하는게 나을지 선택해야 한다 
- 모든 속성을 parameter로 받을지 
  - 하나의 거대한 필터링 메서드가 만들어진다 <u>SRP 위반</u>
- 각각의 메서드로 나눌지 
  - 반복되는 형태의 메서드가 계속 늘어난다 <u>DRY 위반</u>
- 인수로 Predicate를 받을지 
  - Predicate interface를 만들고 다형성 이용해 넣어줄지 <u>DRY 위반</u>
  - 람다로 넣어줄지 <u>재사용 어려우나 가독성 좋음</u>

##Enum Class  == or equals()
Enum은 상수를 사용, 인스턴스가 하나임을 보장한다 equals()를 사용할 필요 없이 == 을 사용하면 된다 equals()도 결국 == 을 사용하는 것이고 비교하는 값에 null이 들어오면 NPE 터질 가능성이 생긴다
<br>ex) null.equals(Enum.~) NPE O, Enum.~.equals(null) NPE X

##Strategy Pattern
Algorithm family 에서 하나의 algorithm 선택해서 적용하는 기법
<br>Runtime에 적용될 메서드가 다른 경우 사용 (다형성 이용)

##Abstraction for List type
타입 추상화, Summary.java 참고

##Sort by Comparator
Comparable vs Comparator
- Comparable compareTo(T o) 자기 자신과 매개변수 객체를 비교 
- Comparator compare(T o1, T o2) 두 매개변수 객체를 비교

##Generic
Generic 클래스 내부에서 지정하는 것이 아닌 외부에서 사용자에 의해 지정되는 것을 의미
1. 제네릭을 사용하면 잘못된 타입이 들어올 수 있는 것을 컴파일 단계에서 방지할 수 있다.
2. 클래스 외부에서 타입을 지정해주기 때문에 따로 타입을 체크하고 변환해줄 필요가 없다. 즉, 관리하기가 편하다.
3. 비슷한 기능을 지원하는 경우 코드의 재사용성이 높아진다.

##Callable
ExecutorService 인터페이스는 task submission & execution 연관성을 끊어준다