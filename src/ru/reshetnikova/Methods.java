package ru.reshetnikova;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedHashMap;

public class Methods {

    // ЗАДАЧА 3.1: Удаление из списка
    public static <T> List<T> removeAllItems(List<T> list, T element) {
        Validator.validateNotNull(list, "Список не может быть null");
        Validator.validateNotNull(element, "Элемент для удаления не может быть null");

        if (list.isEmpty()) {
            return new ArrayList<>();
        }

        List<T> result = new ArrayList<>();
        for (T item : list) {
            if (!Objects.equals(item, element)) {
                result.add(item);
            }
        }
        return result;
    }

    // ЗАДАЧА 4.1: Мап. ФИО учеников
    public static Map<String, String> generateStudentLoginsFromFile(String filename) {
        Validator.validateNotNull(filename, "Имя файла не может быть null");

        List<String> students = readStudentsFromFile(filename);
        return generateStudentLogins(students);
    }

    // Метод для чтения учеников из файла
    public static List<String> readStudentsFromFile(String filename) {
        Validator.validateNotNull(filename, "Имя файла не может быть null");

        List<String> students = new ArrayList<>();
        int expectedCount = 0;
        int actualCount = 0;
        boolean isFirstLine = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // Пропускаем пустые строки
                }

                // Первая строка - количество учеников
                if (isFirstLine) {
                    try {
                        expectedCount = Integer.parseInt(line);
                        if (expectedCount <= 0) {
                            throw new IllegalArgumentException(
                                    "Количество учеников должно быть больше 0, а не: " + expectedCount
                            );
                        }
                        if (expectedCount > 100) {
                            throw new IllegalArgumentException(
                                    "Количество учеников не должно превышать 100, а не: " + expectedCount
                            );
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(
                                "Первая строка файла должна содержать количество учеников (число), а не: " + line
                        );
                    }
                    isFirstLine = false;
                    continue; // Пропускаем первую строку, она уже обработана
                }

                // Проверка формата Фамилия Имя
                String[] parts = line.split("\\s+");
                if (parts.length < 2) {
                    throw new IllegalArgumentException(
                            "Неверный формат строки ученика: '" + line +
                                    "'. Ожидается: Фамилия Имя"
                    );
                }

                String surname = parts[0];
                String name = parts[1];

                // Проверка длины фамилии и имени
                if (surname.length() > 20) {
                    throw new IllegalArgumentException(
                            "Фамилия '" + surname + "' слишком длинная. Максимум 20 символов."
                    );
                }

                if (name.length() > 15) {
                    throw new IllegalArgumentException(
                            "Имя '" + name + "' слишком длинное. Максимум 15 символов."
                    );
                }

                students.add(line);
                actualCount++;
            }

