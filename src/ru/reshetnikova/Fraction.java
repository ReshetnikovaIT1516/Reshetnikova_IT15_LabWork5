package ru.reshetnikova;
import java.util.Objects;

public class Fraction implements FractionInterface {
    private int numerator;
    private int denominator;

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен 0.");
        }
        if (denominator < 0) {
            this.numerator = -numerator;
            this.denominator = -denominator;
        } else {
            this.denominator = denominator;
        }
    }

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен 0.");
        }

        // Перенос знака в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }
    // Конструктор по молчанию
    public Fraction() {
        this(1, 1);
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    @Override
    public double getDecimalValue() {
        return (double) numerator / denominator;
    }

    @Override
    public void setFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен 0.");
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    // Метод для упрощения дроби
    public Fraction simplify() {
        int gcd = gcd(Math.abs(numerator), denominator); // НОД
        return new Fraction(numerator / gcd, denominator / gcd);
    }

    // Нахождение наибольшего общего делителя (Евклид)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Fraction)) return false;
        Fraction other = (Fraction) obj;

        // Приводим обе дроби к простейшему виду и сравниваем
        Fraction simplifiedThis = this.simplify();
        Fraction simplifiedOther = other.simplify();

        return simplifiedThis.numerator == simplifiedOther.numerator &&
                simplifiedThis.denominator == simplifiedOther.denominator;
    }

    @Override
    public int hashCode() {
        Fraction simplified = this.simplify(); // Упрощаем дробь
        return Objects.hash(simplified.numerator, simplified.denominator);
    }
}