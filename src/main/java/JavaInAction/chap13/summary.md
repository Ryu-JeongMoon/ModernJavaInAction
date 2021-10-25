# Default Method
인터페이스와 관련 메서드는 한 몸처럼 움직임 <br>
인터페이스를 구현하는 클래스는 모든 메서드를 구현하거나 추상 클래스로 만듬 <br>
메서드를 바꿔야 하는 경우에 문제 발생, 인터페이스 구현한 모든 클래스에 변경 가해짐 <br>
이러한 문제 때문에 등장한 것이 디폴트 메서드

<hr>

## What is Default Method

<hr>

## Maintain Compatibility with Evolving API
계속해서 진화해가는 언어에서 이미 정의된 Interface, Abstract class 는 어떻게 바꿔야 하는가 ? `Default Method`

<hr>

## Usage Pattern of Default Method
Collection Library 와 같이 여러 곳에서 이미 사용되고 있는 인터페이스를 고치기 위해서 자바 8은 default method 를 제공


<hr>

## Solving Rules
같은 시그니처를 정의한 두 인터페이스를 상속하게 되는 상황 <br>
`public class C implements B, A` 인터페이스의 순서에 따라 호출이 달라진다 (B의 메서드 호출됨)

1. 클래스가 인터페이스보다 우선권을 가진다
2. 현 상황에서 `B extends A` 이므로 서브 인터페이스 B가 이긴다
3. 클래스가 명시적으로 오버라이딩 후 호출해야 한다

