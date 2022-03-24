package JavaInAction.chap16;

import JavaInAction.chap16.v1.Util;
import modernjavainaction.chap16.Discount.Code;

import java.util.Random;

import static JavaInAction.chap16.v1.Util.format;

public class Shop {

  private final String name;
  private final Random random;

  public Shop(String name) {
    this.name = name;
    random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
  }

  public String getPrice(String product) {
    double price = calculatePrice(product);
    Code code = Code.values()[random.nextInt(Code.values().length)];
    return name + ":" + price + ":" + code;
  }

  public double calculatePrice(String product) {
//        Util.delay();
    Util.randomDelay();
    return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
  }

  public String getName() {
    return name;
  }
}
