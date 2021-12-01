
import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Complex;
import exceptions.KeyNotAlphabeticException;
import scientificcalculator.VariableMap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author benedettocirillo
 */
public class VariableMapTest extends VariableMap{
    private VariableMap var;
    private Complex a;
    private Complex b;
    private Complex c;
    private char key1;
    private char key2;
    private char key3;
    
    @Before
    public void setUp(){
        
        var = new VariableMap();
        a = new Complex(5,6);
        b = new Complex(100,0);
        c = new Complex(-50.2, 15.65);
        key1 = 'a';
        key2 = 'b';
        key3 = 'c';
        var.saveVariable(key1, a);
        var.saveVariable(key2, b);
    }
    
    
    @Test   
    public void testSaveVarible(){
       
        assertEquals(a,var.getVariables().get(key1));
        
        //case when you add an element with the same key
        var.saveVariable(key1, b);
        assertEquals(b,var.getVariables().get(key1));
    }
    
    @Test(expected=KeyNotAlphabeticException.class)   
    public void testSaveVaribleException(){
        var.saveVariable('2', b);
    }
    
    @Test   
    public void testpushVarible(){
        
        assertNull(var.pushVariable(key3));
        
        Complex c = var.pushVariable(key1);
        Complex c1 = var.pushVariable(key2);
        
        assertEquals(a,c);
        assertEquals(b,c1);
    }
    
    @Test
    public void testAddVariable(){
        var.addVariable(key1, c);
        assertEquals(var.pushVariable(key1), a.add(c));
        var.addVariable('x', b);
        assertEquals(var.pushVariable('x'), b);
    }
    
}
