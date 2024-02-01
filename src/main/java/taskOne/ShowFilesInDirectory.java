package taskOne;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;

public class ShowFilesInDirectory {
    public ShowFilesInDirectory(Path path) {
    }

    public void printFilesInFolder(Path path) {
        File file = new File(String.valueOf(path));
        for (File f : Objects.requireNonNull(file.listFiles())) {
            System.out.println(f.getName());
        }
    }
}