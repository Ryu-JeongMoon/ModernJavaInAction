package JavaInAction.chap15;

import static JavaInAction.chap15.Functions.f;
import static JavaInAction.chap15.Functions.g;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int x = 1337;
        long start = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<Integer> y = executorService.submit(() -> f(x));
        Future<Integer> z = executorService.submit(() -> g(x));
        System.out.println(y.get() + z.get());

        long end = System.nanoTime();
        System.out.println("elapsed time : " + (end - start) + "ns");

        executorService.shutdown();
    }
}
