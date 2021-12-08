/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scientificcalculator;

import exceptions.DivisionException;
import exceptions.LessOf2ElementsException;
import exceptions.OperationFailedException;
import exceptions.WrongInputException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nicol
 */
public class ComplexStack {
    /*** Contains all the complex that will be inserted*/
    private Stack<Complex> complexStack; 
    /** */
    private HashMap<String, String> invokeOperations;
    
    /**
     * Initialize complexStack
     */
    public ComplexStack() {
        complexStack = new Stack<>();
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
    }
    
    /**
     * The method insert a complex number into the stack
     * @param c {@code Complex}
     */
    public void insert(Complex c){
        complexStack.push(c);
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
    
    public Stack<Complex> getComplexStack(){
        return complexStack;
    }
    
    /**
     * The method returns the last element within the stack
     * @return complexStack.lastElement
     */
    public Complex lastElement(){
        return complexStack.lastElement();
    }
    
    /** */
    public int size(){
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
     * @throws DivisionException if division is not possible
     */
    public void divide() throws LessOf2ElementsException, DivisionException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex b = complexStack.get(complexStack.size()-2);
        
        if(b.getReal()==0 && b.getImaginary()==0)
            throw new DivisionException("Division not possible");
        else{
            Complex a = complexStack.pop();
            complexStack.pop();
            insert(a.divide(b));
        }
        
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
            
    public void selectOperationToInvoke(String op) throws OperationFailedException{
        Method m;
        if (invokeOperations.containsKey(op)){
            try {
                m = ComplexStack.class.getDeclaredMethod(invokeOperations.get(op));
                m.invoke(this);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new OperationFailedException();
                
            }
        }else insert(op);        
    } 
}
