package ru.reshetnikova;

import java.util.*;
import java.io.*;

public class Validator {

    // Проверяет, пустой ли список
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    // Проверка, содержит ли список элемент E
    public static <T> boolean containsElement(List<T> list, T element) {
        if (isListEmpty(list)) {
            return false;
        }
        return list.contains(element);
    }

    // Проверка пустого ввода
    public static boolean isValidInput(String input) {
        return input != null && !input.trim().isEmpty();
    }

    // Проверка null
    public static void validateNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    // Проверка списка
    public static <T> void validateList(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null.");
        }
    }

    // Проверка файла
    public static void validateFile(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Файл не может быть null");
        }
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не существует: " + file.getName());
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("Это не файл: " + file.getName());
        }
    }
}