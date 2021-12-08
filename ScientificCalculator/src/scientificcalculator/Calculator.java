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
import exceptions.WrongInputException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableMap;
import javafx.event.Event;

/**
 * The <em>Calculator</em> class contains all useful methods implemented for the correct 
 * functioning of the stack
 * 
 * @author group15
 */
public class Calculator {
    /*** Contains all the complex that will be inserted*/
    private ComplexStack complexStack;
    /*** */
    private VariableMapStack variables;
    
    private VariableMap currentVariables;
    
    private Operations operations;
       
    private HashMap<String, String> variableOperation;
    
    
    /**
     * Initialize complexStack and variables to empty stacks
     */
    public Calculator(){
        complexStack = new ComplexStack();
        /**Push the new object {@code variables} into a VariableMap*/
        variables = new VariableMapStack();
        currentVariables = new VariableMap();
        operations = new Operations();
       
        variableOperation = new HashMap<>();
        variableOperation.put("<", "pushVariable");
        variableOperation.put(">", "saveVariable");
        variableOperation.put("+", "addVariable");
        variableOperation.put("-", "subVariable");
    }
    
    /**
     * The method returns the last copy of variables saved in the VariableMapStack
     * @return variables
     */
    public VariableMap getVariables() {
        return currentVariables;
    }
   
    /**
     * The method insert a complex number into the stack
     * @param c {@code Complex}
     */
    public void insert(Complex c){
        complexStack.insert(c);
    }
    
    public void insert(String input){
        complexStack.insert(input);
    }
    
    public ComplexStack getComplex(){
        return complexStack;
    }
    
    public Stack<Complex> getComplexStack() {
        return complexStack.getComplexStack();
    }
    
    public Operations getOperations() {
        return operations;
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
        complexStack.add();
    }
    
    /**
     * The method removes the last two elements from the stack, 
     * execute the <code>sub</code> method on the first one
     * and puts the sub on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void sub() throws LessOf2ElementsException{
        complexStack.sub();
    }
    
    /**
     * The method removes the last two elements from the stack,
     * execute the <code>multiply</code> method on the first one
     * and puts the multiplication on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void multiply() throws LessOf2ElementsException{
        complexStack.multiply();
    }
    
    /**
     * The method removes the last two elements from the stack,
     * execute the <code>divide</code> method on the first one
     * and puts the division on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void divide() throws LessOf2ElementsException{
        complexStack.divide();
    }
    
    /**
     * The method removes the last element from the stack, 
     * execute the <code>square</code> method
     * and puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void square() throws EmptyStackException{
        complexStack.square();
    }
    
    /**
     * The method removes the last element from the stack,
     * execute the <code>invert</code> method
     * puts the result on the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void invert()throws EmptyStackException{
        complexStack.invert();
    }
    
    /**
     * The method empties the stack
     * @throws EmptyStackException if the stack is empty
     */
    public void clear() throws EmptyStackException{
        complexStack.clear();
    }
    
    /**
     * The method removes the last element from the stack
     * @throws EmptyStackException if the stack is empty
     * @return complexStack.pop() {@code Complex}
     */
    public Complex drop() throws EmptyStackException{
        return complexStack.drop();
    }
   
    /**
     * The method duplicates the last element in the stack 
     * @throws EmptyStackException if the stack is empty
     */
    public void dup() throws EmptyStackException{
        complexStack.dup();
    }
    
    /**
     * The method swap the last two elements in the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void swap() throws LessOf2ElementsException{
        complexStack.swap();
    } 
    /**
     * The method inserts a copy of the second last element in the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void over()throws LessOf2ElementsException{
        complexStack.over();
    }
            
    public void invokeOperation(String name) throws WrongInputException, IndexOutOfBoundsException{
        String seq = operations.getOperation(name);
        String[] userOperations = seq.split(" ");
        for (String operation : userOperations) {
            if (operation.length() == 2 && operation.substring(0, 1).matches("^[+-><]+$")&& Character.isAlphabetic(operation.charAt(1))) {
                selectOperationVariableToInvoke(operation);   
            }
            else if(operations.containName(operation)){
                    invokeOperation(operation);         
            }
            else complexStack.selectOperationToInvoke(operation);
        }
    }
    
    public void selectOperationVariableToInvoke(String op) throws OperationFailedException{
        Method m;
        String key = op.substring(0, 1);
        if (variableOperation.containsKey(key)){
            try {
                m = VariableMap.class.getDeclaredMethod(variableOperation.get(key), Variable.class);
                Variable var;
                if (op.charAt(0) == '<') {
                    var = new Variable(op.charAt(1), null);
                    insert((Complex) m.invoke(getVariables(), var));
                }
                else {
                    var = new Variable(op.charAt(1), drop());
                     m.invoke(getVariables(), var);
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    /**
     * The method saves the last copy of variables into the VariableMapStack
     */
    public void saveVariables() {
        VariableMap foo = new VariableMap();
        
        for(Map.Entry<Character,Complex> entry: currentVariables.getVariables().entrySet()){
            foo.save(new Variable(entry.getKey(),entry.getValue()));
        }
        variables.save(foo);
    }
    
    /**
     * The method restores the last copy of variables previously saved in the VariableMapStack
     * @throws EmptyStackException if there are less than two elements
     */
    public void restoreVariables() throws EmptyStackException{
        VariableMap foo = new VariableMap();
        foo=variables.restore();
        currentVariables.getVariables().clear();
        for(Map.Entry<Character,Complex> entry: foo.getVariables().entrySet()){
            Variable var = new Variable(entry.getKey(),entry.getValue());
            currentVariables.save(new Variable(entry.getKey(),entry.getValue()));
        }
        
    }
    
    public void saveVariable(char key){
        Complex c = drop();
        Variable var = new Variable(key,c);
        currentVariables.save(var);
    }
    
    public void pushVariable(Variable var){
        currentVariables.push(var);
    }
    
    public void addVariable(Variable var){
        currentVariables.add(var);
    }
    
    public void subVariable(Variable var){
        currentVariables.sub(var);
    }

}
