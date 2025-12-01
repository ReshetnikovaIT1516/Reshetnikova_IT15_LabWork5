package ru.reshetnikova;

public class CountMeowCat implements Meow {
    private final Cat cat;
    private int meowCount;

    public Cat getCat() {
        return cat;
    }

    public int getMeowCount() {
        return meowCount;
    }

    public CountMeowCat(Cat cat) {
        if (cat == null) {
            throw new IllegalArgumentException("Кот не может быть null.");
        }
        this.cat = cat;
        this.meowCount = 0;
    }

    @Override
    public void meow() {
        cat.meow();
        meowCount++;
    }

    @Override
    public String toString() {
        return cat.toString() + " помяукал " + meowCount + " раз.";
    }
}