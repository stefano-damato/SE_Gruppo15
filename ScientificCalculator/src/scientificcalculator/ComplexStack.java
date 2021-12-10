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
 * The <em>ComplexStack</em> represents a stack of {@link scientificcalculator.Complex Complex} objects
 * @author group15
 */
public class ComplexStack {
    /*** Contains all the {@link scientificcalculator.Complex Complex} that will be inserted*/
    private Stack<Complex> complexStack; 
    /** contains the methods <b>(value)</b> of {@link scientificcalculator.ComplexStack ComplexStack},
     * operating on {@link #complexStack complexStack}, that can be invoked by a given command <b>(key)</b>*/
    private HashMap<String, String> invokeOperations;
    
    /**
     * Initialize {@link #complexStack complexStack} to a empty Stack and 
     * <br> {@link #complexStack invokeOperations}
     * <br> -  key,Value
     * <br> -  "+", "add"
     * <br> -  "-", "sub"
     * <br> -  "*", "multiply"
     * <br> -  "/", "divide"
     * <br> -  "+-", "invert"
     * <br> -  "sqrt", "square"
     * <br> -  "drop", "drop"
     * <br> -  "dup", "dup"
     * <br> -  "over", "over"
     * <br> -  "swap", "swap"
     * <br> -  "clear", "clear"
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
     * The method insert a {@link scientificcalculator.Complex Complex} into the {@link #complexStack complexStack}.
     * @param c {@code Complex} the complex number to insert.
     */
    public void insert(Complex c){
        complexStack.push(c);
    }
    
    /**
     * The method insert a new {@link scientificcalculator.Complex Complex} initialized to the value represented by the specified {@code String} into the {@link #complexStack complexStack}.
     * @param input {@code String} the string to be parsed.
     * @throws WrongInputException
     * @throws IndexOutOfBoundsException 
     */
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
     * @param s {@code String} the String to check.
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
     * The method return a reference to {@link #complexStack complexStack}
     * @return complexStack - {@code Stack<{@link scientificcalculator.Complex Complex}>}
     */
    public Stack<Complex> getComplexStack(){
        return complexStack;
    }
    
    /**
     * The method returns the last element within the {@link #complexStack complexStack}.
     * @return the last {@code {@link scientificcalculator.Complex Complex}} of the Stack
     */
    public Complex lastElement(){
        return complexStack.lastElement();
    }
    
    /** */
    public int size(){
        return complexStack.size();
    }
    
    /**
     * The method removes the last two elements from the {@link #complexStack complexStack},
     * execute the {@link scientificcalculator.Complex#sub sub} method on the first one
     * and puts the sub on the stack
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
     * The method removes the last two elements from the {@link #complexStack complexStack},
     * execute the {@link scientificcalculator.Complex#sub sub} method on the first one
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
     * The method removes the last two elements from the {@link #complexStack complexStack}, 
     * execute the {@link scientificcalculator.Complex#multiply multiply} method on the first one
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
     * The method removes the last two elements from the {@link #complexStack complexStack}, 
     * execute the {@link scientificcalculator.Complex#divide divide} method on the first one
     * and puts the division on the stack
     * @throws LessOf2ElementsException if there are less of two elements in the stack
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
     * The method removes the last element from the {@link #complexStack complexStack}, 
     * execute the {@link scientificcalculator.Complex#square square} method
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
     * The method removes the last element from the {@link #complexStack complexStack}, 
     * execute the {@link scientificcalculator.Complex#invert invert} method
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
     * The method empties the {@link #complexStack complexStack}.
     * @throws EmptyStackException if the stack is empty
     */
    public void clear() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        complexStack.clear();
    }
    
    /**
     * The method removes the last element from the {@link #complexStack complexStack}.
     * @throws EmptyStackException if the stack is empty
     * @return complexStack.pop() {@code Complex} the last element of the stack
     */
    public Complex drop() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        return complexStack.pop();
    }
   
    /**
     * The method duplicates the last element in the {@link #complexStack complexStack}.
     * @throws EmptyStackException if the stack is empty
     */
    public void dup() throws EmptyStackException{
        if (complexStack.size() < 1)
            throw new EmptyStackException();
        Complex a = complexStack.lastElement();
        
        insert(a);
    }
    
    /**
     * The method swap the last two elements in the {@link #complexStack complexStack}.
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
     * The method inserts a copy of the second last element in the {@link #complexStack complexStack}.
     * @throws LessOf2ElementsException if there are less of two elements in the stack
     */
    public void over()throws LessOf2ElementsException{
        if(complexStack.size()<2)
            throw new LessOf2ElementsException();
        Complex a = complexStack.get(complexStack.size() - 2);
        
        insert(a);
    }
    
    /**
     * The method decides depending on the passed command
     * which method to invoke on this object
     * The possible methods to invoke are saved in the <code>HashMap</code>  {@link #invokeOperations invokeOperations}.
     * <br>
     * <br> -  "+" invoke {@link #add() add}
     * <br> -  "-" invoke {@link #sub() sub}
     * <br> -  "*" invoke {@link #multiply() multiply}
     * <br> -  "/" invoke {@link #divide() divide}
     * <br> -  "+-" invoke {@link #invert() invert}
     * <br> -  "sqrt" invoke {@link #sqrt() sqrt}
     * <br> -  "drop" invoke {@link #drop() drop}
     * <br> -  "dup" invoke {@link #dup() dup}
     * <br> -  "over" invoke {@link #over() over}
     * <br> -  "swap" invoke {@link #swap() swap}
     * <br> -  "clear" invoke {@link #clear() clear}
     * <br>
     * @param op {@code String} the command to invoke
     * @throws OperationFailedException it is not possible to execute the chosen action.
     */
    public void selectOperationToInvoke(String op) throws OperationFailedException{
        Method m;
        if (invokeOperations.containsKey(op)){
            try {
                m = ComplexStack.class.getDeclaredMethod(invokeOperations.get(op));
                m.invoke(this);
            } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                throw new OperationFailedException();
                
            }
        }else {
            try {
                insert(op);
            }catch(WrongInputException | IndexOutOfBoundsException ex) {
                throw new OperationFailedException();
            }
        } 
                    
    } 
}
