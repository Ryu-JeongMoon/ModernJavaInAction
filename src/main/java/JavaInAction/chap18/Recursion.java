package JavaInAction.chap18;

import java.util.stream.LongStream;

public class Recursion {

  // 큰 입력 값 주니까 Iterative 는 이상한 값이 튀나온당
  public static void main(String[] args) {
    System.out.println(factorialIterative(15));
    System.out.println(factorialRecursive(15));
    System.out.println(factorialStreams(15));
    System.out.println(factorialTailRecursive(15));
  }

  // 매 반복마다 r, i 가 변화됨
  static int factorialIterative(int n) {
    int r = 1;
    for (int i = 1; i <= n; i++) {
      r *= i;
    }
    return r;
  }

  // 일반적으로 반복문보다 재귀가 비싸다, 호출 시마다 생성되는 정보를 저장할 스택 프레임이 만들어지기 때문이다
  static long factorialRecursive(long n) {
    return n == 1 ? 1 : n * factorialRecursive(n - 1);
  }

  static long factorialStreams(long n) {
    return LongStream.rangeClosed(1, n)
        .reduce(1, (a, b) -> a * b);
  }

  // 꼬리호출 최적화로 재귀의 문제를 해결한다는디, 꼬리호출 최적화는 또 뭐시여
  static long factorialTailRecursive(long n) {
    return factorialHelper(1, n);
  }

  // 안타깝게도 자바는 지원 안 한다네.. 그래도 최적화 여지가 있는 꼬리호출을 쓰란다
  static long factorialHelper(long acc, long n) {
    return n == 1 ? acc : factorialHelper(acc * n, n - 1);
  }
}
