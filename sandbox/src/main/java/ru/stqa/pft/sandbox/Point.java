package ru.stqa.pft.sandbox;

public class Point {
  public static void main(String[] args) {

    Pointsdata P = new Pointsdata(10.5, 13.1, -18, -1.8);
    System.out.println("Расстояние между точками = " + distance(P));
  }
  public static double distance (Pointsdata P){

    return Math.sqrt((P.p3-P.p1)*(P.p3-P.p1)+(P.p4-P.p2)*(P.p4-P.p2));
  }
}
