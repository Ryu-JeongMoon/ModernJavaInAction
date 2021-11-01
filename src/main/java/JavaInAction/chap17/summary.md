# Reactive Programming
리액티브가 뭐고 왜 중요한데 ?!

- 우리의 어플은 나날이 자라서 페타바이트 단위 이상으로 구성되고 매일 증가 중!!
- 스마트폰부터 멀티코어 프로세서를 가진 클라우드 클러스터까지 다양한 환경에서 실행
- 멈추는 시간은 용납 못 하고 사람이 인식할 수 없을 정도의 응답 속도를 원한다

쉽게 생각해보면 넷플릭스나 페북 같은 엄청난 트래픽을 받는 곳들이 요청을 동기적으로 처리한다면? 난리남
<hr>

## Reactive Manifesto
- Responsive - 빨라야 하고 일정해야 함
- Resilient - 장애가 발생해도 반응해야 함
- Elastic - 부하가 늘어나면 처리하는 능력도 늘어나야 함
- Message-driven - 컴포넌트, 애플리케이션 간 약한 결합을 유지, 서로 간의 통신이 메시지 기반으로 이뤄져야함 

### Reactivity of Application Level 
리액티브 애플리케이션은 비동기적으로 작업을 수행할 수 있다 <br>
이벤트 스트림을 블록하지 않고 수행한다는 것은 멀티코어 CPU 의 사용률을 극대화 하는 것 <br>
스레드를 퓨터, 액터, 콜백 등을 이용해 이벤트 루프와 공유하고 처리를 담당할 이벤트를 변환하고 관리한당

이들은 스레드를 직접 다루는 것보다 간편하고 저수준의 문제에서 벗어나게 해준다 ex) deadlock, competition <br>

만약 블록되는 작업을 수행해야 한다면?? <br>
RxJava, Akka 같은 framework or library 에서 이들을 지정한 스레드풀에서 실행시켜 전체의 실행에 차질이 없도록 한다

### Reactivity of System Level
시스템 수준에서의 리액티브는 더 어렵다 <br>
전체가 조화를 이루어야 하고, 앞서 말한 리액티브 매니페스토의 원칙을 전부 지켜야 한다 <br>
빠르며 일정한 반응을 보내줄 수 있고, 장애가 나도 멈추지 않으며 부하가 발생해도 처리량이 줄지 않는다!!!!
<hr>

## Reactive Stream and Flow API
리액티브 프로그래밍이란 리액티브 스트림을 사용해 프로그래밍 하는 것이당 <br>
넷플릭스, 레드햇, 트위터, 라이트벤드 등 개쩌는 회사들이 모여서 Reactive Streams Project 를 만들고 정의했다 <br>
최소한의 기능을 가진 네개의 인터페이스 Publisher, Subscriber, Subscription, Processor 를 만들었다

자바 9에서는 이를 java.util.concurrent.Flow 로 추가했다 <br>
publisher 가 발행하고 subscriber 가 소비한다, publisher 가 발행하는 이벤트에 event listener 로 등록하는 것이당 <br>
subscription 이 제어 흐름, 역압력을 관리한다


TODO 추후 정리, 개어렵당