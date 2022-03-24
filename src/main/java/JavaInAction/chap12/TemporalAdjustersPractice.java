package JavaInAction.chap12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class TemporalAdjustersPractice {

  public static void main(String[] args) {
    LocalDate date1 = LocalDate.of(2014, 3, 18);
    LocalDate date2 = date1.with(nextOrSame(DayOfWeek.SUNDAY));
    LocalDate date3 = date2.with(lastDayOfMonth());

    System.out.println("date1 = " + date1);
    System.out.println("date2 = " + date2);
    System.out.println("date3 = " + date3);

    // quiz 12-2
    TemporalAdjuster nextWorkingDate = TemporalAdjusters.ofDateAdjuster(temporal -> {
          DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));

          if (dow == DayOfWeek.FRIDAY || dow == DayOfWeek.SATURDAY) {
            temporal = temporal.with(nextOrSame(DayOfWeek.MONDAY));
          } else {
            temporal = temporal.plusDays(1);
          }
          return temporal;
        }
    );

    LocalDate now = LocalDate.of(2021, 10, 30);
    LocalDate workingDate = now.with(nextWorkingDate);

    System.out.println("workingDate = " + workingDate);

  }
}
