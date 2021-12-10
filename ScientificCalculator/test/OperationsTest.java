/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exceptions.KeyAlreadyPresentInOperations;
import exceptions.KeyNotPresentInOperations;
import exceptions.WrongInputException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import scientificcalculator.Operations;

/**
 *
 * @author group15
 */
public class OperationsTest {
    private Operations operations;
    private String nameA;
    private String operationA;
    private String nameB;
    private String operationB;
    private String nameC;
    private String operationC;
    
    @Before
    public void setUp(){  
        operations = new Operations();
        nameA = "OperationA";
        operationA = "+ -";
        nameB = "OperationB";
        operationB = "/ *";
        nameC = "OperationC";
        operationC = "sqrt +-";
    }
    
    @Test
    public void testAddOperation(){
       operations.addOperation(nameA, operationA);
       operations.addOperation(nameB, operationB);
       operations.addOperation(nameC, operationC);
       assertEquals(true , operations.containName(nameA));
       assertEquals("+ -" , operations.getSequence(nameA));
       assertEquals(true , operations.containName(nameB));
       assertEquals("/ *" , operations.getSequence(nameB));
       assertEquals(true , operations.containName(nameC));
       assertEquals("sqrt +-" , operations.getSequence(nameC));
    }
    
    @Test (expected = KeyAlreadyPresentInOperations.class)
    public void testAddOperationException() {
        operations.addOperation(nameA, operationA);
        operations.addOperation(nameA, operationA);
    }
    
    @Test
    public void testModify() {
        operations.addOperation(nameA, operationA);
        operations.addOperation(nameB, operationB);
        operations.addOperation(nameC, operationC);
        operations.modify(nameA, "<a over");
        operations.modify(nameB, "sqrt +");
        operations.modify(nameC, "/ +");
        assertEquals("<a over" , operations.getSequence(nameA));
        assertEquals("sqrt +" , operations.getSequence(nameB));
        assertEquals("/ +" , operations.getSequence(nameC));
    }
    
    @Test (expected = KeyNotPresentInOperations.class)
    public void testModifyException() {
        operations.modify(nameA, "<a over");
    }
    
    @Test
    public void testDelete() {
        operations.addOperation(nameA, operationA);
        operations.addOperation(nameB, operationB);
        operations.addOperation(nameC, operationC);
        operations.delete(nameA);
        operations.delete(nameB);
        operations.delete(nameC);
        assertEquals(false , operations.containName(nameA));
        assertEquals(false , operations.containName(nameB));
        assertEquals(false , operations.containName(nameC));
    }
    
    @Test (expected = KeyNotPresentInOperations.class)
    public void testDeleteException() {
        operations.delete(nameA);
    }
    
    @Test
    public void testContainName() {
        operations.addOperation(nameA, operationA);
        operations.addOperation(nameB, operationB);
        operations.addOperation(nameC, operationC);
        assertEquals(true , operations.containName(nameA));
        assertEquals(true , operations.containName(nameB));
        assertEquals(true , operations.containName(nameC));
        assertEquals(false , operations.containName("nameD"));
        assertEquals(false , operations.containName("nameE"));
        assertEquals(false , operations.containName("nameF"));
    }
    
    @Test
    public void testGetOperation() {
        operations.addOperation(nameA, operationA);
        operations.addOperation(nameB, operationB);
        operations.addOperation(nameC, operationC);
        assertEquals("+ -" , operations.getSequence(nameA));
        assertEquals("/ *" , operations.getSequence(nameB));
        assertEquals("sqrt +-" , operations.getSequence(nameC));        
    }
    
    @Test (expected = WrongInputException.class)
    public void testCheckUserDefinedOpration() {
        String s1 = "op2";
        operations.checkUserDefinedOpration(s1);
    }
    
}
