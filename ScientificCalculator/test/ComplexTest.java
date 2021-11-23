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
    }
    
    @Test
    public void testAdd(){
        assertEquals(-175.45, e.add(f).getReal(), 0.001);
        assertEquals(375.33, e.add(f).getImaginary(), 0.001);
        assertEquals(-17.17, g.add(h).getReal(), 0.001);
        assertEquals(33.33, g.add(h).getImaginary(), 0.001);
    }
    
    @Test
    public void testSub(){
        assertEquals(-100, a.sub(b).getReal(), 0.001);
        assertEquals(0, a.sub(b).getImaginary(), 0.001);
        assertEquals(100, c.sub(d).getReal(), 0.001);
        assertEquals(300, c.sub(d).getImaginary(), 0.001);
    }
    
    @Test
    public void testMultiply(){
        assertEquals(0, a.multiply(b).getReal(), 0.001);
        assertEquals(0, a.multiply(b).getImaginary(), 0.001);
        assertEquals(20000, c.multiply(d).getReal(), 0.001);
        assertEquals(-10000, c.multiply(d).getImaginary(), 0.001);
    }
    
    @Test
    public void testDivide() {
        assertEquals(-0.757, g.divide(h).getReal(), 0.001);
        assertEquals(0.757, g.divide(h).getImaginary(), 0.001);
    }
    
    @Test(expected = DivisionException.class)
    public void testDivideException(){
        assertEquals(0.0, e.divide(f).getReal(), 0.001);
        assertEquals(0.0, e.divide(f).getImaginary(), 0.001);
    }
    
    
    
}
