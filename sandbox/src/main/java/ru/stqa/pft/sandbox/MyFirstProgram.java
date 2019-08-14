package ru.stqa.pft.sandbox;
public class MyFirstProgram {
	public static void main(String[] args) {
		System.out.println("Введите координаты двух точек");
		Point p1 = new Point();
		Point p2 = new Point();
		System.out.println("Расстояние между двумя точками равно " + p1.distance(p2));
	}
}