package ru.reshetnikova;

public class Cat implements Meow {
    private final String name;

    public String getName() {
        return name;
    }

    public Cat(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Имя не может быть null.");
        }
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    @Override
    public String toString() {
        return "Кот: " + name;
    }
}