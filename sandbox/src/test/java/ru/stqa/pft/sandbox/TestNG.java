package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {
  @Test
  public void testArea() {
    Pointsdata p1 = new Pointsdata(1.5, -9.5);
    Pointsdata p2 = new Pointsdata(5.5, 1.1);
    Assert.assertEquals(p1.distance(p2), 11.329607230614839);
  }
}
