package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Attribute {

  public static void main(String[] args) {
    LocalDate date1 = LocalDate.of(2017, 9, 21);
    LocalDate date2 = date1.withYear(2011);
    LocalDate date3 = date1.withDayOfMonth(25);
    LocalDate date4 = date1.with(ChronoField.MONTH_OF_YEAR, 2);
    LocalDate date5 = date1.plus(6, ChronoUnit.DAYS);
    LocalDate date6 = date1.plus(6, ChronoUnit.CENTURIES);
    LocalDate date7 = date1.plus(6, ChronoUnit.MONTHS);
    LocalDate date8 = date1.plus(6, ChronoUnit.MILLENNIA);
    LocalDate date9 = date1.minusYears(6);

    System.out.println("date1 = " + date1);
    System.out.println("date2 = " + date2);
    System.out.println("date3 = " + date3);
    System.out.println("date4 = " + date4);
    System.out.println("date5 = " + date5);
    System.out.println("date6 = " + date6);
    System.out.println("date7 = " + date7);
    System.out.println("date8 = " + date8);
    System.out.println("date9 = " + date9);

    // quiz 12-1
    LocalDate date = LocalDate.of(2014, 3, 18);
    date = date.with(ChronoField.MONTH_OF_YEAR, 9);
    date = date.plusYears(2).minusDays(10);
    date = date.withYear(2011);
    System.out.println("date = " + date);
  }
}
