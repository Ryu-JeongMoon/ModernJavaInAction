# CompletableFuture, Base of Reactive Programming
최근 소프트웨어 개발 방법을 획기적으로 개선시킨 두가지
1. 하드웨어 <br>
옛날 옛적엔 하나거나 비쌌던 멀티코어 프로세서가 발전하면서 멀티코어 프로세서를 얼마나 잘 쥐어짜는지가 핵심이 되었다
2. 애플리케이션 구성 <br>
하나의 커다란 서비스 덩어리를 잘게 쪼개 MSA로 간다, 개발 단위는 작아졌기 때문에 빨리빨리 가능하지만 프로젝트 내의 메서드 호출이 네트워크를 통한 통신으로 바뀌었다

<hr>

## Java Evolved for Concurrency
초기의 자바는 Runnable, Thread 를 제공했으나 사용이 호락호락 하지 않았다 <br>

자바 5. <br>
더 표현력 있는 동시성을 지원하기 위해 task 제출과 thread 실행을 분리해주는 ExecutorService 제공 <br>
Runnable, Thread 의 변형을 반환하는 `Callable<T>`, `Future<T>`, 제네릭 제공 <br>

자바 7. <br>
분할정복 알고리즘의 Fork/Join 을 구현하는 java.util.concurrent.RecursiveTask 추가<br>

자바 8. <br>
스트림, 람다 지원에 의한 병렬 프로세싱 (parallelStream) 추가

자바 9. <br>
pub-sub protocol 의 java.util.concurrent.Flow 인터페이스 추가 <br>
요놈들을 통해 Reactive Programming 이 가능하단 것이군
<hr>

## Executor and Thread Pool
ExecutorService 는 태스크 제출과 실행을 분리할 수 있는 기능을 제공한다

직접 운영체제 스레드에 접근해 생성, 종료하려면 비싼 cost <br>
`newFixedThreadPool(int nThreads)` 같은 factory method 로 n개의 워커 스레드를 가지는 스레드풀을 만든다 <br>
스레풀에는 큐가 있어 수천, 수만개의 태스크를 제출하더라도 문제 없이 큐에 대기하고 있다가 실행하면 된다 <br>
커스텀 옵션으로 큐의 크기, 거부 정책, 우선순위 등을 설정할 수 있다
<hr>

## Box and Channel Model
동시성 모델을 잘 설계하기 위해서는 그림을 그려봐라
