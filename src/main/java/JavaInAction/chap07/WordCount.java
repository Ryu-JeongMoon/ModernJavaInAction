package JavaInAction.chap07;

import lombok.RequiredArgsConstructor;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WordCount {

  private static final String SENTENCE =
      " Nel   mezzo del cammin  di nostra  vita mi  ritrovai in una  selva oscura che la  dritta via era   smarrita ";
  private static final Stream<Character> stream = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);


  public static void main(String[] args) {
//        System.out.println(countWordsIteratively(SENTENCE));

//        System.out.println(countWords(stream));
    System.out.println(countWords(stream.parallel()));
  }

  // 공백 여러개 있어도 정상 작동
  public static int countWordsIteratively(String s) {
    int counter = 0;
    boolean lastSpace = true;

    for (char c : s.toCharArray()) {
      if (Character.isWhitespace(c)) {
        lastSpace = true;
      } else {
        if (lastSpace) {
          counter++;
          lastSpace = false;
        }
      }
    }
    return counter;
  }

  private static int countWords(Stream<Character> stream) {
    return stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine).getCounter();
  }

  @RequiredArgsConstructor
  static class WordCounter {

    private final int counter;
    private final boolean lastSpace;

    public WordCounter accumulate(Character c) {
      if (Character.isWhitespace(c)) {
        return lastSpace ? this : new WordCounter(counter, true);
      } else {
        return lastSpace ? new WordCounter(counter + 1, false) : this;
      }
    }

    public WordCounter combine(WordCounter wordCounter) {
      return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
      return this.counter;
    }
  }
}
