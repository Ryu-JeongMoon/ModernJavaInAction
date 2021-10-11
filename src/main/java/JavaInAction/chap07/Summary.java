package JavaInAction.chap07;

import java.util.stream.LongStream;
import java.util.stream.Stream;
import modernjavainaction.chap07.ParallelStreamsHarness;

public class Summary {

    public static void main(String[] args) {
//        long start = System.nanoTime();
//        System.out.println(sequentialSum(30000));
//        System.out.println(parallelSum(30000));
//        System.out.println(sideEffectSum(30000));
//        System.out.printf("time : %fs\n", (System.nanoTime() - start) / Math.pow(10, 9));

        System.out.printf("ForkJoin sum done in: %d msecs",
            ParallelStreamsHarness.measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L));

    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .parallel()
            .reduce(0L, Long::sum);
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();

        // 병렬로 실행할 시 race condition 발생 !!
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    static class Accumulator {

        public long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
