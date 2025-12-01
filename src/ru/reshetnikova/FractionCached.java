package ru.reshetnikova;


public class FractionCached implements FractionInterface {

    private final Fraction fraction;
    private Double cache;

    public FractionCached(Fraction fraction) {
        this.fraction = fraction;
    }

    @Override
    public double getDecimalValue() {
        if (cache == null) {
            cache = fraction.getDecimalValue();
        }
        return cache;
    }

    @Override
    public void setFraction(int numerator, int denominator) {
        fraction.setFraction(numerator, denominator);
        cache = null; // сброс кэша
    }

    @Override
    public String toString() {
        return fraction.toString();
    }
}