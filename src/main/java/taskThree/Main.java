package taskThree;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Main {

    /*
    Написать функцию, добавляющую префикс к каждому из набора файлов,
    названия которых переданы ей в качестве параметров через пробел.
     */

    public static void main(String[] args) throws IOException {

        System.out.println("_________________________________________________________________________");
        System.out.println("Прочитаем директорию, созданную в первой задаче к домашнему заданию.");
        System.out.println("Из наваний файлов, лежащих в директории, соберем строку, и выведем ее в консоль.");
        System.out.println("Строка состоит из названий файлов, разделенных друг от друга пробелом: ");
        String files = listOfFileNames();
        System.out.println(files);
        System.out.println("_________________________________________________________________________");


        System.out.println("Изменим названия файлов: ");
        addPrefixToFileName(files, "new_");
        System.out.println("Чтобы убедиться, что названия файлов изменились, выведем строку с " +
                "новыми названиями файлов в консоль: ");
        String newFiles = listOfFileNames();
        System.out.println(newFiles);
        System.out.println("_________________________________________________________________________");
    }

    private static String listOfFileNames() {
        List<File> fileList = getFileList();
        String fileNames = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (File f : fileList) {
            String fileName = f.getName();
            stringBuilder.append(fileName).append(" ");
        }
        fileNames = stringBuilder.toString();
        return fileNames;
    }

    private static List<File> getFileList() {
        File file;
        Path sourseFolderPath = Path.of("./backup");
        List<File> fileList = new ArrayList<>();
        file = new File(String.valueOf(sourseFolderPath));
        Collections.addAll(fileList, Objects.requireNonNull(file.listFiles()));
        return fileList;
    }


    public static void addPrefixToFileName(String listOfFileNames, String prefix) throws IOException {

        List<File> fileList = getFileList();
        StringBuilder sb = new StringBuilder();
        for (File f : fileList) {
            if (f.isFile() && f.canRead()) {
                if (f.exists() && f.isFile()) {
                    String path = f.getParent();
                    String newName = prefix + f.getName();
                    Path soursePath = Path.of(f.getCanonicalPath());
                    Path destinationPath = Path.of(path + "/" + newName);

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
                    f.delete();
                }
            }
        }
    }
}

