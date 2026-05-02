//package com.rafal.skilldiagnosticsystem;
//
//import org.springframework.stereotype.Component;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//@Component
//public class FileManager {
//    private final String fileName;
//
//    public FileManager(String fileName) {
//        this.fileName = fileName;
//    }
//
//    public void writeToFile(List<Double> results) {
//        try (FileWriter writer = new FileWriter(fileName, true)) {
//            if (!results.isEmpty()) {
//                writer.write(String.format("%.0f", results.getLast()) + "\n");
//            }
//        } catch (IOException e) {
//            System.out.println("Nie udało się utworzyć pliku.");
//            e.printStackTrace();
//        }
//    }
//    public List<Double> readFromFile() {
//        File file = new File(fileName);
//        List<Double> results = new ArrayList<>();
//
//        try (Scanner reader = new Scanner(file)) {
//            while (reader.hasNextDouble()) {
//                results.add(reader.nextDouble());
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Nie udało się odczytać pliku.");
//            e.printStackTrace();
//        }
//
//        return results;
//    }
//}
