package JavaInAction.chap17;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class FlowMain {

  public static void main(String[] args) {
    Observable<Long> onePerSec = Observable.interval(1, TimeUnit.SECONDS);
    onePerSec.blockingSubscribe(i -> System.out.println(TempInfo.fetch("New York")),
        (e) -> System.out.println("즉시 에러" + e.getMessage()));
  }

  public static Observable<TempInfo> getTemperature2(String town) {
    return Observable.create(emitter ->
        Observable.interval(2, TimeUnit.SECONDS).subscribe(i -> {
          if (!emitter.isDisposed()) {
            if (i >= 5) {
              emitter.onComplete();
            } else {
              try {
                emitter.onNext(TempInfo.fetch(town));
              } catch (Exception e) {
                emitter.onError(e);
              }
            }
          }
        }));
  }
}
