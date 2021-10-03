package JavaInAction.chap01;

import java.io.File;
import java.io.FileFilter;

public class Summary {

    public static void main(String[] args) {
        // java 8 이전 코드
        File[] hiddenFiles = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // java 8, 문제 자체를 더 직접적으로 설명한다
        File[] hiddenFilesJava8 = new File(".").listFiles(File::isHidden);
    }
}
