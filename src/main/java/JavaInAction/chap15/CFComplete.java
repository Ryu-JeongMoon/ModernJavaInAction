package JavaInAction.chap15;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static modernjavainaction.chap15.Functions.f;
import static modernjavainaction.chap15.Functions.g;

public class CFComplete {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    int x = 1337;

    //        1
    //        CompletableFuture<Integer> a = new CompletableFuture<>();
    //        executorService.submit(() -> a.complete(f(x)));
    //        int b = g(x);
    //        System.out.println("a.get() + b = " + a.get() + b);

    //        2
    CompletableFuture<Integer> b = new CompletableFuture<>();
    executorService.submit(() -> b.complete(g(x)));
    int a = f(x);
    System.out.println("a + b.get() = " + a + b.get());

    executorService.shutdown();
  }
}
