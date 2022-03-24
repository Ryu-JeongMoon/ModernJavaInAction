package JavaInAction.chap12;

import java.time.Instant;

public class InstantPractice {

  public static void main(String[] args) {
    Instant i1 = Instant.ofEpochSecond(3);
    Instant i2 = Instant.ofEpochSecond(3, 0);
    Instant i3 = Instant.ofEpochSecond(2, 1_000_000_000);
    Instant i4 = Instant.ofEpochSecond(4, -1_000_000_000);
    System.out.println("i1 = " + i1);
    System.out.println("i2 = " + i2);
    System.out.println("i3 = " + i3);
    System.out.println("i4 = " + i4);
  }
}
