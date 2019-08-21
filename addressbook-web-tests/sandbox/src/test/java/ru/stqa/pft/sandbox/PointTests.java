package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testDistance1(){
        Point p1 =new Point(1,2);
        Point p2 =new Point(3,4);
        Assert.assertEquals(p1.distance(p2),2.8284271247461903);
    }
    @Test
    public void testDistance2(){
        Point p1 =new Point(53,6);
        Point p2 =new Point(7,85);
        assert p1.distance(p2)==91.4166286842826;
    }
}
