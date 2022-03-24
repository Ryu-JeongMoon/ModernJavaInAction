package JavaInAction.chap05;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Trader {

  private final String name;
  private final String city;
}
