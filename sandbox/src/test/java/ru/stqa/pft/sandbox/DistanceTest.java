package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {
  @Test
  public void testDistanceOne() {
    Pointsdata p1 = new Pointsdata(1.5, -9.5);
    Pointsdata p2 = new Pointsdata(5.5, 1.1);
    Assert.assertEquals(p1.distance(p2), 11.329607230614839);
  }
  @Test
  public void testDistanceTwo() {
    Pointsdata p1 = new Pointsdata(-5.5, -6.0);
    Pointsdata p2 = new Pointsdata(99.9, 11.11);
    Assert.assertEquals(p1.distance(p2), 106.77973637352736);
  }
}
