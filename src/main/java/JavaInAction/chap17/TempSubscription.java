package JavaInAction.chap17;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

@RequiredArgsConstructor
public class TempSubscription implements Subscription {

  private static final ExecutorService executor = Executors.newSingleThreadExecutor();
  private final Subscriber<? super TempInfo> subscriber;
  private final String town;

  @Override
  public void request(long n) {
    executor.submit(() -> {
      for (long i = 0L; i < n; i++) {
        try {
          subscriber.onNext(TempInfo.fetch(town));
        } catch (Exception e) {
          subscriber.onError(e);
          break;
        }
      }
    });
  }

  @Override
  public void cancel() {
    subscriber.onComplete();
  }
}
