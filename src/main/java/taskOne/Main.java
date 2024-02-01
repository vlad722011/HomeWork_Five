package taskOne;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.nio.file.Path;

public class Main {
    static String DESTINATION_FOLDER;
    static String SOURCE_FOLDER;
    static Path Source_Folder_Path;
    static Path Destination_Folder_Path;


    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Резервное копирование файлов из директории источника в директорию ./backup");
        System.out.println("Введите путь к директории источнику: ");
        SOURCE_FOLDER = scanner.nextLine();

        Source_Folder_Path = Path.of(SOURCE_FOLDER);
        Destination_Folder_Path = Path.of("./backup");

        if (!Files.isDirectory(Destination_Folder_Path)) {
            try {
                Files.createDirectory(Destination_Folder_Path);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        DESTINATION_FOLDER = String.valueOf(Destination_Folder_Path);

        System.out.println("Для контроля корректности работы программы, " +
                "выведем в консоль содержимое директории источника: ");
        ShowFilesInDirectory files = new ShowFilesInDirectory(Source_Folder_Path);
        files.printFilesInFolder(Source_Folder_Path);

        System.out.println("Начинаю копирование: ");
        BackupHelper helper = new BackupHelper(Source_Folder_Path, Destination_Folder_Path);
        helper.copyFiles(SOURCE_FOLDER, DESTINATION_FOLDER);

        System.out.printf("Резервное копирование из %s в %s завершено.", SOURCE_FOLDER, DESTINATION_FOLDER);
        System.out.println();

        System.out.println("Проверяем наличие копируемых файлов в папке назначения: ");
        files.printFilesInFolder(Destination_Folder_Path);
    }
}

