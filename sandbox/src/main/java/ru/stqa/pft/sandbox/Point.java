package ru.stqa.pft.sandbox;

public class Point {
    
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p) {
        double x = Math.pow((this.x - p.x), 2);
        double y = Math.pow((this.y - p.y), 2);
        double d = Math.sqrt(x + y);
        return d;
    }
}
