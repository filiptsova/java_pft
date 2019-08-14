package ru.stqa.pft.sandbox;
import java.util.Scanner;

public class Point {
    Scanner scanner = new Scanner(System.in);
    public double x =scanner.nextDouble();
    public double y =scanner.nextDouble();

    public double distance(Point p) {
        double x = Math.pow((this.x - p.x), 2);
        double y = Math.pow((this.y - p.y), 2);
        double d = Math.sqrt(x + y);
        return d;
    }
}
