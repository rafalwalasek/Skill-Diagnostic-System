package com.rafal.skilldiagnosticsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager {
    private final String fileName;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void writeToFile(List<Double> results) {
        //createFile();

        try (FileWriter writer = new FileWriter(fileName, true)) {
            for (Double result : results) {
                writer.write(String.format("%.0f", result) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Nie udało się utworzyć pliku.");
            e.printStackTrace();
        }
    }
    public List<Double> readWithFile(String fileName) {
        File file = new File(fileName);
        List<Double> results = new ArrayList<>();

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                results.add(reader.nextDouble());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie udało się odczytać pliku.");
            e.printStackTrace();
        }

        return results;
    }
    private void createFile() {
        try {
            File file = new File(fileName);

            if (file.createNewFile()) {
                System.out.println("Utworzono plik: " + fileName);
            } else {
                System.out.println("Plik " + fileName + ", już istnieje.");
            }
        } catch (IOException e) {
            System.out.println("Nie udało się utworzyć pliku.");
            e.printStackTrace();
        }
    }
}
