## Utilization of Stream

### Filtering, Slicing, Mapping
- filtering : Predicate를 받고 true인 결과만 반환
- slicing
  - Predicate를 받는 takeWhile, dropWhile
  - 개수 제한 limit
  - 앞의 몇 요소 제끼는 skip
- mapping : 각 요소에 함수 적용
  - flatMapping : 2차원 배열의 평면화

### Searching, Matching, Reducing
- Slicing
  1. allMatch : Predicate 전부 true 인 경우 
  2. anyMatch : Predicate true 하나 이상인 경우 
  3. noneMatch : allMatch 반대 
  4. findFirst : Predicate true 하나 이상인 경우, 제일 첫번째 것 
  5. findAny : Predicate true 하나 이상인 경우 찾아지는 아무거나


- Matching
  - anyMatch, allMatch, noneMatch 는 __short-circuit__ 기법이 이용된다 
  - 자바의 논리 연산자처럼 작동
    - &&의 경우 첫번째 조건에 해당되지 않는다면 바로 연산 종료
    - ||의 경우 첫번째 조건에 해당되면 바로 연산 종료

  - findAny, findFirst 는 값이 없을 수도 있기 때문에 Optional 반환
  - 병렬 실행의 경우에는 첫번째 요소 찾기가 어려우니 findAny 사용할 것

- Reducing
  - 모든 스트림 요소를 처리해서 값으로 도출하는 과정
  - `메뉴의 모든 칼로리 합계를 구하시오` 와 같은 더 복잡한 질의 표현
  - 인수로 초기값과 BinaryOperator 를 넘겨주어야 한다 


### Numeric Stream
상태 있는 연산과 상태 없는 연산
- map & filter 와 같은 연산은 각 요소를 받아 0 또는 결과를 출력 스트림으로 넘긴다
- reduce, sum, max 와 같은 연산은 값을 누적시키는 연산이므로 이전의 연산 history를 알아야 한다
<br>이 때 값을 저장할 공간이 필요한데 이 공간은 한정되어 있다 또한 모든 요소가 버퍼에 추가되어 있어야 한다 
<br>__무한 스트림을 사용할 때 특히 조심해야 한다__ 이와 같은 연산을 내부 상태를 갖는 연산이라 한다 (stateful operation)

  
### Making a Stream from Multi Source
Summary3.java 참고


### Infinite Stream
Summary3.java 참고