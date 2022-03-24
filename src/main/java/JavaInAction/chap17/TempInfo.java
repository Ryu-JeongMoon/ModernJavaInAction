package JavaInAction.chap17;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@Getter
@RequiredArgsConstructor
public class TempInfo {

  public static final Random random = new Random();

  private final String town;
  private final int temp;

  public static TempInfo fetch(String town) {
    if (random.nextInt(10) == 0) {
      throw new RuntimeException("Hello Error!");
    }

    return new TempInfo(town, random.nextInt(100));
  }

  @Override
  public String toString() {
    return town + " : " + temp;
  }
}
