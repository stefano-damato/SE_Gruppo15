/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyAlreadyPresentInOperations;
import exceptions.OperationFailedException;
import exceptions.LessOf2ElementsException;
import exceptions.KeyNotPresentInOperations;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableMap;

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
    
    private HashMap<String, String> invokeOperations;
    
    private HashMap<String, String> variableOperation;
    
    
    /**
     * Initialize complexStack and variables to empty stacks
     */
    public Calculator(){
        complexStack = new Stack<>();
        variables = new Stack<>();
        /**Push the new object {@code variables} into a VariableMap*/
        variables.push(new VariableMap());
        operations = new Operations();
        invokeOperations = new HashMap<>();
        invokeOperations.put("+", "add");
        invokeOperations.put("-", "sub");
        invokeOperations.put("*", "multiply");
        invokeOperations.put("/", "divide");
        invokeOperations.put("+-", "invert");
        invokeOperations.put("sqrt", "square");
        invokeOperations.put("drop", "drop");
        invokeOperations.put("dup", "dup");
        invokeOperations.put("over", "over");
        invokeOperations.put("swap", "swap");
        invokeOperations.put("clear", "clear");
        
        variableOperation = new HashMap<>();
        variableOperation.put("<", "pushVariable");
        variableOperation.put(">", "saveVariable");
        variableOperation.put("+", "addVariable");
        variableOperation.put("-", "subVariable");
    }
    
    /**
     * The method returns the last element of the variables whitin the VariableMap
     * @return variables
     */
    public VariableMap getVariables() {
        return variables.lastElement();
    }

    public Stack<Complex> getComplexStack() {
        return complexStack;
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
    
    public void invokeOperation(String name){
        String seq = operations.getOperation(name);
        String[] userOperations = seq.split(" ");
        for (String operation : userOperations) {
            if (operation.length() == 2) {
                selectOperationVariableToInvoke(operation);   
                System.out.println(operation);
            }
            else selectOperationToInvoke(operation);
        }
    }
    
    public void selectOperationToInvoke(String op) throws OperationFailedException{
        Method m;
        if (invokeOperations.containsKey(op)){
            try {
                m = Calculator.class.getDeclaredMethod(invokeOperations.get(op));
                m.invoke(this);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new OperationFailedException();
            } 
        }
    }
    
    public void selectOperationVariableToInvoke(String op) throws OperationFailedException{
        Method m;
        String key = op.substring(0, 1);
        if (variableOperation.containsKey(key)){
            System.out.println("asadsasdasda");
            try {
                m = VariableMap.class.getDeclaredMethod(variableOperation.get(key), Variable.class);
                Variable var;
                if (op.charAt(0) == '<') {
                    var = new Variable(op.charAt(1), null);
                }
                else {
                    var = new Variable(op.charAt(1), drop());
                     System.out.println(key);
                }
                m.invoke(getVariables(), var);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public Operations getOperations() {
        return operations;
    }
       
}
