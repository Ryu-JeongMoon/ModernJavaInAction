package JavaInAction.chap16.v1;

import java.util.List;
import java.util.function.Supplier;

public class BestPriceFinderMain {

  private static final BestPriceFinder bestPriceFinder = new BestPriceFinder();

  public static void main(String[] args) {
//        execute("sequential", () -> bestPriceFinder.findPricesSequential("myPhone27S"));
//        execute("parallel", () -> bestPriceFinder.findPricesParallel("myPhone27S"));
    execute("future", () -> bestPriceFinder.findPricesFuture("myPhone27S"));
//        execute("lazyFuture", () -> bestPriceFinder.findPricesLazyFuture("myPhone27S"));
  }

  private static void execute(String message, Supplier<List<String>> supplier) {
    long start = System.nanoTime();
    System.out.println("supplier.get() = " + supplier.get());
    long duration = (System.nanoTime() - start) / 1_000_000;
    System.out.println(String.format("%s done in %d msecs", message, duration));
  }
}
