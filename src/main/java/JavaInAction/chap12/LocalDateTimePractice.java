package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimePractice {

  public static void main(String[] args) {
    // LocalDateTime = LocalDate + LocalTime 복합 클래스

    LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 13, 45, 20);

    LocalDate date = LocalDate.of(2011, 11, 15);
    LocalTime time = LocalTime.of(9, 11, 45);
    LocalDateTime dt2 = LocalDateTime.of(date, time);
    LocalDateTime dt3 = date.atTime(16, 33, 43);
    LocalDateTime dt4 = date.atTime(time);
    LocalDateTime dt5 = time.atDate(date);

    System.out.println("dt1 = " + dt1);
    System.out.println("dt2 = " + dt2);
    System.out.println("dt3 = " + dt3);
    System.out.println("dt4 = " + dt4);
    System.out.println("dt5 = " + dt5);
  }
}
