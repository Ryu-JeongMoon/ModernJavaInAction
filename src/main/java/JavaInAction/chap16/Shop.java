package JavaInAction.chap16;

import static JavaInAction.chap16.v1.Util.delay;
import static modernjavainaction.chap16.Util.format;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import modernjavainaction.chap16.Discount;
import modernjavainaction.chap16.Util;

public class Shop {

    private final String name;
    private final Random random;

    public Shop(String name) {
        this.name = name;
        random = new Random(name.charAt(0) * name.charAt(1) * name.charAt(2));
    }

    public String getPrice(String product) {
        double price = calculatePrice(product);
        modernjavainaction.chap16.Discount.Code code = modernjavainaction.chap16.Discount.Code.values()[random.nextInt(
            Discount.Code.values().length)];
        return name + ":" + price + ":" + code;
    }

    public double calculatePrice(String product) {
        Util.delay();
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

    public String getName() {
        return name;
    }
}
