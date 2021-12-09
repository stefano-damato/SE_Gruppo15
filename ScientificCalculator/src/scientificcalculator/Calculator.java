/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.KeyAlreadyPresentInOperations;
import exceptions.KeyNotAlphabeticException;
import exceptions.OperationFailedException;
import exceptions.LessOf2ElementsException;
import exceptions.KeyNotPresentInOperations;
import exceptions.VariableNotFoundException;
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
    private HashMap<String, String> variableStackOperation;
    
    
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
        
        variableStackOperation = new HashMap<>();
        variableStackOperation.put("save", "saveVariables");
        variableStackOperation.put("restore", "restoreVariables");
    }
    
    /**
     * The method returns the last copy of variables saved in the VariableMapStack
     * @return variables
     */
    public VariableMap getVariables() {
        return currentVariables;
    }
    
    public Stack<Complex> getComplexStack() {
        return complexStack.getComplexStack();
    }
    
    public Operations getOperations() {
        return operations;
    }
             
    public void invokeOperation(String name) throws WrongInputException{
        String seq = operations.getSequence(name);
        String[] userOperations = seq.split(" ");
        for (String operation : userOperations) {
            if (operation.length() == 2 && operation.substring(0, 1).matches("^[+-><]+$")&& Character.isAlphabetic(operation.charAt(1))) {
                selectOperationVariableToInvoke(operation);   
            }else if(operations.containName(operation)){
                invokeOperation(operation);         
            }else if(operation.equalsIgnoreCase("save") || operation.equalsIgnoreCase("restore")){
                selectOperationVariableStackToInvoke(operation);
            }
            else selectOperationToInvoke(operation);
        }
    }
    
    public void selectOperationToInvoke(String op) throws OperationFailedException {
        complexStack.selectOperationToInvoke(op);
    }
    
    public void selectOperationVariableToInvoke(String op) throws OperationFailedException, WrongInputException{
        checkValidInputForVariables(op);
        Method m;
        String key = op.substring(0, 1);
        if (variableOperation.containsKey(key)){
            try {
                m = Calculator.class.getDeclaredMethod(variableOperation.get(key), char.class);
                if (op.charAt(0) == '<') {
                    complexStack.insert((Complex) m.invoke(this, op.charAt(1)));
                }
                else {
                     m.invoke(this, op.charAt(1));
                }
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new OperationFailedException();
            } 
        }
    }
    
    public void selectOperationVariableStackToInvoke(String op) throws OperationFailedException{
        Method m;
        if (variableStackOperation.containsKey(op)){
            try {
                m = Calculator.class.getDeclaredMethod(variableStackOperation.get(op));
                m.invoke(this);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new OperationFailedException();
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
        VariableMap foo = variables.restore();
        currentVariables.getVariables().clear();
        for(Map.Entry<Character,Complex> entry: foo.getVariables().entrySet()){
            Variable var = new Variable(entry.getKey(),entry.getValue());
            currentVariables.save(var);
        }
        
    }
    
    public void saveVariable(char key) throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.save(var);
    }
    
    public Complex pushVariable(char key) throws VariableNotFoundException{
        Variable var = new Variable(key,null);
        return currentVariables.push(var);
    }
    
    public void addVariable(char key)throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.add(var);
    }
    
    public void subVariable(char key)throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.sub(var);
    }
    
    /**
     * The method checks if the string <code>s</code> is in 
     * the correct form to do operations with variables
     * @param s {@code String}
     * @throws WrongInputException 
     */
    private void checkValidInputForVariables(String s) throws WrongInputException{
        if(s.length()!=2 || !s.substring(0, 1).matches("^[+-><]+$") || !Character.isAlphabetic(s.charAt(1)))
            throw new WrongInputException();
    }

}
