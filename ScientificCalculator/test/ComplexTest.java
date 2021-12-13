/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Complex;
import exceptions.DivisionException;

/**
 *
 * @author group15
 */
public class ComplexTest {
  
    private Complex a;
    private Complex b;
    private Complex c;
    private Complex d;
    private Complex e;
    private Complex f;
    private Complex g;
    private Complex h;
    private Complex l;
    
    @Before
    public void setUp() {
        a = new Complex(0,0);
        b = new Complex(100,0);
        c = new Complex(0, 100);
        d = new Complex(-100, -200);
        e = new Complex(-175.45, 900.98);
        f = new Complex(0, -525.65);
        g = new Complex(-50.50, 0);
        h = new Complex(33.33, 33.33);
        l = new Complex(-2, 0);
    }
    
    @Test
    public void testAdd(){
        assertEquals(new Complex(-175.45, 375.33), e.add(f));
        assertEquals(new Complex(-17.17, 33.33), g.add(h));
        assertEquals(new Complex(-142.12, 934.31), e.add(h));
        assertEquals(new Complex(-2, 0), a.add(l));
    }
    
    @Test
    public void testSub(){
        assertEquals(new Complex(-100, 0), a.sub(b));
        assertEquals(new Complex(100, 300), c.sub(d));
        assertEquals(new Complex(75.45, -1100.98), d.sub(e));
        assertEquals(new Complex(-33.33, -558.98), f.sub(h));
    }
    
    @Test
    public void testMultiply(){
        assertEquals(new Complex(0, 0), a.multiply(b));
        assertEquals(new Complex(20000, -10000), c.multiply(d));
        assertEquals(new Complex(473600.137, 92225.2925), e.multiply(f));
        assertEquals(new Complex(-1683.165, -1683.165), g.multiply(h));
    }
    
    @Test
    public void testDivide() {
        assertEquals(new Complex(-0.75757575, 0.75757575), g.divide(h));
        assertEquals(new Complex(0, 0), a.divide(b));
        assertEquals(new Complex(-0.4, -0.2), c.divide(d));
        assertEquals(new Complex(10.88403840, 16.14806480), e.divide(h));
    }
    
    @Test
    public void testSquare() {
        assertEquals(new Complex(10, 0), b.square());
        assertEquals(new Complex(19.267, 23.381), e.square());
        assertEquals(new Complex(0, 1.414), l.square());
        assertEquals(new Complex(0, 0), a.square());
    }
    
    @Test
    public void testInvert() {
        assertEquals(new Complex(0, 0), a.invert());
        assertEquals(new Complex(175.45, -900.98), e.invert());
        assertEquals(new Complex(-33.33, -33.33), h.invert());
        assertEquals(new Complex(50.50, 0), g.invert());
    }
    
    @Test
    public void testMod() {
        assertEquals(0 , a.mod(),0.0000000000000001);
        assertEquals(100 , b.mod(),0.0000000000000001);
        assertEquals(100 , c.mod(),0.0000000000000001);
        assertEquals(917.9039508031327784 , e.mod(),0.0000000000000001);
    }
}
