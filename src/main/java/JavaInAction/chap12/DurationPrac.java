package JavaInAction.chap12;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class DurationPrac {

    public static void main(String[] args) {

        LocalTime time1 = LocalTime.of(11, 52, 34);
        LocalTime time2 = LocalTime.now();
        Duration duration = Duration.between(time1, time2);
        System.out.println("duration = " + duration);

        Period period = Period.between(LocalDate.of(2021, 6, 18), LocalDate.of(2021, 7, 25));
        System.out.println("period = " + period);

        // withAttribute 를 이용한 날짜 변경
        LocalDate date1 = LocalDate.now();
        System.out.println("date1 = " + date1);

        LocalDate date2 = date1.withYear(2022);
        System.out.println("date2 = " + date2);

        LocalDate date3 = date2.withMonth(9);
        System.out.println("date3 = " + date3);

        LocalDate date4 = date3.withDayOfMonth(22);
        System.out.println("date4 = " + date4);

        // quiz 12-1
        LocalDate date = LocalDate.of(2014, 3, 18);
        System.out.println("date = " + date);

        date = date.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println("date = " + date);

        date = date.plusYears(2).minusDays(10);
        System.out.println("date = " + date);

        System.out.println("date = " + date.withYear(2011));

        // quiz 12-2
        date = date.with(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                dayToAdd = 2;
            }

            return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        System.out.println("date = " + date);

        LocalDate date5 = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("s1 = " + s1);

        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("s2 = " + s2);

        LocalDate parse1 = LocalDate.parse(s1, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println("parse1 = " + parse1);

        LocalDate parse2 = LocalDate.parse(s2, DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("parse2 = " + parse2);

        parse1 = parse1.plusYears(22).plusMonths(5);
        System.out.println("parse1 = " + parse1);
    }
}
