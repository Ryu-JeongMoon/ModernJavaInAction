## Java는 왜 변화하는가?
SW 환경 변화, 기존의 자바는 core 하나만 사용
남는 core는 idle 상태이거나 외부 프로그램 사용
<br>이는 굉장히 비효율적 시간이 지나며 대량의 데이터를 처리해야 하는 상황이 왔고 멀티코어 활용, 컴퓨터 클러스터링의 필요성이 대두됨

## Java는 어떻게 변화했는가?
1. Stream Processing
기존의 방식에서는 데이터 처리를 파이프라인으로 연결하기 위해서 for 문 안에서 여러 메서드를 수행하는 방식이었다면
Stream<T>는 T 형식의 데이터 흐름을 선언적으로 처리할 수 있게 해준다
<br>(Unix & Linux의 파이프 (|)를 이용한 처리와 같다)

2. Behavior Parameterization
method를 다른 method의 parameter로 넘겨줄 수 있다
<br>ex) sort() 에 정렬 기준을 넘겨줄 때 사용

3. Parallelism & Shared Mutable Data
병렬로 실행될 때 공유 가변 데이터를 건드리지 않아야 한다
<br>synchronized를 활용한 코드를 작성할 수 있지만 이는 치명적인 단점이 있다
<br>일반적으로 각각의 core는 cache를 가지고 있어 빠르게 연산을 수행할 수 있는데 synchronized 키워드는 하나의 core 외에 나머지 core에 Lock을 걸어야 한다
Lock을 걸기 위해 core cache synchronization 필요 -> 추가적 비용 발생의 흐름으로 성능에 악영향이 있다

## Java Function
프로그래밍 세계에서 함수 == 메서드
<br>절차지향과 객체지향을 구분 짓기 위해 다른 이름을 사용한다
<br>객체지향의 개념이 확장되면서 메서드에 추가적인 개념이 붙었는데 객체 간에 함수를 호출하는 것이 아닌 메시지 기반 통신을 하고 메시지를 수신한 객체가 메시지를 처리하기 위한 방법을 메서드라 한다
<br>그에 따라 자바에서 함수란 수학적인 의미에서 함수와 같다 -> 부작용을 일으키지 않는 함수
<br>Predicate란 수학에서 인수로 값을 받아 true, false를 반환하는 함수를 의미

####First Class or Citizen
- 메서드와 람다를 일급 시민으로
<br>FilteringApples 참고, 람다 편리성 << 코드 명확성
<br>람다의 사용은 한 두번만 사용될 메서드를 대신 한다는 점에서 의미가 있다 코드가 복잡한 상황이라면 코드가 의미하는 바를 명확히 나타낼 수 있는 이름의 메서드를 만드는게 낫다 

## Stream
Java Application에서는 Collection을 만들고 데이터를 처리한다
<br>Collection에서는 반복 과정을 직접 처리해주어야 한다 
이런 방식의 반복을 external iteration이라 한다
<br> Stream API를 사용한다면 라이브러리 내부에서 반복을 처리하고 이런 방식을 internal iteration이라 한다

Multi Thread
<br>멀티 스레딩은 어렵다 각각의 스레드는 공유된 가변 데이터에 접근할 수 있고 값을 바꿀 수 있다 이렇게 된다며 어떤 스레드에 의해 바뀌었는지 알 수 없고, 이를 제어하려면 순서를 정해야 하는데 이는 멀티 스레딩 방식의 장점을 포기하는 것

Stream API는 컬렉션을 처리하면서 발생하는 모호함과 반복적인 코드 문제, 멀티코어 활용 어려움을 다 해결했다??!!
-> 반복되는 패턴으로 주어진 조건에 따라 필터링, 추출, 그룹화 등의 기능 수행, 여러 CPU core를 활용하는 방식은 forking step을 거친다 (divide and conquer 가능)

## Default Method and Java Module
Java 8 이전에는 Collection interface가 stream 또는 parallelStream 메서드를 지원하지 않았다 기존의 방식으로 해결하기 위해서는 Collection에 stream / parallelStream을 만들고 Collection을 구현하는 모든 class가 stream / parallelStream을 구현해야 했다 
이 방식은 말도 안 되게 많은 구현체들을 손봐야 한다
<br>자바 설계자들은 이를 interface default method로 해결했다
<br>-> TODO default method 추가 정리 필요