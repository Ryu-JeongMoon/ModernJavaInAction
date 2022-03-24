package JavaInAction.chap17.rxjava;

import JavaInAction.chap17.TempInfo;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class TempObserver implements Observer<TempInfo> {

  @Override
  public void onSubscribe(Disposable disposable) {

  }

  @Override
  public void onNext(@NonNull TempInfo tempInfo) {
    System.out.println(tempInfo);
  }

  @Override
  public void onError(@NonNull Throwable throwable) {
    System.out.println(throwable.getMessage());
  }

  @Override
  public void onComplete() {
    System.out.println("Done");
  }
}
