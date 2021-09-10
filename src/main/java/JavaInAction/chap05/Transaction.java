package JavaInAction.chap05;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class Transaction {

    private final Trader trader;
    private final int year;
    private final int value;
}
