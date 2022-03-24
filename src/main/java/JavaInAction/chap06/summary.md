## Collecting Data from Stream

### Collectors class

Collector 인터페이스의 구현을 어떻게 하느냐에 따라 스트림의 요소를 도출하는 방식을 지정

- 스트림 요소를 하나의 값으로 리듀스하고 요약
    - 총합을 구하는 등
- 요소 그룹화
    - toList(), groupingBy() 등
- 요소 분할
    - Predicate 를 받아 true / false 에 따라 나눔

### Data Stream Reduce

### Special Reduce Operation

### Data Grouping and Partition

- 다수준 그룹화
- 분할 : 참 / 거짓 두가지 요소 스트림 리스트 모두 유지한다는 것이 장점

### Custom Collector