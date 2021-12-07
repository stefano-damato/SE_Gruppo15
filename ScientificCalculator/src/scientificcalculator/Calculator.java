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
import java.util.EmptyStackException;
import java.util.HashMap;
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
            
    public void invokeOperation(String name) throws WrongInputException, IndexOutOfBoundsException{
        String seq = operations.getOperation(name);
        String[] userOperations = seq.split(" ");
        for (String operation : userOperations) {
            if (operation.length() == 2 && operation.substring(0, 1).matches("^[+-><]+$")&& Character.isAlphabetic(operation.charAt(1))) {
                selectOperationVariableToInvoke(operation);   
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
        else if(!operations.containName(op)){
            insert(op);
        }
        else{
            invokeOperation(op);
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
     * The method returns the reference to the Operations object.
     * @return operations {@code Operations}
     */
    public Operations getOperations() {
        return operations;
    }
    
    public void insert(String input) throws WrongInputException, IndexOutOfBoundsException{
        double real=0;
        double imaginary=0;
        int sign=1;
        Complex c;
        
        //check string
        if(input.indexOf("-")==0 || input.indexOf("+")==0){
            if(input.indexOf("-")==0)
                sign=-1;
            input=input.substring(1);
        }

        checkValidInput(input.trim());

        //The number has only the real part 
        if(!input.contains("j")){
                real=sign*Double.parseDouble(input);
        }else{
            input = input.trim();
            int j=input.indexOf("j");
            ////The number has only the imaginary part 
            if(!(input.contains("+") || input.contains("-"))){
                if(input.length()==1)
                    imaginary = sign*1;
                else
                    imaginary=sign*Double.parseDouble(input.substring(0,input.length()-1));
            }else{
                ////the number has both real and imaginary part
                String[] parseText= input.split("[+-]");
                real=sign*Double.parseDouble(parseText[0]);
                if(parseText[1].trim().length()==1)
                    imaginary=1;
                else
                    imaginary=Double.parseDouble(parseText[1].substring(0,parseText[1].length()-1));
                if(input.contains("-"))
                    imaginary=-imaginary;
            }
        }
        
        insert(new Complex(real, imaginary));
    }
       
    
    /**
     * The method checks if the string <code>s</code>  is in the form a + j b. a and b must be real numbers.
     * @param s {@code String}
     * @return <code>true</code>  if the string is correct
     * @throws WrongInputException 
     */
    private boolean checkValidInput(String s) throws WrongInputException{
        if(!s.contains("j")){
            if(!s.matches("^[0-9.]+$"))
            throw new WrongInputException();
        }else{
            if(!s.matches("^[0-9+-.j ]+$")){
                throw new WrongInputException();
            }else{
                if(s.indexOf("j")!=(s.length()-1))
                    throw new WrongInputException();
                if(s.contains("+")|| s.contains("-")){
                    String[] parseText= s.split("[+-]");
                    String s1 = parseText[0].trim();
                    String s2 = parseText[1].trim();
                    if(!s1.matches("^[0-9.]+$"))
                        throw new WrongInputException();
                    if(s2.length()==1)
                        return true;
                    s2=s2.substring(0, s2.length()-1).trim();
                    if(!s2.matches("^[0-9.]+$"))
                        throw new WrongInputException();
                }else{
                    if(s.length()==1)
                        return true;
                    if(!s.substring(0, s.length()-1).trim().matches("^[0-9.]+$")){
                        throw new WrongInputException();
                    }
                }
            }
        }
        return true;
    }
    
    /**
     * The method creates a new instance of the VariableMap object and inserts it onto stack.
     */
    public void saveVariables() {
        VariableMap newMap = new VariableMap();
        variables.push(newMap);
    }
    
    /**
     * The method deletes the last inserted VariableMap object and restores the previous one.
     * @throws EmptyStackException if there are less than two elements
     */
    public void restoreVariables() throws EmptyStackException{
        if (variables.size() <= 1)
            throw new EmptyStackException();
        variables.pop();
    }

}
