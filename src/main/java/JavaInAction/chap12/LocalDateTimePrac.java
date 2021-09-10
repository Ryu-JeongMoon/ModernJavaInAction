package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateTimePrac {

    public static void main(String[] args) {

        /**
         * LocalDateTime = LocalDate + LocalTime 복합 클래스
         */

        LocalDateTime dt1 = LocalDateTime.of(2017, Month.SEPTEMBER, 21, 11, 49, 53);
        System.out.println("dt1 = " + dt1);

        LocalDateTime dt2 = LocalDateTime.of(2018, 11, 13, 7, 14, 51);
        System.out.println("dt2 = " + dt2);

        LocalDate date = dt1.toLocalDate();
        LocalTime time = dt1.toLocalTime();

        LocalDateTime dt3 = LocalDateTime.of(date, time);
        System.out.println("dt3 = " + dt3);

        LocalDateTime localDateTime = date.atTime(time);
        System.out.println("localDateTime = " + localDateTime);

        LocalDateTime localDateTime1 = time.atDate(date);
        System.out.println("localDateTime1 = " + localDateTime1);
    }
}
