package ru.reshetnikova;

import java.util.*;
import java.io.*;


public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1) Задача 1.1: Шаблоны. Дробь.");
            System.out.println("2) Задача 2.1: Структурные шаблоны. Количество мяуканий. ");
            System.out.println("3) Задача 3.1: Список. Удаление из списка.");
            System.out.println("4) Задача 4.1: Мап. ФИО учеников.");
            System.out.println("5) Задача 5.1: Сет. Звонкие согласные. ");
            System.out.println("6) Задача 6.5: Очередь. Обратный порядок. ");
            System.out.println("7) Задача 7.1: Стрим. Точка, линия и ломанная");
            System.out.println("8) Задача 7.2: Стрим. Имя и номер");
            System.out.println("0) Выход");
            System.out.print("Выберите задачу: ");

            String choice = scanner.next();

            switch (choice) {
                case "1":
                    scanner.nextLine();
                    System.out.println("Задача 1.1: Шаблоны. Дробь.");

                    try {
                        List<Fraction> fractions = new ArrayList<>();
                        List<FractionCached> cachedFractions = new ArrayList<>();

                        // Ввод количества дробей
                        int count = 0;
                        while (true) {
                            System.out.print("Введите количество дробей: ");
                            if (scanner.hasNextInt()) {
                                count = scanner.nextInt();
                                scanner.nextLine();
                                if (count > 0) {
                                    break;
                                } else {
                                    System.out.println("Количество дробей должно быть больше 0!");
                                }
                            } else {
                                System.out.println("Ошибка: Введите целое число!");
                                scanner.nextLine();
                            }
                        }

                        // Ввод дробей
                        System.out.println("Введите " + count + " дробей (числитель и знаменатель):");
                        for (int i = 0; i < count; i++) {
                            System.out.println("Дробь " + (i + 1) + ": ");

                            int numerator = 0;
                            int denominator = 0;

                            // Ввод числителя
                            while (true) {
                                System.out.print("Введите числитель: ");
                                if (scanner.hasNextInt()) {
                                    numerator = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Ошибка: Введите целое число для числителя!");
                                    scanner.nextLine();
                                }
                            }

                            // Ввод знаменателя
                            while (true) {
                                System.out.print("Введите знаменатель: ");
                                if (scanner.hasNextInt()) {
                                    denominator = scanner.nextInt();
                                    scanner.nextLine();
                                    if (denominator != 0) {
                                        break;
                                    } else {
                                        System.out.println("Ошибка: Знаменатель не может быть равен 0!");
                                    }
                                } else {
                                    System.out.println("Ошибка: Введите целое число для знаменателя!");
                                    scanner.nextLine();
                                }
                            }

                            // Создание обычной дроби и дроби с кэшем
                            Fraction fraction = new Fraction(numerator, denominator);
                            FractionCached cachedFraction = new FractionCached(fraction);

                            fractions.add(fraction);
                            cachedFractions.add(cachedFraction);

                            System.out.println("Дробь добавлена: " + fraction + "\n");
                        }

                        System.out.println("Обычные дроби");
                        for (int i = 0; i < fractions.size(); i++) {
                            FractionInterface frac = fractions.get(i);
                            System.out.println("Дробь " + (i + 1) + ": " + frac + " = " + frac.getDecimalValue());
                        }

                        System.out.println("Кэшированные дроби");
                        for (int i = 0; i < cachedFractions.size(); i++) {
                            FractionInterface frac = cachedFractions.get(i);
                            System.out.println("Дробь " + (i + 1) + ": " + frac + " = " + frac.getDecimalValue());
                        }


                        // Сравнение дробей
                        System.out.println("\nСравнение дробей (каждая с каждой)");
                        for (int i = 0; i < fractions.size(); i++) {
                            for (int j = 0; j < fractions.size(); j++) {
                                if (i != j) { // не сравниваем дробь саму с собой
                                    Fraction frac1 = fractions.get(i);
                                    Fraction frac2 = fractions.get(j);
                                    boolean areEqual = frac1.equals(frac2);
                                    System.out.println("Дробь " + (i + 1) + " " + frac1 +
                                            " равняется Дроби " + (j + 1) + " " + frac2 +
                                            ": " + areEqual);
                                }
                            }
                        }

                        System.out.println("\nДемонстрация hashCode()");
                        for (int i = 0; i < fractions.size(); i++) {
                            Fraction frac = fractions.get(i);
                            System.out.println("Дробь " + (i + 1) + ": " + frac +
                                    " -> hashCode = " + frac.hashCode());
                        }
                        // Изменение дроби с кэшем
                        System.out.println("\nИзменение дроби с кэшем");
                        if (!cachedFractions.isEmpty()) {
                            FractionCached fracToChange = cachedFractions.get(0);
                            System.out.println("Текущая дробь: " + fracToChange + " = " + fracToChange.getDecimalValue());

                            int newNumerator = 0;
                            int newDenominator = 0;

                            // Ввод нового числителя
                            while (true) {
                                System.out.print("Введите новый числитель: ");
                                if (scanner.hasNextInt()) {
                                    newNumerator = scanner.nextInt();
                                    scanner.nextLine();
                                    break;
                                } else {
                                    System.out.println("Ошибка: Введите целое число!");
                                    scanner.nextLine();
                                }
                            }

                            // Ввод нового знаменателя
                            while (true) {
                                System.out.print("Введите новый знаменатель: ");
                                if (scanner.hasNextInt()) {
                                    newDenominator = scanner.nextInt();
                                    scanner.nextLine();
                                    if (newDenominator != 0) {
                                        break;
                                    } else {
                                        System.out.println("Ошибка: Знаменатель не может быть равен 0!");
                                    }
                                } else {
                                    System.out.println("Ошибка: Введите целое число!");
                                    scanner.nextLine();
                                }
                            }

                            fracToChange.setFraction(newNumerator, newDenominator);
                            System.out.println("После изменения: " + fracToChange + " = " + fracToChange.getDecimalValue());
                            System.out.println("Повторное вычисление: " + fracToChange.getDecimalValue());
                        }

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;


                case "2":
                    scanner.nextLine();
                    System.out.println("Задача 2.1: Структурные шаблоны. Количество мяуканий.");

                    try {
                        System.out.print("Введите имя кота: ");
                        String catName = scanner.nextLine();

                        if (!Validator.isValidInput(catName)) {
                            System.out.println("Ошибка: Имя кота не может быть пустым!");
                            break;
                        }

                        // Создаем кота
                        Cat originalCat = new Cat(catName);
                        System.out.println("Создан: " + originalCat);

                        // Для подсчета мяуканий
                        CountMeowCat countingCat = new CountMeowCat(originalCat);

                        Funs.meowsCare(countingCat);

                        // Выводим количество мяуканий
                        System.out.println("Результат: " + countingCat.toString());

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;


                case "3":
                    scanner.nextLine();
                    System.out.println("Задача 3.1: Список. Удаление из списка.");
                    List<String> list = new ArrayList<>();

                    try {
                        int count = 0;
                        while (true) {
                            System.out.print("Введите количество элементов в списке: ");
                            if (scanner.hasNextInt()) {
                                count = scanner.nextInt();
                                scanner.nextLine();
                                if (count > 0) {
                                    break;
                                } else {
                                    System.out.println("Количество элементов должно быть больше 0!");
                                }
                            } else {
                                System.out.println("Ошибка: Введите целое число!");
                                scanner.nextLine();
                            }
                        }

                        System.out.println("Введите " + count + " элементов:");
                        for (int i = 0; i < count; i++) {
                            System.out.print("Элемент " + (i + 1) + ": ");
                            String element = scanner.nextLine();
                            list.add(element);
                        }

                        System.out.println("Полученный список: " + list);

                        if (Validator.isListEmpty(list)) {
                            System.out.println("Список пуст.");
                            break;
                        }

                        String elementToRemove = "";
                        while (true) {
                            System.out.print("Введите элемент E для удаления: ");
                            elementToRemove = scanner.nextLine();

                            if (Validator.isValidInput(elementToRemove)) {
                                if (Validator.containsElement(list, elementToRemove)) {
                                    break;
                                } else {
                                    System.out.println("Элемент '" + elementToRemove + "' не найден в списке. Попробуйте еще раз.");
                                }
                            } else {
                                System.out.println("Ошибка: Элемент для удаления не может быть пустым!");
                            }
                        }

                        List<String> resultList = Methods.removeAllItems(list, elementToRemove);
                        System.out.println("Исходный список: " + list);
                        System.out.println("Элемент для удаления: " + elementToRemove);
                        System.out.println("Список после удаления: " + resultList);

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "4":
                    scanner.nextLine();
                    System.out.println("Задача 4.1: Мап. ФИО учеников.");

                    try {
                        String filename = "students.txt";
                        System.out.println("Используется файл: " + filename);

                        // Читаем учеников из файла и генерируем логины
                        Map<String, String> studentLogins = Methods.generateStudentLoginsFromFile(filename);

                        // Выводим результат
                        System.out.println(Methods.toStringStudentLogins(studentLogins));

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "5":
                    scanner.nextLine();
                    System.out.println("Задача 5.1: Сет. Звонкие согласные. ");
                    String filename = "set.txt";
                    System.out.println("Используется файл: " + filename);

                    try {
                        // Используем Methods для работы с файлом и поиска согласных
                        String text = Methods.readTextFromFile(filename);
                        Set<Character> consonants = Methods.findVoicedConsonants(text);

                        // Выводим результат
                        System.out.println(Methods.toStringVoicedConsonants(consonants, filename, text));

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;


                case "6":
                    scanner.nextLine();
                    System.out.println("Задача 6.5: Очередь. Обратный порядок.");

                    try {
                        Queue<String> queueL1 = new LinkedList<>();
                        int count = 0;
                        while (true) {
                            System.out.print("Введите количество элементов в очереди L1: ");
                            if (scanner.hasNextInt()) {
                                count = scanner.nextInt();
                                scanner.nextLine();
                                if (count > 0) {
                                    break;
                                } else {
                                    System.out.println("Количество элементов должно быть больше 0!");
                                }
                            } else {
                                System.out.println("Ошибка: Введите целое число!");
                                scanner.nextLine();
                            }
                        }

                        System.out.println("Введите " + count + " элементов для очереди L1:");
                        for (int i = 0; i < count; i++) {
                            System.out.print("Элемент " + (i + 1) + ": ");
                            String element = scanner.nextLine();
                            queueL1.add(element);
                        }

                        System.out.println("Исходная очередь L1: " + Methods.toStringQueue(queueL1));

                        Queue<String> queueL2 = Methods.reverseQueue(new LinkedList<>(queueL1));
                        System.out.println("Очередь L2 (обратный порядок): " + Methods.toStringQueue(queueL2));
                        System.out.println("Очередь L1 (осталась без изменений): " + Methods.toStringQueue(queueL1));

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "7":
                    scanner.nextLine();
                    System.out.println("Задача 7.1: Стрим. Точка, линия и ломанная");

                    try {
                        List<Point> points = new ArrayList<>();

                        // Цикл для ввода количества точек с проверкой
                        int count = 0;
                        while (true) {
                            System.out.print("Введите количество точек: ");
                            if (scanner.hasNextInt()) {
                                count = scanner.nextInt();
                                scanner.nextLine(); // очистка буфера
                                if (count > 0) {
                                    break;
                                } else {
                                    System.out.println("Ошибка: Количество точек должно быть больше 0!");
                                }
                            } else {
                                System.out.println("Ошибка: Введите целое число!");
                                scanner.nextLine(); // очистка неверного ввода
                            }
                        }

                        System.out.println("Введите координаты точек:");
                        for (int i = 0; i < count; i++) {
                            System.out.println("Точка " + (i + 1) + ": ");

                            double x = 0;
                            double y = 0;

                            while (true) {
                                System.out.print("Введите координату X: ");
                                if (scanner.hasNextDouble()) {
                                    x = scanner.nextDouble();
                                    scanner.nextLine(); // очистка буфера
                                    break;
                                } else {
                                    System.out.println("Ошибка: Введите число для координаты X!");
                                    scanner.nextLine(); // очистка неверного ввода
                                }
                            }

                            while (true) {
                                System.out.print("Введите координату Y: ");
                                if (scanner.hasNextDouble()) {
                                    y = scanner.nextDouble();
                                    scanner.nextLine(); // очистка буфера
                                    break;
                                } else {
                                    System.out.println("Ошибка: Введите число для координаты Y!");
                                    scanner.nextLine(); // очистка неверного ввода
                                }
                            }

                            points.add(new Point(x, y));
                            System.out.println("Точка " + (i + 1) + " добавлена: {" + x + ";" + y + "}\n");
                        }

                        System.out.println("Исходные точки: " + points);

                        // Обрабатываем точки через стрим
                        Polyline polyline = Methods.processPoints(points);
                        System.out.println("Все отрицательные значения Y стали положительными");
                        System.out.println("Результат - ломаная: " + polyline);

                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "8":
                    scanner.nextLine();
                    System.out.println("Задача 7.2: Стрим. Имя и номер");

                    try {
                        String filename7 = "names.txt";
                        System.out.println("Используется файл: " + filename7);
                        // Обрабатываем файл через стрим
                        // processNamesFromFile - это метод, который
                        // обрабатывает файл с именами и номерами, группируя имена по номерам.

                        Map<Integer, List<String>> result = Methods.processNamesFromFile(filename7);

                        // Выводим результат с исходными данными
                        System.out.println(Methods.toStringNamesByNumber(filename7, result));

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case "0":
                    System.out.println("Выход из программы");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор! Попробуйте снова.");
            }

            System.out.println("\nНажмите 'Enter' чтобы снова выбрать номер задачи");
            scanner.nextLine();

        }

    }

}
