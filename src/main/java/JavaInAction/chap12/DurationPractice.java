package JavaInAction.chap12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class DurationPractice {

    public static void main(String[] args) {
        LocalDateTime time1 = LocalDateTime.of(2019, 11, 14, 22, 45, 44);
        LocalDateTime time2 = LocalDateTime.of(2021, 3, 8, 5, 13, 49);
        Duration d1 = Duration.between(time1, time2);
        System.out.println("d1 = " + d1);

        // toTotalMonths() method 로 달 차이 편하게 계산?!
        LocalDate date1 = LocalDate.of(2021, 3, 11);
        LocalDate date2 = LocalDate.of(2021, 8, 11);
        Period period = Period.between(date1, date2);
        System.out.println("period = " + period);
        System.out.println("period.toTotalMonths = " + period.toTotalMonths());

        
    }
}
