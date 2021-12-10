
import org.junit.*;
import static org.junit.Assert.*;
import scientificcalculator.Complex;
import exceptions.KeyNotAlphabeticException;
import exceptions.VariableNotFoundException;
import scientificcalculator.Variable;
import scientificcalculator.VariableMap;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author group15
 */
public class VariableMapTest extends VariableMap{
    private VariableMap var;
    private Variable v1;
    private Variable v2;
    
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
        
        v1 = new Variable(key1,a);
        v2 = new Variable(key2,b);
        
        var.save(v1);
        var.save(v2);
    }
        
    @Test   
    public void testSave(){
       
        assertEquals(a,var.getVariables().get(key1));
        
        //case when you add an element with the same key
        var.save(new Variable(key1,b));
        assertEquals(b,var.getVariables().get(key1));
    }
    
    @Test(expected=KeyNotAlphabeticException.class)   
    public void testSaveVaribleException(){
        
        var.save(new Variable('2', b));
    }
    
    @Test   
    public void testPush(){
        
        Complex c = var.push(new Variable(key1, null));
        Complex c1 = var.push(new Variable(key2,null));
        
        assertEquals(a,c);
        assertEquals(b,c1);
    }
    
    @Test(expected=VariableNotFoundException.class)   
    public void testPushVaribleException(){
        
        assertNull(var.push(new Variable(key3, null)));
    }
    
    @Test
    public void testAdd(){
        var.add(new Variable(key1, c));
        assertEquals(var.push(new Variable(key1, null)), a.add(c));
        var.add(new Variable('x', b));
        assertEquals(var.push(new Variable('x', null)), b);
    }
    
    @Test
    public void testSub(){
        var.sub(new Variable(key1, c));
        assertEquals(var.push(new Variable(key1, null)), a.sub(c));
        var.sub(new Variable('x', b));
        assertEquals(var.push(new Variable('x',null)), b);
    }
}
