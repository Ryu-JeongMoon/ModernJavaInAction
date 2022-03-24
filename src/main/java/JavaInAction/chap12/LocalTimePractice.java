package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimePractice {

  public static void main(String[] args) {
    LocalTime time = LocalTime.of(13, 45, 20);

    int second = time.getSecond();
    int minute = time.getMinute();
    int hour = time.getHour();
    System.out.println("second = " + second);
    System.out.println("minute = " + minute);
    System.out.println("hour = " + hour);

    LocalDate parseDate = LocalDate.parse("2017-09-21");
    LocalTime parseTime = LocalTime.parse("13:45:20");
    System.out.println("parseDate = " + parseDate);
    System.out.println("parseTime = " + parseTime);
  }
}
