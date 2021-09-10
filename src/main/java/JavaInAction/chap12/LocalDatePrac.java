package JavaInAction.chap12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.IsoEra;
import java.time.temporal.ChronoField;

public class LocalDatePrac {

    public static void main(String[] args) {

        java.time.LocalDate localDate = java.time.LocalDate.of(2021, 6, 17);
        System.out.println("localDate = " + localDate);

        int year = localDate.getYear();
        System.out.println("year = " + year);

        Month month = localDate.getMonth();
        System.out.println("month = " + month);

        int dayOfMonth = localDate.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);

        int monthValue = localDate.getMonthValue();
        System.out.println("monthValue = " + monthValue);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("dayOfWeek = " + dayOfWeek);

        int dayOfYear = localDate.getDayOfYear();
        System.out.println("dayOfYear = " + dayOfYear);

        IsoEra era = localDate.getEra();
        System.out.println("era = " + era);

        java.time.LocalDate now = java.time.LocalDate.now();
        System.out.println("now = " + now);

        int year1 = localDate.get(ChronoField.YEAR);
        System.out.println("year1 = " + year1);

        int month1 = localDate.get(ChronoField.MONTH_OF_YEAR);
        System.out.println("month1 = " + month1);

        int day1 = localDate.get(ChronoField.DAY_OF_MONTH);
        System.out.println("day1 = " + day1);

        LocalDate localDate1 = LocalDate.now();
        int year2 = localDate1.getYear();
        System.out.println("year2 = " + year2);

        Month month2 = localDate1.getMonth();
        System.out.println("month2 = " + month2);

        int dayOfMonth1 = localDate1.getDayOfMonth();
        System.out.println("dayOfMonth1 = " + dayOfMonth1);


    }
}
