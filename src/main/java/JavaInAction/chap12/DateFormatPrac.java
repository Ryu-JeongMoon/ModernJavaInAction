package JavaInAction.chap12;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatPrac {

  public static void main(String[] args) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date1 = LocalDate.of(2022, 3, 5);
    String s1 = date1.format(formatter);
    System.out.println("s1 = " + s1);

    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd//HH/mm/ss");
    LocalDateTime dateTime = LocalDateTime.now();
    String s2 = dateTime.format(formatter1);
    System.out.println("s2 = " + s2);

    LocalDateTime date = LocalDateTime.now();

    ZoneId rome = ZoneId.of("Europe/Rome");
    System.out.println("rome = " + rome);
    ZonedDateTime zonedDateTime = date.atZone(rome);
    System.out.println("zonedDateTime = " + zonedDateTime);

    ZoneId london = ZoneId.of("Europe/London");
    System.out.println("london = " + london);
    ZonedDateTime zoneLondon = date.atZone(london);
    System.out.println("zoneLondon = " + zoneLondon);

    ZoneId paris = ZoneId.of("Europe/Paris");
    System.out.println("paris = " + paris);


  }
}
