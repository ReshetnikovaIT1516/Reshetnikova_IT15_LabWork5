package ru.reshetnikova;

import java.io.*;
import java.util.*;

public class VoicedConsonantsFinder {
    private String text;
    private Set<Character> foundConsonants;
    private String filename;

    // Конструктор для чтения из файла
    public VoicedConsonantsFinder(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя файла не может быть пустым");
        }
        this.filename = filename;
        this.text = readTextFromFile();
        this.foundConsonants = findVoicedConsonants();
    }

    // Конструктор для работы с готовым текстом
    public VoicedConsonantsFinder(String text, boolean fromString) {
        if (text == null || text.trim().isEmpty()) {
            throw new IllegalArgumentException("Текст не может быть пустым");
        }
        this.text = text;
        this.filename = null;
        this.foundConsonants = findVoicedConsonants();
    }

    // Чтение текста из файла
    private String readTextFromFile() {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(" ");
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Файл не найден: " + filename);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения файла: " + e.getMessage());
        }

        if (content.length() == 0) {
            throw new IllegalArgumentException("Файл пуст: " + filename);
        }

        return content.toString().trim();
    }

    // Метод для поиска звонких согласных
    private Set<Character> findVoicedConsonants() {
        // Множество звонких согласных русского языка
        Set<Character> voicedConsonants = new HashSet<>(Arrays.asList(
                'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'л', 'м', 'н', 'р'
        ));

        Set<Character> result = new TreeSet<>(); // TreeSet для автоматической сортировки

        // Приводим текст к нижнему регистру и обрабатываем каждый символ
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            // Если символ - русская буква и является звонкой согласной
            if (isRussianLetter(c) && voicedConsonants.contains(c)) {
                result.add(c);
            }
        }

        return result;
    }

    // Проверка, является ли символ русской буквой
    private boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    // Геттеры
    public String getText() {
        return text;
    }

    public Set<Character> getFoundConsonants() {
        return new TreeSet<>(foundConsonants);
    }

    public String getFilename() {
        return filename;
    }

    @Override
    public String toString() {
        if (foundConsonants.isEmpty()) {
            if (filename != null) {
                return "Файл: " + filename + "\nТекст: \"" + text + "\"\nЗвонкие согласные буквы: не найдено";
            }
            return "Текст: \"" + text + "\"\nЗвонкие согласные буквы: не найдено";
        }

        StringBuilder sb = new StringBuilder();
        if (filename != null) {
            sb.append("Файл: ").append(filename).append("\n");
        }
        sb.append("Текст: \"").append(text).append("\"\n");
        sb.append("Звонкие согласные буквы (в алфавитном порядке): ");

        for (char consonant : foundConsonants) {
            sb.append(consonant).append(" ");
        }

        return sb.toString().trim();
    }
}