            // Проверка соответствия количества
            if (expectedCount != actualCount) {
                throw new IllegalArgumentException(
                        "Несоответствие количества учеников. Ожидалось: " +
                                expectedCount + ", фактически: " + actualCount
                );
            }

        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Файл не найден: " + filename);
        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения файла: " + e.getMessage());
        }

        if (students.isEmpty()) {
            throw new IllegalArgumentException("Файл пуст: " + filename);
        }

        return students;
    }

    // Основной метод генерации логинов
    public static Map<String, String> generateStudentLogins(List<String> students) {
        Validator.validateNotNull(students, "Список учеников не может быть null");

        Map<String, String> logins = new LinkedHashMap<>();
        Map<String, Integer> surnameCount = new HashMap<>();

        for (String student : students) {
            if (student == null || student.trim().isEmpty()) {
                continue;
            }

            String[] parts = student.split("\\s+", 2);
            if (parts.length < 2) {
                continue;
            }

            String surname = parts[0].trim();
            String fullName = student.trim();

            int count = surnameCount.getOrDefault(surname, 0) + 1;
            surnameCount.put(surname, count);

            String login = (count == 1) ? surname : surname + count;
            logins.put(fullName, login);
        }

        return logins;
    }

    // Метод для вывода только логинов
    public static String toStringStudentLogins(Map<String, String> logins) {
        if (logins == null || logins.isEmpty()) {
            return "Нет данных об учениках";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Данные из файла:\n");

        // ФИО из файла
        for (String fullName : logins.keySet()) {
            sb.append(fullName).append("\n");
        }
        sb.append("\nСформированные логины:\n");

        // Выводим только значения (логины) в порядке их создания
        for (String login : logins.values()) {
            sb.append(login).append("\n");
        }

        return sb.toString().trim();
    }

    // ЗАДАЧА 5.1: Звонкие согласные (Set)
    public static Set<Character> findVoicedConsonantsFromFile(String filename) {
        Validator.validateNotNull(filename, "Имя файла не может быть null");

        String text = readTextFromFile(filename);
        return findVoicedConsonants(text);
    }

    public static Set<Character> findVoicedConsonants(String text) {
        Validator.validateNotNull(text, "Текст не может быть null");

        Set<Character> voicedConsonants = Set.of('б', 'в', 'г', 'д', 'ж', 'з', 'й', 'л', 'м', 'н', 'р');
        Set<Character> result = new TreeSet<>();

        String lowerText = text.toLowerCase();
        for (char c : lowerText.toCharArray()) {
            if (isRussianLetter(c) && voicedConsonants.contains(c)) {
                result.add(c);
            }
        }
        return result;
    }

    // ЗАДАЧА 6.5: Очередь. Обратный порядок
    public static <T> Queue<T> reverseQueue(Queue<T> originalQueue) {
        Validator.validateNotNull(originalQueue, "Очередь не может быть null");

        if (originalQueue.isEmpty()) {
            return new LinkedList<>();
        }

        // Создаем стек для временного хранения элементов в обратном порядке
        Stack<T> stack = new Stack<>();

        // Перекладываем все элементы из очереди в стек
        while (!originalQueue.isEmpty()) {
            stack.push(originalQueue.poll());
        }

        // Создаем новую очередь и перекладываем элементы из стека обратно
        Queue<T> reversedQueue = new LinkedList<>();
        while (!stack.isEmpty()) {
            reversedQueue.add(stack.pop());
        }

        return reversedQueue;
    }

    // Метод для красивого вывода очереди
    public static <T> String toStringQueue(Queue<T> queue) {
        if (queue == null || queue.isEmpty()) {
            return "Очередь пуста";
        }
        return "Очередь: " + queue;
    }

    // ЗАДАЧА 7.1: Стрим. Точка, линия и ломанная
    public static Polyline processPoints(List<Point> points) {
        if (points == null || points.isEmpty()) {
            return new Polyline();
        }

        List<Point> result = points.stream()
                .map(p -> new Point(p.getX(), Math.abs(p.getY()))) // сначала преобразуем Y
                .distinct()                      // потом убираем дубликаты
                .sorted(Comparator.comparingDouble(Point::getX)) // сортировка по X
                .collect(Collectors.toList());

        return new Polyline(result);
    }

    // Создание линии из двух точек
    public static Line createLine(Point start, Point end) {
        return new Line(start, end);
    }

    // ЗАДАЧА 7.2: Стрим. Имя и номер
    public static Map<Integer, List<String>> processNamesFromFile(String filename) {
        Validator.validateNotNull(filename, "Имя файла не может быть null");

        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));

            Map<Integer, List<String>> result = lines.stream()
                    .filter(line -> line.contains(":")) // отфильтровываем строки без разделителя
                    .map(line -> {
                        String[] parts = line.split(":");
                        String name = parts[0].trim();
                        String numberStr = parts.length > 1 ? parts[1].trim() : "";
                        return new AbstractMap.SimpleEntry<>(name, numberStr);
                    })
                    .filter(entry -> !entry.getValue().isEmpty()) // убираем людей без номеров
                    .filter(entry -> entry.getValue().matches("\\d+")) // проверяем, что номер состоит из цифр
                    .map(entry -> {
                        String name = entry.getKey();
                        // Приводим имя к нижнему регистру, но с первой буквой в верхнем
                        String formattedName = name.substring(0, 1).toUpperCase() +
                                name.substring(1).toLowerCase();
                        int number = Integer.parseInt(entry.getValue());
                        return new AbstractMap.SimpleEntry<>(formattedName, number);
                    })
                    .collect(Collectors.groupingBy(
                            Map.Entry::getValue,
                            Collectors.mapping(Map.Entry::getKey, Collectors.toList())
                    ));

            return result;

        } catch (IOException e) {
            throw new IllegalArgumentException("Ошибка чтения файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ошибка формата номера в файле");
        }
    }

    // Метод для вывода результата
    public static String toStringNamesByNumber(String filename, Map<Integer, List<String>> map) {
        if (map == null || map.isEmpty()) {
            return "Нет данных для отображения";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("Исходные данные из файла:\n");
        try {
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (String line : lines) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            sb.append("Ошибка чтения файла: ").append(e.getMessage());
        }

        // Затем выводим результат группировки
        sb.append("\nРезультат группировки имен по номерам:\n");
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    sb.append("[").append(entry.getKey()).append(": [");
                    sb.append(String.join(", ", entry.getValue()));
                    sb.append("]]\n");
                });

        return sb.toString().trim();
    }

    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ

    // Метод для чтения файла
    public static String readTextFromFile(String filename) {
        Validator.validateNotNull(filename, "Имя файла не может быть null");

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

    // Проверка, является ли символ русской буквой
    private static boolean isRussianLetter(char c) {
        return (c >= 'а' && c <= 'я') || c == 'ё';
    }

    // МЕТОДЫ toString ДЛЯ ВЫВОДА

    public static String toStringVoicedConsonants(Set<Character> consonants, String filename, String text) {
        if (consonants.isEmpty()) {
            return "Файл: " + filename + "\nТекст: \"" + text + "\"\nЗвонкие согласные буквы: не найдено";
        }

        return "Файл: " + filename + "\nТекст: \"" + text + "\"\nЗвонкие согласные буквы (в алфавитном порядке): " +
                consonants.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(" "));
    }
}