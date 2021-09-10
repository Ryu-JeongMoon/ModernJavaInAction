package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.LocalTime;

public class LocalTimePrac {

    public static void main(String[] args) {

        LocalTime time = LocalTime.of(13, 45, 20);
        System.out.println("time = " + time);

        int hour = time.getHour();
        System.out.println("hour = " + hour);

        int minute = time.getMinute();
        System.out.println("minute = " + minute);

        int second = time.getSecond();
        System.out.println("second = " + second);

        // 문자열로 날짜 변환, parse 메서드 사용
        LocalDate date = LocalDate.parse("2017-10-22");
        System.out.println("date = " + date);

        LocalTime localTime = LocalTime.parse("13:55:59");
        System.out.println("localTime = " + localTime);
    }
}
