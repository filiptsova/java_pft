package ru.stqa.pft.sandbox;
public class MyFirstProgram {
	public static void main(String[] args) {
		Point p1 = new Point(53,6);
		Point p2 = new Point(7,85);
		System.out.println("Расстояние между двумя точками равно " + p1.distance(p2));
	}
}