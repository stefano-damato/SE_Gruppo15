/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exceptions.DivisionException;
import exceptions.LessOf2ElementsException;
import exceptions.OperationFailedException;
import exceptions.WrongInputException;
import java.util.EmptyStackException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.Complex;
import scientificcalculator.ComplexStack;

/**
 *
 * @author group15
 */
public class ComplexStackTest {
    private ComplexStack stack;
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
        stack = new ComplexStack();
        
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
    public void testLastElement(){
        stack.insert(a);
        stack.insert(b);
        assertEquals(b, stack.lastElement());
        stack.insert(c);
        assertEquals(c, stack.lastElement());
        stack.insert(e);
        stack.insert(f);
        stack.insert(h);
        assertEquals(h, stack.lastElement());        
    }
    
    @Test
    public void testInsert(){
        stack.insert(a);
        assertEquals(a, stack.lastElement());
        stack.insert(f);
        assertEquals(f, stack.lastElement());
        stack.insert(h);
        assertEquals(h, stack.lastElement());
        stack.insert(g);
        assertEquals(g, stack.lastElement());
    }
    
    @Test   
    public void testAdd(){
        stack.insert(a);
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        
        Complex hg = h.add(g);
	stack.add();
            assertEquals(hg, stack.lastElement());
	Complex hgf = hg.add(f);
	stack.add();
            assertEquals(hgf, stack.lastElement());
        Complex hgfe = hgf.add(e);
	stack.add();
            assertEquals(hgfe, stack.lastElement());
        Complex hgfed = hgfe.add(d);
	stack.add();
            assertEquals(hgfed, stack.lastElement());
        Complex hgfedc = hgfed.add(c);
        stack.add();
            assertEquals(hgfedc, stack.lastElement());
        Complex hgfedcb = hgfedc.add(b);
        stack.add();
            assertEquals(hgfedcb, stack.lastElement());
        Complex hgfedcba = hgfedcb.add(a);
        stack.add();
            assertEquals(hgfedcba, stack.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testAddExceptionOneComplex(){
        stack.insert(a);
        stack.add();        
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testAddException(){
        stack.add();        
    }
    
    @Test 
    public void testSub(){
        stack.insert(a);
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        
        Complex hg = h.sub(g);
	stack.sub();
            assertEquals(hg, stack.lastElement());
	Complex hgf = hg.sub(f);
	stack.sub();
            assertEquals(hgf, stack.lastElement());
        Complex hgfe = hgf.sub(e);
	stack.sub();
            assertEquals(hgfe, stack.lastElement());
        Complex hgfed = hgfe.sub(d);
	stack.sub();
            assertEquals(hgfed, stack.lastElement());
        Complex hgfedc = hgfed.sub(c);
        stack.sub();
            assertEquals(hgfedc, stack.lastElement());
        Complex hgfedcb = hgfedc.sub(b);
        stack.sub();
            assertEquals(hgfedcb, stack.lastElement());
        Complex hgfedcba = hgfedcb.sub(a);
        stack.sub();
            assertEquals(hgfedcba, stack.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testSubExceptionOneElement(){
        stack.insert(a);
        stack.sub();
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testSubException(){
        stack.sub();
    }
    
    @Test
    public void testMultiply(){
        stack.insert(a);
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        
        Complex hg = h.multiply(g);
	stack.multiply();
            assertEquals(hg, stack.lastElement());
	Complex hgf = hg.multiply(f);
	stack.multiply();
            assertEquals(hgf, stack.lastElement());
        Complex hgfe = hgf.multiply(e);
	stack.multiply();
            assertEquals(hgfe, stack.lastElement());
        Complex hgfed = hgfe.multiply(d);
	stack.multiply();
            assertEquals(hgfed, stack.lastElement());
        Complex hgfedc = hgfed.multiply(c);
        stack.multiply();
            assertEquals(hgfedc, stack.lastElement());
        Complex hgfedcb = hgfedc.multiply(b);
        stack.multiply();
            assertEquals(hgfedcb, stack.lastElement());
        Complex hgfedcba = hgfedcb.multiply(a);
        stack.multiply();
            assertEquals(hgfedcba, stack.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testMultiplyExceptionOneElement(){
        stack.insert(a);
        stack.multiply();
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testMultiplyException(){
        stack.multiply();
    }
    
    @Test 
    public void testDivide(){
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        
        
        Complex hg = h.divide(g);
	stack.divide();
            assertEquals(hg, stack.lastElement());
	Complex hgf = hg.divide(f);
	stack.divide();
            assertEquals(hgf, stack.lastElement());
        Complex hgfe = hgf.divide(e);
	stack.divide();
            assertEquals(hgfe, stack.lastElement());
        Complex hgfed = hgfe.divide(d);
	stack.divide();
            assertEquals(hgfed, stack.lastElement());
        Complex hgfedc = hgfed.divide(c);
        stack.divide();
            assertEquals(hgfedc, stack.lastElement());
        Complex hgfedcb = hgfedc.divide(b);
        stack.divide();
            assertEquals(hgfedcb, stack.lastElement());
       
    }
    
    @Test (expected = DivisionException.class)
    public void testDivideZero(){
        stack.insert(a);
        stack.insert(g);     
	stack.divide();
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testDivideExceptionOneElement(){
        stack.insert(a);
        stack.divide();
    }

    @Test(expected = LessOf2ElementsException.class)
    public void testDivideException(){
        stack.divide();
    }
    
    @Test 
    public void testSquare(){    
        Complex hg = h.square();
        Complex ha = a.square();
        Complex hb = b.square();
        Complex hc = c.square();
        Complex hd = d.square();
        stack.insert(h);   
	stack.square();
        assertEquals(hg, stack.lastElement());
        stack.insert(a);
        stack.square();
        assertEquals(ha, stack.lastElement());
        stack.insert(b);
        stack.square();
        assertEquals(hb, stack.lastElement());
        stack.insert(c);
        stack.square();
        assertEquals(hc, stack.lastElement());
        stack.insert(d);
        stack.square();
        assertEquals(hd, stack.lastElement());     
    }
    
    @Test(expected = EmptyStackException.class)
        public void testSquareException(){
        stack.square();
    }
    
    @Test 
    public void testInvert(){
        stack.insert(h);  
        Complex hg = h.invert();
	stack.invert();
            assertEquals(hg, stack.lastElement());
        stack.insert(a);  
        Complex ag = a.invert();
	stack.invert();
            assertEquals(ag, stack.lastElement());
        stack.insert(b);  
        Complex bg = b.invert();
	stack.invert();
            assertEquals(bg, stack.lastElement());
        stack.insert(c);  
        Complex cg = c.invert();
	stack.invert();
            assertEquals(cg, stack.lastElement());
        stack.insert(d);  
        Complex dg = d.invert();
	stack.invert();
            assertEquals(dg, stack.lastElement());
    }
    
    @Test(expected = EmptyStackException.class)
    public void testInvertException(){
        stack.invert();
    }
    
    @Test
    public void testClear(){
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.clear();
        assertEquals(true, stack.getComplexStack().empty());
    }
    
    @Test(expected = EmptyStackException.class)
    public void testClearException(){
        stack.clear();
    }
    
    @Test
    public void testDrop(){
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        assertEquals(f,stack.drop());
        assertEquals(e, stack.lastElement());
        assertEquals(e,stack.drop());
        assertEquals(d, stack.lastElement());
        assertEquals(d,stack.drop());
        assertEquals(true, stack.getComplexStack().empty());
    }
    
    @Test(expected = EmptyStackException.class)
    public void testDropException(){
        stack.drop();
    }
    
    @Test
    public void testDup(){
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.dup();
        assertEquals(f, stack.lastElement());
        assertEquals(f , stack.getComplexStack().get(stack.getComplexStack().size()-2));
        stack.drop();
        stack.drop();
        stack.dup();
        assertEquals(e, stack.lastElement());
        assertEquals(e , stack.getComplexStack().get(stack.getComplexStack().size()-2));
        stack.drop();
        stack.drop();
        stack.dup();
        assertEquals(d, stack.lastElement());
        assertEquals(d , stack.getComplexStack().get(stack.getComplexStack().size()-2));
    }
    
    @Test(expected = EmptyStackException.class)
    public void testDupException(){
        stack.dup();
    }   
    
    @Test
    public void testSwap(){
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        stack.swap();
        assertEquals(g, stack.lastElement());
        assertEquals(h, stack.getComplexStack().get(stack.getComplexStack().size()-2));
        stack.drop();
        stack.swap();
        assertEquals(f, stack.lastElement());
        assertEquals(h, stack.getComplexStack().get(stack.getComplexStack().size()-2));
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testSwapExceptionOneElement(){
        stack.insert(d);
        stack.swap();
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testSwapException(){ 
        stack.swap();
    }
    
    @Test
    public void testOver(){
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(a);
        stack.insert(b);
        stack.over();
        assertEquals(a, stack.lastElement());
        stack.drop();
        stack.drop();
        stack.over();
        assertEquals(f, stack.lastElement());
        stack.drop();
        stack.drop();
        stack.over();
        assertEquals(e, stack.lastElement());
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testOverExceptionOneElement(){
        stack.insert(c);
        stack.over();
    }
    
    @Test(expected = LessOf2ElementsException.class)
    public void testOverException(){;
        stack.over();
    }
    
    @Test
    public void testInsertString() {
        String s = "27+10j";
        String s1 = "-31+4j";
        String s2 = "-15.982+10j";
        String s3 = "-874.944-100.938j";
        String s4 = "24.938";
        String s5 = "-10j";
        String s6 = " 84    + 7j   ";
        stack.insert(s);
        Complex cs = new Complex(27,10);
        assertEquals(cs, stack.lastElement());
        stack.insert(s1);
        Complex cs1 = new Complex(-31,4);
        assertEquals(cs1, stack.lastElement());
        stack.insert(s2);
        Complex cs2 = new Complex(-15.982,10);
        assertEquals(cs2, stack.lastElement());
        stack.insert(s3);
        Complex cs3 = new Complex(-874.944,-100.938);
        assertEquals(cs3, stack.lastElement());
        stack.insert(s4);
        Complex cs4 = new Complex(24.938,0);
        assertEquals(cs4, stack.lastElement());
        stack.insert(s5);
        Complex cs5 = new Complex(0,-10);
        assertEquals(cs5, stack.lastElement());
        stack.insert(s6);
        Complex cs6 = new Complex(84,7);
        assertEquals(cs6, stack.lastElement());
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertStringException(){;
        String s = "27+j10";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString2Exception(){;
        String s = "10j+20";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString3Exception(){;
        String s = "j10+21.3";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString4Exception(){;
        String s = "10js+2g0";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString5Exception(){;
        String s = "+c+10j";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString6Exception(){;
        String s = ". 23";
        stack.insert(s);
    }
    
    @Test(expected = WrongInputException.class)
    public void testInsertString7Exception(){;
        String s = "++65+10j";
        stack.insert(s);
    }
    
    @Test
    public void testSelectOperationToInvoke(){
        stack.insert(a);
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.insert(e);
        stack.insert(f);
        stack.insert(g);
        stack.insert(h);
        Complex sum = h.add(g);
        stack.selectOperationToInvoke("+");
        assertEquals(sum, stack.lastElement());
        Complex sub = sum.sub(f);
        stack.selectOperationToInvoke("-");
        assertEquals(sub, stack.lastElement());
        Complex multiply = sub.multiply(e);
        stack.selectOperationToInvoke("*");
        assertEquals(multiply, stack.lastElement());
        Complex divide = multiply.divide(d);
        stack.selectOperationToInvoke("/");
        assertEquals(divide, stack.lastElement());
        Complex invert = divide.invert();
        stack.selectOperationToInvoke("+-");
        assertEquals(invert, stack.lastElement());
        Complex sqrt = invert.square();
        stack.selectOperationToInvoke("sqrt");
        assertEquals(sqrt, stack.lastElement());
        stack.selectOperationToInvoke("drop");
        assertEquals(c, stack.lastElement());
        stack.selectOperationToInvoke("dup");
        assertEquals(c, stack.lastElement());
        assertEquals(c, stack.getComplexStack().elementAt(stack.getComplexStack().size() - 2));
        stack.drop();
        stack.selectOperationToInvoke("swap");
        assertEquals(b, stack.lastElement());
        assertEquals(c, stack.getComplexStack().elementAt(stack.getComplexStack().size() - 2));
        stack.selectOperationToInvoke("over");
        assertEquals(c, stack.lastElement());
        stack.selectOperationToInvoke("clear");
        assertEquals(true, stack.getComplexStack().empty());
        Complex cs = new Complex(27,10);
        stack.selectOperationToInvoke("27+10j");
        assertEquals(cs, stack.lastElement());
    }
    
    
    @Test(expected = OperationFailedException.class)
    public void testSelectOperationToInvokeException(){
        stack.insert(a);
        stack.insert(b);
        stack.insert(c);
        stack.insert(d);
        stack.selectOperationToInvoke("hypotenuse");
    }
    
    @Test(expected = OperationFailedException.class)
    public void testSelectOperationToInvoke2Exception(){
        stack.insert(a);
        stack.selectOperationToInvoke("+");
    }
}