package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.Point.distance;

public class TestNG {
  @Test
  public void testArea() {
    Pointsdata a = new Pointsdata(1.5, -9.5);
    Pointsdata b = new Pointsdata(5.5, 1.1);
    Assert.assertEquals(distance(a, b), 11.329607230614839);
  }
}
