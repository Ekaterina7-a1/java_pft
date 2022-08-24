package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {
    Pointsdata p1 = new Pointsdata(1.5, -9.5);
    Pointsdata p2 = new Pointsdata(5.5, 1.1);
    System.out.println("Расстояние между точками = " + p1.distance(p2));
  }
}