package JavaInAction.chap03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) throws IOException {
        Runnable r1 = () -> System.out.println("hello world1");
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("hello world3"));

        Test t = new Test();
        String oneLine = t.processFile((BufferedReader br) -> br.readLine());
        System.out.println(oneLine);

        String twoLine = t.processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(twoLine);

        Function<String, Integer> f = (String s) -> s.length();
        System.out.println(f.apply("hello"));

    }


    @FunctionalInterface
    public interface BufferedReaderProcessor {

        String process(BufferedReader br) throws IOException;
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    public static void process(Runnable r) {
        r.run();
    }
}
