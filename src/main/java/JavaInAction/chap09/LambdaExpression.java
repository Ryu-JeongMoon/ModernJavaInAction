package JavaInAction.chap09;

public class LambdaExpression {

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello");
            }
        };

        Runnable r2 = () -> System.out.println("World");

        r1.run();
        r2.run();
    }
}
