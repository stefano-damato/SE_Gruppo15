/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.OperationFailedException;
import exceptions.LessOf2ElementsException;
import exceptions.KeyNotPresentInOperations;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * The <em>Calculator</em> class contains all useful methods implemented for the correct 
 * functioning of the stack
 * 
 * @author group15
 */
public class Calculator {
    /*** Contains all the complex that will be inserted*/
    private Stack<Complex> complexStack;
    /*** */
    private Stack<VariableMap> variables;
    
    private Operations operations;
    
    /**
     * Initialize complexStack and variables to empty stacks
     */
    public Calculator(){
        complexStack = new Stack<>();
        variables = new Stack<>();
        /**Push the new object {@code variables} into a VariableMap*/
        variables.push(new VariableMap());
        operations = new Operations();
    }
    
    /**
     * The method returns the last element of the variables whitin the VariableMap
     * @return variables
     */
    public VariableMap getVariables() {
        return variables.lastElement();
    }
    
    /**
     * The method insert a complex number into the stack
     * @param c {@code Complex}
     */
    public void insert(Complex c){
        complexStack.push(c);
    }
    
    /**
     * The method returns the last element within the stack
     * @return complexStack.lastElement
     */
    public Complex lastElement(){
        return complexStack.lastElement();
    }
    
    public int complexStackSize(){
        return complexStack.size();
    }
    /**
     * The method removes the last two elements from the stack, 
     * execute the <code>add</code> method on the first one
     * and puts the sum on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void add() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.add(b));
    }
    
    /**
     * The method removes the last two elements from the stack, 
     * execute the <code>sub</code> method on the first one
     * and puts the sub on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void sub() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.sub(b));
    }
    
    /**
     * The method removes the last two elements from the stack,
     * execute the <code>multiply</code> method on the first one
     * and puts the multiplication on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void multiply() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.multiply(b));
    }
    
    /**
     * The method removes the last two elements from the stack,
     * execute the <code>divide</code> method on the first one
     * and puts the division on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void divide() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a.divide(b));
    }
    
    /**
     * The method removes the last element from the stack, 
     * execute the <code>square</code> method
     * and puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void square() throws EmptyStackException{
        if(complexStack.size()<1)
            throw new EmptyStackException();
        Complex a = complexStack.pop();
        
        insert(a.square());
    }
    
    /**
     * The method removes the last element from the stack,
     * execute the <code>invert</code> method
     * puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void invert()throws EmptyStackException{
        if(complexStack.size()<1)
            throw new EmptyStackException();
        Complex a = complexStack.pop();
        
        insert(a.invert());
    }
    
    /**
     * The method empties the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void clear() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        complexStack.clear();
    }
    
    /**
     * The method removes the last element from the stack
     * @throws EmptyStackException if the stack is empty
     * @return complexStack.pop() {@code Complex}
     */
    public Complex drop() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        return complexStack.pop();
    }
   
    /**
     * The method duplicates the last element in the stack 
     * @throws EmptyStackException if the stack is empty
     */
    public void dup() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        Complex a = complexStack.lastElement();
        
        insert(a);
    }
    
    /**
     * The method swap the last two elements in the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void swap() throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.pop();
        Complex b = complexStack.pop();
        
        insert(a);
        insert(b);
    } 
    /**
     * The method inserts a copy of the second last element in the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void over()throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.get(complexStack.size() - 2);
        
        insert(a);
    }
    
    public void invokeOperation(String name) throws KeyNotPresentInOperations, OperationFailedException{
        if(!operations.containName(name)) throw new KeyNotPresentInOperations();
        String operationName = operations.getOperation(name);
        String[] allOperation = operationName.split(" ");
        try {
            for (String singleOperation : allOperation) {
                if (singleOperation.equalsIgnoreCase("+")) {
                    add();
                }
                if (singleOperation.equalsIgnoreCase("-")) {
                    sub();
                }
                if (singleOperation.equalsIgnoreCase("*")) {
                    multiply();
                }
                if (singleOperation.equalsIgnoreCase("/")) {
                    divide();
                }
                if (singleOperation.equalsIgnoreCase("sqrt")) {
                    square();
                }
                if (singleOperation.equalsIgnoreCase("+-")) {
                    invert();
                }
                if (singleOperation.equalsIgnoreCase("dup")) {
                    dup();
                }
                if (singleOperation.equalsIgnoreCase("swap")) {
                    swap();
                }
                if (singleOperation.charAt(0) == '<') {
                    char x = singleOperation.charAt(1);            
                    insert(getVariables().pushVariable(x));
                }
                if (singleOperation.charAt(0) == '>') {
                    char x = singleOperation.charAt(1);
                    getVariables().saveVariable(x, drop());              
                }
                /**
                if (singleOperation.equalsIgnoreCase("save")){

                } 
                if (singleOperation.equalsIgnoreCase("restore")) {

                } **/
                if (singleOperation.equalsIgnoreCase("clear")) {
                    clear();
                }
                if (singleOperation.equalsIgnoreCase("over")) {
                    over();
                }
                if (singleOperation.equalsIgnoreCase("drop")) {
                    drop();
                }
            }
        }catch (Exception ex) {
            throw new OperationFailedException();
        }
    }
}
