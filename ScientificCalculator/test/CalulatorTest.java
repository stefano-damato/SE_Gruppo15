/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Calculator;
import scientificcalculator.Complex;
import exceptions.DivisionException;
import exceptions.KeyNotAlphabeticException;
import java.util.EmptyStackException;
import java.util.*;
import exceptions.LessOf2ElementsException;
import exceptions.OperationFailedException;
import exceptions.VariableNotFoundException;
import exceptions.WrongInputException;
import scientificcalculator.Variable;
/**
/**
 *
 * @author group15
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
    public void testSaveVariable() {
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        calc.saveVariable('g');
        calc.saveVariable('f');
        calc.saveVariable('e');
        
        assertEquals(true, calc.getComplexStack().empty());
        assertEquals(b, calc.getVariables().getVariables().get('b'));
        assertEquals(h, calc.getVariables().getVariables().get('h'));
        assertEquals(g, calc.getVariables().getVariables().get('g'));
        assertEquals(f, calc.getVariables().getVariables().get('f'));
        assertEquals(e, calc.getVariables().getVariables().get('e'));                           
    }
 
    @Test(expected = EmptyStackException.class)
    public void testSaveVariableEmptyStackException(){
        calc.saveVariable('a');              
    }
    
    @Test(expected = KeyNotAlphabeticException.class)
    public void testSaveVariableKeyNotAlphabeticException(){
        calc.getComplexStack().push(e);  
        calc.saveVariable('+');
    }
    
    @Test(expected = KeyNotAlphabeticException.class)
    public void testSaveVariable2KeyNotAlphabeticException(){
        calc.getComplexStack().push(e);  
        calc.saveVariable('2');
    }
    
    @Test(expected = KeyNotAlphabeticException.class)
    public void testSaveVariable3KeyNotAlphabeticException(){
        calc.getComplexStack().push(e);  
        calc.saveVariable(' ');
    }
    
    @Test
    public void testPushVariable() {
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        calc.saveVariable('g');
        calc.saveVariable('f');
        calc.saveVariable('e');
        
        assertEquals(true, calc.getComplexStack().empty());
        
        calc.pushVariable('e');       
        assertEquals(e, calc.getVariables().getVariables().get('e')); 
        calc.pushVariable('b');       
        assertEquals(b, calc.getVariables().getVariables().get('b'));
        calc.pushVariable('f');       
        assertEquals(f, calc.getVariables().getVariables().get('f'));
        calc.pushVariable('h');       
        assertEquals(h, calc.getVariables().getVariables().get('h'));
    }
    
    
    @Test(expected = VariableNotFoundException.class)
    public void testPushVariableException(){
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.pushVariable('z');
    }
    
    @Test
    public void testAddVariable(){
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.addVariable('h');
        Complex hg = h.add(g);
        assertEquals(hg, calc.getVariables().getVariables().get('h'));
        calc.addVariable('b');
        Complex bf = b.add(f);
        assertEquals(bf, calc.getVariables().getVariables().get('b'));
        calc.addVariable('h');
        Complex hge = hg.add(e);
        assertEquals(hge, calc.getVariables().getVariables().get('h'));
    }
    
    @Test(expected = EmptyStackException.class)
    public void testAddVariableEmptyStackException(){
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.addVariable('h');
    }

    @Test(expected = KeyNotAlphabeticException.class)
    public void testAddVariableKeyNotAlphabeticException(){
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(g);
        
        calc.saveVariable('h');
        calc.addVariable('.');
    }
    
    @Test
    public void testSubVariable(){
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.subVariable('h');
        Complex hg = h.sub(g);
        assertEquals(hg, calc.getVariables().getVariables().get('h'));
        calc.subVariable('b');
        Complex bf = b.sub(f);
        assertEquals(bf, calc.getVariables().getVariables().get('b'));
        calc.subVariable('h');
        Complex hge = hg.sub(e);
        assertEquals(hge, calc.getVariables().getVariables().get('h'));
    }
    
    @Test(expected = EmptyStackException.class)
    public void testSubVariableEmptyStackException(){
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.subVariable('h');
    }

    @Test(expected = KeyNotAlphabeticException.class)
    public void testSubVariableKeyNotAlphabeticException(){
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(g);
        
        calc.saveVariable('h');
        calc.subVariable('.');
    }
    
    @Test
    public void testSelectOperationVariableToInvoke() {
        String s = ">b";
        String s2 = "<b";
        String s3 = "+b";
        String s4 = "-b";
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.selectOperationVariableToInvoke(s);
        assertEquals(b, calc.getVariables().getVariables().get('b'));
        assertEquals(h, calc.getComplexStack().lastElement());
        calc.selectOperationVariableToInvoke(s2);
        assertEquals(b, calc.getComplexStack().lastElement());
        calc.selectOperationVariableToInvoke(s3);
        Complex bb = b.add(b);
        assertEquals(bb, calc.getVariables().getVariables().get('b'));
        calc.selectOperationVariableToInvoke(s4);
        Complex bbh = bb.sub(h);
        assertEquals(bbh, calc.getVariables().getVariables().get('b'));
    }
    
    @Test(expected = OperationFailedException.class)
    public void testSelectOperationVariableToInvokeOperationFailedException() {
        String s = "<d";
        calc.selectOperationVariableToInvoke(s);
    }
    
    @Test(expected = OperationFailedException.class)
    public void testSelectOperationVariableToInvoke2OperationFailedException() {
        calc.getComplexStack().push(e);
        calc.saveVariable('e');
        String s = "+e";
        calc.selectOperationVariableToInvoke(s);
    }
    
    
    @Test(expected = WrongInputException.class)
    public void testSelectOperationVariableToInvokeWrongInputException() {
        String s = "ss";
        calc.selectOperationVariableToInvoke(s);
    }
    
    
    @Test
    public void selectOperationVariableStackToInvoke(){
        String s = "save";
        String s2 = "restore";
        
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        calc.getComplexStack().push(b);
        
        calc.saveVariable('b');
        calc.saveVariable('h');
        
        calc.selectOperationVariableStackToInvoke(s);
        assertEquals(b, calc.getVariables().getVariables().get('b'));
        assertEquals(h, calc.getVariables().getVariables().get('h'));
        
        calc.saveVariable('g');
        calc.saveVariable('f');
        assertEquals(g, calc.getVariables().getVariables().get('g'));
        assertEquals(f, calc.getVariables().getVariables().get('f'));
        
        Complex be = b.add(e);
        calc.addVariable('b');
        assertEquals(be, calc.getVariables().getVariables().get('b'));
        
        calc.selectOperationVariableStackToInvoke(s2);
        assertEquals(b, calc.getVariables().getVariables().get('b'));
        assertEquals(h, calc.getVariables().getVariables().get('h'));
        assertEquals(false, calc.getVariables().getVariables().containsKey('g'));
        assertEquals(false, calc.getVariables().getVariables().containsKey('f'));
        
    }
    
    
    @Test (expected = OperationFailedException.class)
    public void selectOperationVariableStackToInvokeException() {
        calc.selectOperationVariableStackToInvoke("restore");
    }
    
    @Test
    public void testInvokeOperation(){
        calc.getComplexStack().push(a);
        calc.getComplexStack().push(b);
        calc.getComplexStack().push(c);
        calc.getComplexStack().push(d);
        calc.getComplexStack().push(e);
        calc.getComplexStack().push(f);
        calc.getComplexStack().push(g);
        calc.getComplexStack().push(h);
        
        String s = "+ - * / +- sqrt";
        calc.getOperations().addOperation("operation", s);
        Complex a = h.add(g).sub(f).multiply(e).divide(d).invert().square();
        calc.invokeOperation("operation");
        assertEquals(a, calc.getComplexStack().lastElement());
        String s2 = "over >a 56 87 <a +a 91 -a drop swap 100j 200-3002j dup operation";
        Complex b = new Complex(13.542892776690213,71.06580101346465);
        calc.getOperations().addOperation("op", s2);
        calc.invokeOperation("op");
        assertEquals(b, calc.getComplexStack().lastElement());
        String s3 = "clear";
        calc.getOperations().addOperation("secondOp", s3);
        calc.invokeOperation("secondOp");
        assertEquals(true, calc.getComplexStack().empty());
        String s4 = "35 78 >a >b save 41 98 +a +b restore <a 20 *";
        Complex c = new Complex(1560,0);
        calc.getOperations().addOperation("thirdOp", s4);
        calc.invokeOperation("thirdOp");
        assertEquals(c, calc.getComplexStack().lastElement());
    }
    
    
}