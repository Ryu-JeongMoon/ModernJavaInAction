# New Date and Time API
자바 8 이전까지의 날짜 & 시간 API 를 개선한 새로운 API 제공 <br>
<hr>

## Why Java 8 provide new Date and Time API
자바 1.0 java.util.Date 하나로 날짜와 시간 관련 기능 제공 <br>
날짜를 의미하는 Date 와 다르게 특정 시점을 밀리초 단위로 표현하는 것이 문제 <br>
2017년 9월 21일 표현 `Date date = new Date(117, 8, 21)` -> `Thu Sep 21 00:00:00 CET 2017` <br>
1900년을 기준으로 첫번째 인자만큼 년도 수를 늘리고 월은 0부터 시작하기 때문에 0 - 11로 표현, 결과가 직관적이지 않음

자바 1.1에서 Date 는 deprecated, java.util.Calendar 가 새로 제공됨 <br>
1900부터 시작하는 오프셋은 없어졌지만 여전히 월의 인덱스는 0-11 <br>
Date, Calendar 로 인해 혼란 가중되었고 Joda-Time 과 같은 third-party library 많이 사용됨 <br>
Oracle 에서 Joda-Time 여러 기능을 java.time 패키지로 추가하여 새로운 API 제공
<hr>

## Understandable Expression of Date and Time by Machine or People
사람은 주, 날짜, 시간, 분으로 날짜와 시간 계산, 기계는 연속된 시간에서 특정 지점을 하나의 큰 수로 표현 <br>
java.time.Instant 에서는 unix epoch time (1970.1.1 00:00:00 UTC) 기준으로 특정 지점까지의 시간을 초로 표현 <br>
~~~
Instant i1 = Instant.ofEpochSecond(3);
Instant i2 = Instant.ofEpochSecond(3, 0);
Instant i3 = Instant.ofEpochSecond(2, 1_000_000_000);
Instant i4 = Instant.ofEpochSecond(4, -1_000_000_000);
        
i1 = 1970-01-01T00:00:03Z
i2 = 1970-01-01T00:00:03Z
i3 = 1970-01-01T00:00:03Z
i4 = 1970-01-01T00:00:03Z
~~~
Instant 는 기계 전용 Util
<hr>

## Control, Formatting and Parsing of Date
- LocalDate, Time, DateTime 에서 Attribute 이용해 간단한 계산 & 비교 가능
- TemporalAdjuster 를 사용하여 날짜 조건 구현 가능 (ex. 다음 근무일 등)
- DateTimeFormatter 이용해 파싱 가능
<hr>


## Dealing Timezone and Calendar
