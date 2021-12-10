/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.EmptyStackException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.VariableMap;
import scientificcalculator.VariableMapStack;

/**
 *
 * @author group15
 */
public class VariableMapStackTest {
    private VariableMapStack variables; 
    
    @Before
    public void setUp() {
        variables = new VariableMapStack();
    }
    
    @Test
    public void testSave() {
        VariableMap map = new VariableMap();
        VariableMap map2 = new VariableMap();
        VariableMap map3 = new VariableMap();
        
        assertEquals(true, variables.getVariables().empty());
        
        variables.save(map);
        assertEquals(1, variables.getVariables().size());
        assertEquals(map, variables.getVariables().lastElement());
        
        variables.save(map2);
        assertEquals(2, variables.getVariables().size());
        assertEquals(map2, variables.getVariables().lastElement());
        
        variables.save(map3);
        assertEquals(3, variables.getVariables().size());
        assertEquals(map3, variables.getVariables().lastElement());        
    }
    
    @Test
    public void testRestore() {
        VariableMap map = new VariableMap();
        VariableMap map2 = new VariableMap();
        VariableMap map3 = new VariableMap();
        
        variables.save(map);
        variables.save(map2);
        variables.save(map3);
        
        assertEquals(3, variables.getVariables().size());
        
        variables.restore();
        assertEquals(2, variables.getVariables().size());
        assertEquals(map2, variables.getVariables().lastElement());
        
        variables.restore();
        assertEquals(1, variables.getVariables().size());
        assertEquals(map, variables.getVariables().lastElement());
        
        variables.restore();
        assertEquals(true, variables.getVariables().empty());
    }
    
    @Test(expected = EmptyStackException.class)
    public void testRestoreException() {
        variables.restore();
    }
}
