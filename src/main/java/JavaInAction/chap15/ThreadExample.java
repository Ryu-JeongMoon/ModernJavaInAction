package JavaInAction.chap15;

import static JavaInAction.chap15.Functions.f;
import static JavaInAction.chap15.Functions.g;

public class ThreadExample {

    public static void main(String[] args) throws InterruptedException {
        int x = 1337;
        long start = System.nanoTime();

        Result result = new Result();

        Thread t1 = new Thread(() -> result.left = f(x));
        Thread t2 = new Thread(() -> result.right = g(x));
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(result.left + result.right);

        long end = System.nanoTime();
        System.out.println("elapsed time : " + (end - start) + "ns");
    }

    private static class Result {

        private int left;
        private int right;
    }
}
