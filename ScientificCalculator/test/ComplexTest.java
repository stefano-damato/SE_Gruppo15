/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Complex;
import scientificcalculator.DivisionException;

/**
 *
 * @author nicol
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
    private Complex i;
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
        i = new Complex(0,0);
        l = new Complex(-2, 0);
    }
    
    @Test
    public void testAdd(){
        assertEquals(new Complex(-175.45, 375.33), e.add(f));
        assertEquals(new Complex(-17.17, 33.33), g.add(h));
    }
    
    @Test
    public void testSub(){
        assertEquals(new Complex(-100, 0), a.sub(b));
        assertEquals(new Complex(100, 300), c.sub(d));
    }
    
    @Test
    public void testMultiply(){
        assertEquals(new Complex(0, 0), a.multiply(b));
        assertEquals(new Complex(20000, -10000), c.multiply(d));
    }
    
    @Test
    public void testDivide() {
        assertEquals(new Complex(-0.75757575, 0.75757575), g.divide(h));
    }
    
    @Test(expected = DivisionException.class)
    public void testDivideException(){
        assertEquals(new Complex(0.0, 0.0), h.divide(i));
    }
    
    @Test
    public void testSquare() {
        assertEquals(new Complex(10.0, 0.0), b.square());
        assertEquals(new Complex(19.267, 23.381), e.square());
        assertEquals(new Complex(0.0, 1.414), l.square());
    }
    
    @Test
    public void testInvert() {
        assertEquals(new Complex(0.0, 0.0), a.invert());
        assertEquals(new Complex(175.45, -900.98), e.invert());
    }
    
}
