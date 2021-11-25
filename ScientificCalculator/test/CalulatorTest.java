/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Calculator;
import scientificcalculator.Complex;
import scientificcalculator.DivisionException;
import java.util.EmptyStackException;
import java.util.*;
import scientificcalculator.LessOf2ElementsException;
/**
/**
 *
 * @author andreapassarelli
 */
public class CalulatorTest extends Calculator{
    private Calculator calc;
    private Complex a;
    private Complex b;
    private Complex c;
    private Complex d;
    private Complex e;
    private Complex f;
    private Complex g;
    private Complex h;
    
    
    @Before
    public void setUp(){
        calc = new Calculator();
        
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
        calc.insert(d);
        calc.insert(e);
        calc.insert(f);
        calc.insert(g);
        calc.insert(h);
        
        Complex hg = h.add(g);
	calc.add();
            assertEquals(hg, calc.lastElement());
	Complex hgf = hg.add(f);
	calc.add();
            assertEquals(hgf, calc.lastElement());
        Complex hgfe = hgf.add(e);
	calc.add();
            assertEquals(hgfe, calc.lastElement());
        Complex hgfed = hgfe.add(d);
	calc.add();
            assertEquals(hgfed, calc.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testAddException(){
    calc.insert(a);
    calc.add();
}
    @Test 
    public void testSub(){
        calc.insert(d);
        calc.insert(e);
        calc.insert(f);
        calc.insert(g);
        calc.insert(h);
        
        Complex hg = h.sub(g);
	calc.sub();
            assertEquals(hg, calc.lastElement());
	Complex hgf = hg.sub(f);
	calc.sub();
            assertEquals(hgf, calc.lastElement());
        Complex hgfe = hgf.sub(e);
	calc.sub();
            assertEquals(hgfe, calc.lastElement());
        Complex hgfed = hgfe.sub(d);
	calc.sub();
            assertEquals(hgfed, calc.lastElement());
    }
    @Test(expected = LessOf2ElementsException.class)
    public void testSubException(){
    calc.insert(a);
    calc.sub();
    }
    
    @Test
    public void testMultiply(){
        calc.insert(d);
        calc.insert(e);
        calc.insert(f);
        calc.insert(g);
        calc.insert(h);
        
        Complex hg = h.multiply(g);
	calc.multiply();
            assertEquals(hg, calc.lastElement());
	Complex hgf = hg.multiply(f);
	calc.multiply();
            assertEquals(hgf, calc.lastElement());
        Complex hgfe = hgf.multiply(e);
	calc.multiply();
            assertEquals(hgfe, calc.lastElement());
        Complex hgfed = hgfe.multiply(d);
	calc.multiply();
            assertEquals(hgfed, calc.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testMultiplyException(){
    calc.insert(a);
    calc.multiply();
    }
    
    @Test
    public void testDivide(){
        calc.insert(d);
        calc.insert(e);
        calc.insert(f);
        calc.insert(g);
        calc.insert(h);
        
        Complex hg = h.divide(g);
	calc.divide();
            assertEquals(hg, calc.lastElement());
	Complex hgf = hg.divide(f);
	calc.divide();
            assertEquals(hgf, calc.lastElement());
        Complex hgfe = hgf.divide(e);
	calc.divide();
            assertEquals(hgfe, calc.lastElement());
        Complex hgfed = hgfe.divide(d);
	calc.divide();
            assertEquals(hgfed, calc.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testDivideException(){
    calc.insert(a);
    calc.divide();
    }
    
    @Test 
    public void testSquare(){
        
        calc.insert(h);
        
        Complex hg = h.square();
	calc.square();
            assertEquals(hg, calc.lastElement());
    }
    
    @Test(expected = EmptyStackException.class)
    public void testSquareException(){
    calc.insert(a);
    calc.square();
    }
    
    @Test 
    public void testInvert(){
        calc.insert(h);
     
        Complex hg = h.invert();
	calc.invert();
            assertEquals(h, calc.lastElement());
	
    }
    
    @Test(expected = EmptyStackException.class)
    public void testInvertException(){
    calc.insert(a);
    calc.invert();
    }
    
}
