package JavaInAction.chap12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.IsoChronology;
import java.time.chrono.IsoEra;
import java.time.temporal.ChronoField;

public class LocalDatePractice {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 9, 21);
        System.out.println("date = " + date);

        int year = date.getYear();
        Month month = date.getMonth();
        int monthValue = date.getMonthValue();

        System.out.println("year = " + year);
        System.out.println("month = " + month);
        System.out.println("monthValue = " + monthValue);

        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDayOfMonth();
        int dayOfYear = date.getDayOfYear();
        System.out.println("dayOfWeek = " + dayOfWeek);
        System.out.println("dayOfMonth = " + dayOfMonth);
        System.out.println("dayOfYear = " + dayOfYear);

        IsoEra era = date.getEra();
        IsoChronology chronology = date.getChronology();
        boolean leapYear = date.isLeapYear();
        System.out.println("era = " + era);
        System.out.println("chronology = " + chronology);
        System.out.println("leapYear = " + leapYear);

        // TemporalField 인터페이스 구현한 ChronoField 를 통해 원하는 정보 찾기
        int chronoDay = date.get(ChronoField.DAY_OF_MONTH);
        int chronoMonth = date.get(ChronoField.MONTH_OF_YEAR);
        int chronoYear = date.get(ChronoField.YEAR);
        System.out.println("chronoDay = " + chronoDay);
        System.out.println("chronoMonth = " + chronoMonth);
        System.out.println("chronoYear = " + chronoYear);
    }
}
