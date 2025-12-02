package ru.reshetnikova;

import java.util.Random;

public class Funs {
    public static void meowsCare(Meow meow) { // Позволяет методу принимать от 0 до N аргументов указанного типа
        Random random = new Random();
        // Генерируем случайное количество мяуканий от 1 до 10
        int meowCount = random.nextInt(10) + 1;

        System.out.println("Кот будет мяукать " + meowCount + " раз:");

        for (int i = 0; i < meowCount; i++) {
            meow.meow();
        }
    }
}