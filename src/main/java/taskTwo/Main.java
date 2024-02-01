package taskTwo;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        int[] array = {1, 0, 2, 3, 0, 1, 2, 3, 1};
        arrayToFile(array, "arrayToFile");

    }

    private static void arrayToFile(int[] arr, String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        for (int b = 0; b < 3; b++) {
            byte wr = 0;
            for (int v = 0; v < 3; v++) {
                wr += (byte) (arr[3 * b + v] << (v * 2));
            }
            fileOutputStream.write(wr);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
