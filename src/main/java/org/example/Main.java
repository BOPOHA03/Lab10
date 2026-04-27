package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String inputFilePath = scanner.nextLine();

        String outputDir = "output";
        String outputFileName = "result.txt";
        String outputFilePath = outputDir + File.separator + outputFileName;

        // Создаём новую директорию средствами класса File
        File dir = new File(outputDir);
        if (!dir.exists()) {
            if (dir.mkdir()) {
                System.out.println("Создана директория: " + outputDir);
            } else {
                System.err.println("Не удалось создать директорию: " + outputDir);
                return;
            }
        }

        int wordCount = 0;
        int intCount = 0;
        int doubleCount = 0;
        int charCount = 0;

        try (Scanner fileScanner = new Scanner(new File(inputFilePath))) {
            while (fileScanner.hasNext()) {
                String token = fileScanner.next();

                // Проверяем, является ли токен целым числом
                if (token.matches("-?\\d+")) {
                    intCount++;
                }
                // Проверяем, является ли токен числом с плавающей запятой
                else if (token.matches("-?\\d+\\.\\d+")) {
                    doubleCount++;
                }
                // Проверяем, является ли токен одним символом (не цифрой)
                else if (token.length() == 1 && !Character.isDigit(token.charAt(0))) {
                    charCount++;
                }
                // Иначе считаем словом
                else {
                    wordCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + inputFilePath);
            return;
        }

        // Записываем результат в новый файл
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.println("Анализ файла: " + inputFilePath);
            writer.println("--------------------------------");
            writer.println("Целые числа: " + intCount);
            writer.println("Числа с плавающей запятой: " + doubleCount);
            writer.println("Символы (одиночные, не цифры): " + charCount);
            writer.println("Слова: " + wordCount);
            writer.println("--------------------------------");
            writer.println("Всего токенов: " + (intCount + doubleCount + charCount + wordCount));
            System.out.println("Результат сохранён в файл: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }

        System.out.println("\nРазработчик: Парамонов С.В.");
        System.out.println("Дата получения: 11.04.2026");
        System.out.println("Дата сдачи: 27.04.2026");
    }
}