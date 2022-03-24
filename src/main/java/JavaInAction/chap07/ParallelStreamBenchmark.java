package JavaInAction.chap07;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@State(value = Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Fork(value = 2, jvmArgs = {"-Xms4G", "-Xmx4G"})
public class ParallelStreamBenchmark {

  private static final long N = 10_000_000L;

  //        @Benchmark
  public long sequentialSum() {
    return Stream.iterate(1L, i -> i + 1)
        .limit(N)
        .reduce(0L, Long::sum);
  }

  //    @Benchmark
  public long iterativeSum() {
    long result = 0;
    for (int i = 0; i <= N; i++) {
      result += i;
    }
    return result;
  }

  //    @Benchmark
  public long parallelSum() {
    return Stream.iterate(1L, i -> i + 1)
        .limit(N)
        .parallel()
        .reduce(0L, Long::sum);
  }

  //        @Benchmark
  public long rangedSum() {
    return LongStream.range(1, N).reduce(0L, Long::sum);
  }

  @Benchmark
  public long parallelRangedSum() {
    return LongStream.rangeClosed(1, N)
        .parallel()
        .reduce(0L, Long::sum);
  }

  @TearDown(Level.Invocation)
  public void tearDown() {
    System.gc();
  }
}
