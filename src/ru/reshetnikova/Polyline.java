package ru.reshetnikova;

import java.util.Arrays;
import java.util.List;

public class Polyline {
    private final List<Point> points;

    public Polyline(List<Point> points) {
        this.points = points;
    }

    public Polyline(Point... points) {
        this.points = Arrays.asList(points);
    }

    public List<Point> getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return "Линия " + points;
    }
}