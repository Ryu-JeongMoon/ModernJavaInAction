package JavaInAction.chap13;

public interface B extends A {

  default void hello() {
    System.out.println("hello from B");
  }
}
