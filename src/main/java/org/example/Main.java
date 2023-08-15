package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/*
    1. Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
*/
public class Main {
    public static void main(String[] args) {

        String source = "./testDirectory1";
        String destination ="./backup";

        // Создание двух тестовых директорий и двух файлов для работы функции
        try {
            Files.createDirectory(Paths.get(source));
            Files.createDirectory(Paths.get(destination));
            Files.createFile(Paths.get("./testDirectory1/file1.txt"));
            Files.createFile(Paths.get("./testDirectory1/file2.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Создание бэкапа
        try {
            makeBackup(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeBackup(String source, String destination) throws IOException {
        File folder = new File(source);
        for (File file : folder.listFiles()) {
            String tempDir = destination + "/" + file.getName();
            File tempFile = new File(tempDir);
            Files.copy(file.toPath(), tempFile.toPath());
        }
    }
}