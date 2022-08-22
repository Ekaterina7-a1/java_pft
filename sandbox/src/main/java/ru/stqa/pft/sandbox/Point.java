package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {
    Pointsdata a = new Pointsdata(1.5, -9.5);
    Pointsdata b = new Pointsdata(5.5, 1.1);
    System.out.println("Расстояние между точками = " + distance(a, b));
  }
  public static double distance(Pointsdata p1, Pointsdata p2) {
    return Math.sqrt((p2.x - p1.x)*(p2.x - p1.x)+(p2.y - p1.y)*(p2.y - p1.y));
  }
}