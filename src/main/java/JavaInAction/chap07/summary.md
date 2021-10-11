# Parallel Data Processing and Performance

<hr>

## Parallel Processing
stream에서는 간단하게 parallel()을 입력하는 것만으로도 병렬 스트림으로 수행할 수 있다<br> 
병렬 실행 boolean flag를 true로 만들어주는 역할을 한다

그럼 thread는 어디서 제공 받을까?

내부적으로 fork & join framework를 이용하고 프로세서 수에 상응하는 thread를 갖고 있다 
~~~
thread 수 찍어보기

System.out.printf("number of processor = %s\n", Runtime.getRuntime().availableProcessors());
-> number of processor = 12
~~~

<hr>

## Performance Analysis of Parallel Stream
java microbenchmark harness library, jmh를 이용한 성능 측정 <br>
jar 파일 만들고 java -jar 로 실행하거나 annotation 방식으로도 사용 가능

~~~
# JMH version: 1.33
# VM version: JDK 11.0.10, Java HotSpot(TM) 64-Bit Server VM, 11.0.10+8-LTS-162
# VM invoker: /Library/Java/JavaVirtualMachines/jdk-11.0.10.jdk/Contents/Home/bin/java
# VM options: -Xms4G -Xmx4G
# Blackhole mode: full + dont-inline hint (default, use -Djmh.blackhole.autoDetect=true to auto-detect)
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Average time, time/op
# Benchmark: JavaInAction.chap07.ParallelStreamBenchmark.sequentialSum
~~~
위는 성능 측정 기본 스펙을 나타냈다 성능 측정에는 jmh 1.33, G1GC, jdk 11 을 이용하였다

~~~
Benchmark                                                  Mode  Cnt   Score   Error  Units
JavaInAction.chap07.ParallelStreamBenchmark.sequentialSum  avgt   10  99.506 ± 3.256  ms/op

Benchmark                                                 Mode  Cnt  Score   Error  Units
JavaInAction.chap07.ParallelStreamBenchmark.iterativeSum  avgt   10  8.708 ± 0.294  ms/op

Benchmark                                                Mode  Cnt   Score   Error  Units
JavaInAction.chap07.ParallelStreamBenchmark.parallelSum  avgt   10  96.924 ± 1.954  ms/op
~~~
jmh 1.33을 이용해 측정해보니 책의 내용과는 상당히 다른 결과가 나왔다 책에서는 parallel이 약 604.059 ms/op 로 측정되어 sequential에 비해 5배 정도 느린 성능을 보이는데 내가 돌린 실험에서는 parallel이 근소하게 sequential에 앞서고 iterative는 둘과 비교할 수 없을 정도로 빠르다 그 원인은 아래와 같이 유추 가능하다

- stream의 결과로 박싱된 객체가 만들어져 언박싱 비용 발생
- 반복 작업은 병렬 수행을 위한 독립 단위로 나누기 어려움

<br>
그렇다면 어떻게 해야 parallelStream()을 효과적으로 사용할 수 있는 것일까?
1. Boxing, Unboxing 비용을 없애기 위해 기본형 특화 스트림을 사용한다 - IntStream / LongStream ...
2. 독립 단위로 자르기 쉽도록 범위를 주는 방식으로 생성한다 - IntStream.range() / LongStream.rangeClosed() ...

아래는 두가지 방식을 사용해 순차적으로 실험한 rangedSum, 병렬로 실험한 parallelRangedSum 의 결과다
~~~
Benchmark                                              Mode  Cnt  Score   Error  Units
JavaInAction.chap07.ParallelStreamBenchmark.rangedSum  avgt   10  5.868 ± 0.310  ms/op

Benchmark                                                      Mode  Cnt  Score   Error  Units
JavaInAction.chap07.ParallelStreamBenchmark.parallelRangedSum  avgt   10  3.232 ± 3.597  ms/op
~~~
<br>

결과에서 특이한 부분이 있었는데 첫번째 사이클에서는 두번째 사이클에 비해 5배나 느린 결과를 보이다가 <br>
50% 진행한 이후로 대략 1ms 이하로 내려가는 모습을 볼 수 있었다 다시 한번 돌리니 이번엔 반대의 결과를 얻었다 <br>
jmh 측정 결과를 언제나 믿을 수 있는건 아니고 여러번 돌려서 평균값을 얻어내는 방식으로 이용해야 할 것 같다
~~~
# Run progress: 0.00% complete, ETA 00:03:20
# Fork: 1 of 2
# Warmup Iteration   1: 5.001 ms/op
# Warmup Iteration   2: 5.186 ms/op
# Warmup Iteration   3: 5.620 ms/op
# Warmup Iteration   4: 5.295 ms/op
# Warmup Iteration   5: 5.261 ms/op
Iteration   1: 5.469 ms/op
Iteration   2: 5.540 ms/op
Iteration   3: 5.253 ms/op
Iteration   4: 5.600 ms/op
Iteration   5: 5.573 ms/op

# Run progress: 50.00% complete, ETA 00:01:40
# Fork: 2 of 2
# Warmup Iteration   1: 1.070 ms/op
# Warmup Iteration   2: 0.998 ms/op
# Warmup Iteration   3: 0.983 ms/op
# Warmup Iteration   4: 0.978 ms/op
# Warmup Iteration   5: 0.980 ms/op
Iteration   1: 0.977 ms/op
Iteration   2: 0.977 ms/op
Iteration   3: 0.976 ms/op
Iteration   4: 0.976 ms/op
Iteration   5: 0.974 ms/op
~~~

최종 정리로 병렬 실행이 항상 옳은 건 아니다라는 결론을 냈다 <br>
context switching 때문일 것으로 판단되고 context switching 비용을 넘어서는 경우에만 병렬 실행이 옳다  
또한 잘못된 방식으로 병렬 실행을 사용하고 있는건 아닌지 주의해야 한다

<hr>

## Fork / Join Framework
병렬화할 수 있는 작업을 재귀적으로 작은 작업으로 분할하고 sub-task를 thread pool의 worker thread에 분산 할당하는 ExecutorService interface를 구현한다 

- Race Condition <br>
critical section (임계구역) 에 접근하려는 multi thread or process 간 경쟁을 의미

- 작업 훔치기 <br>
병렬처리된 코어에서는 비슷한 시간에 작업이 끝날 것이라 예상되지만 현실은 그렇지 않다 I/O 작업이던지 외부 네트워크 때문이던지 지연이 발생할 수 있고 Fork / Join Framework 는 이러한 비효율을 작업 훔치기로 해결한다 자신에게 할당된 큐의 모든 일이 끝났다고 idle 로 가는 것이 아니라 다른 thread queue 에 있는 작업을 가져와 수행한다

<hr>

## Data Partitioning By Spliterator
자바 8은 Spliterator 라는 새로운 interface 를 제공한다
Iterator 와 유사한 기능을 수행하는데 병렬 작업에 특화되어 있다 <br>
tryAdvance 로 다음 요소를 확인하고 trySplit 으로 분할하고
estimateSize 로 대략적인 크기를 확인해 공평하게 분할할 수 있게 한다

######예제 추가 필요 넘어렵당