package JavaInAction.chap17;

import java.util.concurrent.Flow.Publisher;

public class MainCelsius {

    public static void main(String[] args) {
        getCelsiusTemperature("Seoul")
            .subscribe(new TempSubscriber());
    }

    public static Publisher<TempInfo> getCelsiusTemperature(String town) {
        return subscriber -> {
            TempProcessor tempProcessor = new TempProcessor();
            tempProcessor.subscribe(subscriber);
            tempProcessor.onSubscribe(new TempSubscription(tempProcessor, town));
        };
    }
}
