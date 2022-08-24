package ru.stqa.pft.sandbox;
public class Pointsdata {
  public double x;
  public double y;
  public Pointsdata (double x, double y){
    this.x = x;
    this.y = y;
  }
  public double distance(Pointsdata p2) {
    return Math.sqrt((p2.x - this.x)*(p2.x - this.x)+(p2.y - this.y)*(p2.y - this.y));
  }
  }
