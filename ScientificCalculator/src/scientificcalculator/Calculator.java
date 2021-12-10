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
    /** Contains all the complex numbers that will be inserted*/
    private ComplexStack complexStack;
    /** Contains all saved versions of the variables*/
    private VariableMapStack variables;
    /** Contains all the variables that will be inserted*/
    private VariableMap currentVariables;
    /** Contains all the user defined operation*/
    private Operations operations;
    
    /** contains the name of methods <b>(value)</b> of {@link scientificcalculator.Calculator Calculator}, 
     * operating on {@link #currentVariables currentVariables},that can be invoked by a given command <b>(key)</b>*/
    private HashMap<String, String> variableOperation;
    /** contains the name of methods <b>(value)</b> of {@link scientificcalculator.Calculator Calculator},
     * operating on {@link #variables variables}, that can be invoked by a given command <b>(key)</b>*/
    private HashMap<String, String> variableStackOperation;
    
    
    /**
     * Initialize {@link #complexStack complexStack},{@link #variables variables},{@link #currentVariables currentVariables},{@link #currentVariables currentVariables} through their Constructor and
     * <br>{@link #variableOperation variableOperation}
     * <br> -  key,Value
     * <br> -  "&lt" "pushVariable"
     * <br> -  ">", "saveVariable"
     * <br> -  "+", "addVariable"
     * <br> -  "+", "addVariable"
     * <br><br>{@link #variableStackOperation variableStackOperation}
     * <br> -  key,Value
     * <br> -  "save", "saveVariables"
     * <br> -  "restore", "restoreVariables"
     * <br>
     * 
     */
    public Calculator(){
        complexStack = new ComplexStack();
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
     * The method returns the {@link #currentVariables currentVariables} object of type VariableMap.
     * @return variables  {@code VariableMap}
     */
    public VariableMap getVariables() {
        return currentVariables;
    }
    /**
     * The method returns the stack of complex numbers saved in the object {@link #complexStack complexStack}.
     * @return complexStack.getComplexStack() {@code Stack<Complex>}
     */
    public Stack<Complex> getComplexStack() {
        return complexStack.getComplexStack();
    }
    /**
     * The method returns the {@link #operations operations} object of type Operations
     * @return operations  {@code Operations}
     */
    public Operations getOperations() {
        return operations;
    }
    
    /**
     * The method manages a sequence of operations by deciding which method to invoke according to the type of operation
     * @param name  {@code String} the name of the sequence of the operations to invoke
     * @throws WrongInputException if the string that has <b>name</b> as key in the observableMap contained in <code>operations</code> is not in the right format
     * @throws IndexOutOfBoundsException if the string that has <b>name</b> as key in the observableMap contained in <code>operations</code>, contains a number to insert written in the wrong way
     */
    public void invokeOperation(String name) {
        if(operations.containName(name)){
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
    }
    
    /**
     * The method calls {@link scientificcalculator.ComplexStack#selectOperationToInvoke(String) selectOperationToInvoke} 
     * method of the <code>complexStack</code> object,
     * passing as argument the type of operation you want to invoke.
     * @param op  {@code String} the command to invoke
     * 
     */
    public void selectOperationToInvoke(String op) throws OperationFailedException {
        complexStack.selectOperationToInvoke(op);
    }
    
    /**
     * The method decides depending on the passed command
     * which method to invoke on the object {@link #currentVariables currentVariables}.
     * The possible methods to invoke are saved in the <code>HashMap</code>  {@link #variableOperation variableOperation}.
     * <br>
     * The first character is the <b>command</b>.
     * <br>
     * The second character is the name of the <b>variable</b> on which the method will be executed.
     * <br>
     * <br> -  ">" invoke {@link #saveVariable(char) saveVariable}
     * <br> -  "&lt" invoke {@link #pushVariable(char) pushVariable}
     * <br> -  "+" invoke {@link #addVariable(char) addVariable}
     * <br> -  "-" invoke {@link #subVariable(char) subVariable}
     * <br>
     * @param op {@code String} the command to invoke
     * @throws OperationFailedException it is not possible to execute the chosen action.
     */
    public void selectOperationVariableToInvoke(String op) throws OperationFailedException{
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
    
    /**
     * The method decides depending on the passed command
     * which method to invoke on the object {@link #variables variables}.
     * The possible methods to invoke are saved in the <code>HashMap</code>  {@link #variableStackOperation variableStackOperation}.
     * <br>
     * <br> -  "save" invoke {@link #saveVariables() saveVariables}
     * <br> -  "restore" invoke {@link #restoreVariables() restoreVariables}
     * <br>
     * @param op {@code String} the command to invoke
     * @throws OperationFailedException it is not possible to execute the chosen action.
     */
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
     * The method saves a copy of the variables contained in the object {@link #currentVariables currentVariables}
     * into the object {@link #VariableMapStack VariableMapStack}.
     */
    public void saveVariables() {
        VariableMap foo = new VariableMap();
        for(Map.Entry<Character,Complex> entry: currentVariables.getVariables().entrySet()){
            foo.save(new Variable(entry.getKey(),entry.getValue()));
        }
        variables.save(foo);
    }
    
    /**
     * The method restores the last copy of variables previously saved in the object 
     * {@link #VariableMapStack VariableMapStack},removing them from it,
     * in the object in the object {@link #currentVariables currentVariables}.
     * @throws EmptyStackException if there are less than one element 
     */
    public void restoreVariables() throws EmptyStackException{
        VariableMap foo = variables.restore();
        currentVariables.getVariables().clear();
        for(Map.Entry<Character,Complex> entry: foo.getVariables().entrySet()){
            Variable var = new Variable(entry.getKey(),entry.getValue());
            currentVariables.save(var);
        }
        
    }
    
    /**
     * The method saves a new variable containing the last complex of the object
     * {@link #complexStack complexStack} from which it will be removed.
     * @param key {@code char} the name of the new variable.
     */
    public void saveVariable(char key) throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.save(var);
    }
    
    /**
     * The method saves the value of a variable in the object {@link #complexStack complexStack}.
     * @param key {@code char} the name of the variable.
     * @return {@code Complex} the value saved in the variable.
     */
    public Complex pushVariable(char key) throws VariableNotFoundException{
        Variable var = new Variable(key,null);
        return currentVariables.push(var);
    }
    
    /**
     * The method adds to the value of a variable the last complex of the object
     * {@link #complexStack complexStack} from which it will be removed. 
     * @param key {@code char} the name of the variable.
     */
    public void addVariable(char key)throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.add(var);
    }
    
    /**
     * The method subtracts to the value of a variable the last complex of the object
     * {@link #complexStack complexStack} from which it will be removed. 
     * @param key {@code char} the name of the variable.
     */
    public void subVariable(char key)throws EmptyStackException, KeyNotAlphabeticException{
        Variable var = new Variable(key,complexStack.drop());
        currentVariables.sub(var);
    }
    
    /**
     * The method checks if the string <code>s</code> is in 
     * the correct form to do operations with variables (eg. ">a","&lta","+a","-a").
     * @param s {@code String} the string to check
     * @throws WrongInputException if the String is not in the correct form
     */
    private void checkValidInputForVariables(String s) throws WrongInputException{
        if(s.length()!=2 || !s.substring(0, 1).matches("^[+-><]+$") || !Character.isAlphabetic(s.charAt(1)))
            throw new WrongInputException();
    }

}
