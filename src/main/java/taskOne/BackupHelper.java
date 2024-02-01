package taskOne;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;




@Setter
@Getter
@Data
public class BackupHelper {
    private String DESTINATION_FOLDER;
    private String SOURCE_FOLDER;
    private final Path Source_Folder_Path;
    private final Path Destination_Folder_Path;

    public BackupHelper(Path Source_Folder_Path, Path destinationFolderPath) {
        this.Source_Folder_Path = Source_Folder_Path;
        this.Destination_Folder_Path = destinationFolderPath;
    }

    public void copyFiles(String SOURCE_FOLDER, String BACKUP_DESTINATION_FOLDER) {
        File file;

        List<File> fileList = new ArrayList<>();
        file = new File(String.valueOf(Source_Folder_Path));
        Collections.addAll(fileList, Objects.requireNonNull(file.listFiles()));

        for (File value : fileList) {
            System.out.println("Копирую файл -> " + value.getName());
            Path soursePath = Path.of(SOURCE_FOLDER + "/" + value.getName());
            Path destinationPath = Path.of(BACKUP_DESTINATION_FOLDER + "/" + value.getName());
            try {
                DataInputStream inputStream = new DataInputStream(new FileInputStream(String.valueOf(soursePath)));
                DataOutputStream outStream = new DataOutputStream(new FileOutputStream(String.valueOf(destinationPath)));
                byte[] bytes = inputStream.readAllBytes();
                for (byte aByte : bytes) {
                    outStream.write(aByte);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}



